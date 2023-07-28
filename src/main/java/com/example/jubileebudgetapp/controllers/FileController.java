package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.exceptions.UploadFileNotFoundException;
import com.example.jubileebudgetapp.models.File;
import com.example.jubileebudgetapp.repositories.FileRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class FileController {

    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @PostMapping("/single/uploadDB")
    public ResponseEntity<String> singleFileUpload(@RequestParam("file")MultipartFile file) throws IOException {
        File uploadfile = new File();
        uploadfile.setFilename("new file");
        uploadfile.setDocFile(file.getBytes());

        fileRepository.save(uploadfile);

        return ResponseEntity.ok("file is uploaded");
    }

    @GetMapping("/downloadFromDb/{fileId}")
    public ResponseEntity<byte[]> downloadSingleFile(@PathVariable Long fileId) {
        File file = fileRepository.findById(fileId)
                .orElseThrow(RuntimeException::new);
        byte[] docFile = file.getDocFile();

        if (docFile == null) {
            throw new RuntimeException("there is no file yet.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentDispositionFormData("attachment", "file" + file.getFilename() + ".jpeg");
        headers.setContentLength(docFile.length);

        return new ResponseEntity<>(docFile, headers, HttpStatus.OK);
    }

}
