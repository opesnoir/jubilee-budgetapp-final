package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.dtos.UploadDto;
import com.example.jubileebudgetapp.services.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/uploads")
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping
    public ResponseEntity<UploadDto> uploadFile(@RequestParam("file")MultipartFile file, @RequestParam("accountId") Long accountId){
        UploadDto uploadedFileDto = uploadService.uploadFile(file, accountId);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(uploadedFileDto.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(uploadedFileDto);
    }


}
