package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.FileDto;
import com.example.jubileebudgetapp.exceptions.RecordNotFoundException;
import com.example.jubileebudgetapp.exceptions.UnsupportedFileTypeException;
import com.example.jubileebudgetapp.exceptions.UploadedFileNotFoundException;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.File;
import com.example.jubileebudgetapp.repositories.AccountRepository;
import com.example.jubileebudgetapp.repositories.FileRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
public class FileService {

    private final FileRepository fileRepository;
    private final AccountRepository accountRepository;


    public FileService(FileRepository fileRepository, AccountRepository accountRepository) {
        this.fileRepository = fileRepository;
        this.accountRepository = accountRepository;
    }

    public FileDto uploadFile(MultipartFile file) {
        try {
            String contentType = file.getContentType();

            if (!Objects.equals(contentType, MediaType.APPLICATION_PDF_VALUE)) {
                throw new UnsupportedFileTypeException("Uploaded file must be a PDF.");
            }

            byte[] fileBytes = file.getBytes();
            String fileName = file.getOriginalFilename();

            File uploadedFile = convertDtoToFile(createFileDto(fileBytes, fileName));
            File savedFile = fileRepository.save(uploadedFile);

            return convertFileToDto(savedFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    public Resource downloadFile(Long fileId) throws UploadedFileNotFoundException {
        File file =fileRepository.findById(fileId)
                .orElseThrow(() -> new UploadedFileNotFoundException(fileId));

        byte[] fileBytes = file.getUploadedFile();
        String fileName = file.getFileName();

        return new ByteArrayResource(fileBytes){
            @Override
            public String getFilename() {
                return fileName;
            }
        };
    }

    public void deleteFile(Long fileId) throws FileNotFoundException{
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with ID: " + fileId));

        fileRepository.delete(file);
    }

    //helper methods
    public File convertDtoToFile(FileDto fileDto){
        File file = new File();

        file.setUploadedFile(fileDto.getUploadedFile());
        file.setFileName(fileDto.getFileName());

        return file;
    }

    public FileDto convertFileToDto(File file){
        FileDto fileDto = new FileDto();

        fileDto.setId(file.getId());
        fileDto.setUploadedFile(file.getUploadedFile());
        fileDto.setFileName(file.getFileName());

        return fileDto;
    }

    private FileDto createFileDto(byte[] fileBytes, String fileName){
        FileDto fileDto = new FileDto();

        fileDto.setUploadedFile(fileBytes);
        fileDto.setFileName(fileName);

        return fileDto;
    }

    public void assignFileToAccount(Long id, Long accountId){
        Optional<File> optionalFile = fileRepository.findById(id);
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (optionalFile.isPresent() && optionalAccount.isPresent()){
            var contract = optionalFile.get();
            var account = optionalAccount.get();

            contract.setAccount(account);
            fileRepository.save(contract);
        } else {
            throw new RecordNotFoundException();
        }
    }

}
