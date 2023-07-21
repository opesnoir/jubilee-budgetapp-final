package com.example.jubileebudgetapp.services;

import com.example.jubileebudgetapp.dtos.UploadDto;
import com.example.jubileebudgetapp.models.Account;
import com.example.jubileebudgetapp.models.Upload;
import org.springframework.stereotype.Service;

@Service
public class UploadService {

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
