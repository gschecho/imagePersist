package com.imgpersist.persistenciaimg.service;


import com.imgpersist.persistenciaimg.entity.ImageData;
import com.imgpersist.persistenciaimg.repo.ImageDataRepository;
import com.imgpersist.persistenciaimg.util.ImageUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

    @Autowired
    private ImageDataRepository imageDataRepository;

    @Transactional
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = imageDataRepository.save(ImageData.builder().name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())) //en imageData puede ser file.getBytes() sin comprimir
                .build());

        if (imageData != null) {
            return "subido correctamente" + file.getOriginalFilename();
        }


        return null;
    }

    @Transactional
    public byte[] downloadImage(String name) {

        Optional<ImageData> imageDB = imageDataRepository.findByName(name);
        return ImageUtil.decompressImage(imageDB.get().getImageData());
    }


}