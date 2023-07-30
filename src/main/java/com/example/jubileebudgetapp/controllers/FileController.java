package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.dtos.FileDto;
import com.example.jubileebudgetapp.dtos.UploadDto;
import com.example.jubileebudgetapp.exceptions.UploadedFileNotFoundException;
import com.example.jubileebudgetapp.services.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.net.URI;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/single/upload")
    public ResponseEntity<FileDto> uploadFile(@RequestParam("file")MultipartFile file){
        FileDto uploadedFileDto = fileService.uploadFile(file);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(uploadedFileDto.getId())
                .toUriString());

        return ResponseEntity.created(uri)
                .body(uploadedFileDto);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") Long fileId) throws UploadedFileNotFoundException{
        Resource fileResource = fileService.downloadFile(fileId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileResource.getFilename());
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileResource);
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<UploadDto> deleteFile(@PathVariable("fileId") Long id) throws FileNotFoundException {
        fileService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/{accountId}")
    public ResponseEntity<Object> assignFileToAccount(@PathVariable("id") Long id, @PathVariable("accountId") Long accountId){
        fileService.assignFileToAccount(id, accountId);
        return ResponseEntity.noContent().build();
    }





}
