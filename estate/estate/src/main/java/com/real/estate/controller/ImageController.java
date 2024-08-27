package com.real.estate.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.real.estate.entity.Images;
import com.real.estate.service.ImagesService;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImagesService imagesServiceRef;

    @PostMapping("/upload/{userId}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile[] image, @PathVariable Integer userId) throws IOException {
        try {
            return new ResponseEntity<>(imagesServiceRef.saveImage(image, userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to upload images: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByUser/{userId}")
    public ResponseEntity<?> getImagesByUser(@PathVariable Integer userId) {
        try {
            List<Images> imagesData = imagesServiceRef.getImagesDataByUserId(userId);
            if (imagesData.isEmpty()) {
                return new ResponseEntity<>("No images found for user ID: " + userId, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(imagesData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve images: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/uploadImagePg/{propertyId}")
    public ResponseEntity<?> uploadImageForProperty(@RequestParam("image") MultipartFile[] image, @PathVariable Integer propertyId) throws IOException {
        try {
            return new ResponseEntity<>(imagesServiceRef.saveImageForPg(image, propertyId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to upload images: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByProperty/{propertyId}")
    public ResponseEntity<?> getImagesByProperty(@PathVariable Integer propertyId) {
        try {
            List<Images> imagesData = imagesServiceRef.getImagesDataByPropertyId(propertyId);
            if (imagesData.isEmpty()) {
                return new ResponseEntity<>("No images found for property ID: " + propertyId, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(imagesData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve images: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable Integer id) {
        try {
            Images image = imagesServiceRef.getImageById(id);
            if (image == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            InputStream inputStream = new ByteArrayInputStream(image.getImagedata());
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "image/jpeg"); // Adjust if necessary
            return new ResponseEntity<>(new InputStreamResource(inputStream), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Integer id) {
        try {
            return imagesServiceRef.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete image: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Images>> getAllImages() {
        List<Images> images = imagesServiceRef.findAll();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }
}
