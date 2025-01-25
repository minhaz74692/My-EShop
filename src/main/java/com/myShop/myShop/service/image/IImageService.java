package com.myShop.myShop.service.image;

import com.myShop.myShop.dto.ImageDto;
import com.myShop.myShop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> file, Long productId);
    void updateImage(MultipartFile files, Long imageId);
}
