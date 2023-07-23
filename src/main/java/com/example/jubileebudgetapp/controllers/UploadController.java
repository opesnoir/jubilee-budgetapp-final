package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.dtos.UploadDto;
import com.example.jubileebudgetapp.exceptions.UploadFileNotFoundException;
import com.example.jubileebudgetapp.services.UploadService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
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

        return ResponseEntity.created(uri)
                .body(uploadedFileDto);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long id) throws UploadFileNotFoundException {
        Resource fileResource = uploadService.downloadFile(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileResource.getFilename());

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UploadDto> deleteFile(@PathVariable("id") Long id) throws FileNotFoundException {
        UploadDto deletedFileDto = uploadService.deleteFile(id);
        return ResponseEntity.ok(deletedFileDto);
    }

}
