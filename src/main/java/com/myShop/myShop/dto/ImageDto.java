package com.myShop.myShop.dto;

import lombok.Data;

@Data
public class ImageDto {
    private Long id;
    private String name;
    private String imageUrl;
    private String downloadUrl;
}
