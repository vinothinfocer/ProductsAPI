package com.api.products.Service;

import com.api.products.dto.ProductDto;
import com.api.products.dto.ReviewDto;
import com.api.products.entity.Product;
import com.api.products.entity.Review;
import com.api.products.entity.Tag;
import com.api.products.respository.ProductRepository;
import com.api.products.respository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TagRepository tagRepository;

    public ProductDto addProduct(ProductDto productDto) {
        Product product = productRepository.save(convertDtoToEntity(productDto));
        return convertProductEntitytoDto(product);
    }

    private Product convertDtoToEntity(ProductDto productDto) {

        Product dto = new Product();
        dto.setId(productDto.getId());
        dto.setName(productDto.getName());
        dto.setCategory(productDto.getCategory());
        dto.setPrice(productDto.getPrice());
        productDto.getTags().forEach(tag -> {
            Optional<Tag> tagResult = tagRepository.findByTagName(tag);
            Tag newTag = tagResult.orElseGet(() -> {
                Tag t = new Tag();
                t.setTagName(tag);
                return t;
            });
            dto.getTags().add(newTag);
        });
        dto.setReview(productDto.getReview().stream().map(review -> this.convertReviewDtoToEntity(review, productDto.getId())).toList());
        return dto;
    }

    private Review convertReviewDtoToEntity(ReviewDto reviewDto, int productId) {
        Review review = new Review();
        review.setReviewerName(reviewDto.getReviewerName());
        review.setComment(reviewDto.getComment());
//        review.setProductId(productId);
        return review;
    }

    private ProductDto convertProductEntitytoDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setTags(product.getTags().stream().map(Tag::getTagName).toList());
        dto.setReview(product.getReview().stream().map(this::convertReviewEntitytoDto).toList());
        return dto;
    }

    private ReviewDto convertReviewEntitytoDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewerName(review.getReviewerName());
        reviewDto.setComment(review.getComment());
        return reviewDto;
    }
}
