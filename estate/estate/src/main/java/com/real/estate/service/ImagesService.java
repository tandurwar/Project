package com.real.estate.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.real.estate.customException.UserException;
import com.real.estate.entity.Images;
import com.real.estate.entity.Properties;
import com.real.estate.entity.User;
import com.real.estate.jparepo.ImagesRepo;

@Service
public class ImagesService {

    @Autowired
    private ImagesRepo imagesRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private PropertiesService propertiesService;

    public List<Images> saveImage(MultipartFile[] imageFiles, Integer userId) throws IOException {
        Set<Images> images = new HashSet<>();
        for (MultipartFile img : imageFiles) {
            Images image = new Images();
            image.setImagedata(img.getBytes());
            User userRef = userService.fetchById(userId);
            image.setUser(userRef);
            images.add(image);
        }
        List<Images> savedImages = imagesRepo.saveAll(images);
        if (savedImages != null) {
            return savedImages;
        } else {
            throw new UserException("Unable to save images, try again later");
        }
    }

    public List<Images> saveImageForPg(MultipartFile[] imageFiles, Integer propertyId) throws IOException {
        Set<Images> images = new HashSet<>();
        for (MultipartFile img : imageFiles) {
            Images image = new Images();
            image.setImagedata(img.getBytes());
            Properties propertyRef = propertiesService.fetchById(propertyId);
            image.setProperties(propertyRef);
            images.add(image);
        }
        List<Images> savedImages = imagesRepo.saveAll(images);
        if (savedImages != null) {
            return savedImages;
        } else {
            throw new UserException("Unable to save images, try again later");
        }
    }

    public List<Images> getImagesDataByUserId(Integer userId) {
        List<Images> images = imagesRepo.findByUser_UserId(userId);
        if (images.isEmpty()) {
            throw new UserException("No images found for user ID: " + userId);
        }
        return images;
    }

    public List<Images> getImagesDataByPropertyId(Integer propertyId) {
        List<Images> images = imagesRepo.findByPropertiesPropertyId(propertyId);
        if (images.isEmpty()) {
            throw new UserException("No images found for property ID: " + propertyId);
        }
        return images;
    }
    
    public Images getImageById(Integer imageId) {
        return imagesRepo.findById(imageId).orElseThrow();
    }

    public ResponseEntity<String> delete(Integer id) {
        Optional<Images> img = imagesRepo.findById(id);
        if (img.isPresent()) {
            imagesRepo.deleteById(id);
            return new ResponseEntity<>("Image deleted successfully", HttpStatus.OK);
        } else {
            throw new UserException("Image not found");
        }
    }
    
    public List<Images> findAll() {
        return imagesRepo.findAll();
    }
}
