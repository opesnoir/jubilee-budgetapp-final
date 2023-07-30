package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.FileDto;
import com.example.jubileebudgetapp.exceptions.UnsupportedFileTypeException;
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
import java.util.Objects;

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

            switch (Objects.requireNonNull(contentType)){
                case MediaType.APPLICATION_PDF_VALUE:
                case MediaType.IMAGE_JPEG_VALUE:
                case MediaType.IMAGE_PNG_VALUE:
                    break;
                default:
                    throw new UnsupportedFileTypeException("Uploaded file must be a PDF, JPEG or PNG.");
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

}
