package com.api.products.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="review")
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;
    private String Comment;
    private String reviewerName;

//    @ManyToOne(
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            optional = false
//    )
////    @JoinColumn(name="product_id")
//    private Product productId;
}
