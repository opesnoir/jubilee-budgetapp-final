package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.UploadDto;
import com.example.jubileebudgetapp.exceptions.AccountIdNotFoundException;
import com.example.jubileebudgetapp.exceptions.UploadFileNotFoundException;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.Upload;
import com.example.jubileebudgetapp.repositories.AccountRepository;
import com.example.jubileebudgetapp.repositories.UploadRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class UploadService {

    private final UploadRepository uploadRepository;
    private final AccountRepository accountRepository;

    public UploadService(UploadRepository uploadRepository, AccountRepository accountRepository) {
        this.uploadRepository = uploadRepository;
        this.accountRepository = accountRepository;
    }

    public UploadDto uploadFile(MultipartFile file, Long accountId){
        try{
            Account account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new AccountIdNotFoundException(accountId));

            byte[] fileBytes = file.getBytes();
            String fileName = file.getOriginalFilename();

            Upload upload = convertDtoToUpload(createUploadDto(fileBytes, fileName, account));
            Upload savedUpload = uploadRepository.save(upload);
            UploadDto uploadDto = convertUploadToDto(savedUpload);

            return uploadDto;
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    public Resource downloadFile(Long id) throws UploadFileNotFoundException{
        Upload upload = uploadRepository.findById(id)
                .orElseThrow(() -> new UploadFileNotFoundException(id));

        byte[] fileBytes = upload.getUpload();
        String fileName = upload.getFileName();

        return new ByteArrayResource(fileBytes) {
            @Override
            public String getFilename() {
                return fileName;
            }
        };
    }

    public UploadDto deleteFile(Long id) throws FileNotFoundException{
        Upload upload = uploadRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File not found with ID: " + id));

        uploadRepository.delete(upload);
        return convertUploadToDto(upload);
    }

    //helper methodes
    public Upload convertDtoToUpload(UploadDto uploadDto){
        Upload upload = new Upload();

        upload.setUpload(uploadDto.getUpload());
        upload.setFileName(uploadDto.getFileName());

        return upload;
    }

    public UploadDto convertUploadToDto(Upload upload){
        UploadDto uploadDto = new UploadDto();

        uploadDto.setId(upload.getId());
        uploadDto.setUpload(upload.getUpload());
        uploadDto.setFileName(upload.getFileName());

        uploadDto.setAccountId(upload.getAccount().getId());

        return uploadDto;
    }

    private UploadDto createUploadDto(byte[] fileBytes, String fileName, Account account){
        UploadDto uploadDto = new UploadDto();

        uploadDto.setUpload(fileBytes);
        uploadDto.setFileName(fileName);
        uploadDto.setAccountId(account.getId());

        return uploadDto;
    }
}
