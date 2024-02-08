package com.imgpersist.persistenciaimg.controller;


import com.imgpersist.persistenciaimg.entity.ImageData;
import com.imgpersist.persistenciaimg.service.ImageDataService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageDataController {

    @Autowired
    private ImageDataService imageDataService;


@PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {

       String uploadImage= imageDataService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("/{file}")
    public ResponseEntity<?> downloadImage(@PathVariable("file")  String fileName) throws IOException {

        byte[] image = imageDataService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image); // debería ser dinamico y saber el tipo de archivo que se sube para cambiarlo dinamicamente
    }

}