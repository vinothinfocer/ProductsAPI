package com.api.products.dto;

import com.api.products.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int id;
    private String name;
    private String category;
    private double price;
    private List<ReviewDto> review = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
}
