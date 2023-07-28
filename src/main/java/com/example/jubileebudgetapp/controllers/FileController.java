package com.example.jubileebudgetapp.controllers;

import com.example.jubileebudgetapp.models.File;
import com.example.jubileebudgetapp.repositories.FileRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

/*    @GetMapping("downloadFromDB/{filename}")
    ResponseEntity<byte[]> downloadSingleFile(@PathVariable String filename){


    }*/

}
