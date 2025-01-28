package com.myShop.myShop.service.image;

import com.myShop.myShop.dto.ImageDto;
import com.myShop.myShop.exception.ResourseNotFoundException;
import com.myShop.myShop.model.Image;
import com.myShop.myShop.model.Product;
import com.myShop.myShop.repository.image.ImageRepository;
import com.myShop.myShop.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService{
    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(()-> new ResourseNotFoundException("Image not found"));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository:: delete,()->{
            throw  new ResourseNotFoundException("Image not found");
        });
    }

    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> savedImageDtos = new ArrayList<>();
        for(MultipartFile file: files){
            try{
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl = "/api/v1/images/image/download/";
                String iamgeUrlPrefix = "/api/v1/images/";
                String downloadUrl =  buildDownloadUrl+ image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage =   imageRepository.save(image);
                savedImage.setDownloadUrl(buildDownloadUrl+ savedImage.getId());
                savedImage.setImageUrl(iamgeUrlPrefix + savedImage.getId());
                imageRepository.save(savedImage);
                ImageDto imageDto = new ImageDto();
                imageDto.setId(savedImage.getId());
                imageDto.setName(savedImage.getFileName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());
                imageDto.setImageUrl(savedImage.getImageUrl());

                savedImageDtos.add(imageDto);
            }catch (IOException | SQLException e){
                throw  new RuntimeException(e.getMessage());
            }

        }
        return  savedImageDtos;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try{
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
