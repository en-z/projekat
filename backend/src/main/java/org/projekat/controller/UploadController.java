package org.projekat.controller;

import org.projekat.service.UploadXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadXmlService uploadXmlService;

    @PostMapping("/xml-file")
    public CompletableFuture<ResponseEntity<?>> uploadXmlFile(@RequestParam("file") MultipartFile file) {
        try {
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
            return uploadXmlService.uploadXmlFromString(content);
        } catch (IOException e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("File read error: " + e.getMessage()));
        }
    }

    @PostMapping("/xml-string")
    public CompletableFuture<ResponseEntity<?>> uploadXmlString(@RequestBody String xmlContent) {
        return uploadXmlService.uploadXmlFromString(xmlContent);
    }
}
