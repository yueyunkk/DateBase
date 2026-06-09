package com.example.secondhand.entity;

import lombok.Data;

@Data
public class Category {
    private Long categoryId;
    private String categoryName;
    private String categoryDesc;
    private Integer sortNo;
    private Integer status;
}