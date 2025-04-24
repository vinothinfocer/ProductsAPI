package com.api.products.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Table(name="product", uniqueConstraints = @UniqueConstraint(
        name = "productName",
        columnNames = "name"
))
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String category;
    private double price;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name="product_id",
            referencedColumnName = "id"
    )
    private List<Review> review = new ArrayList<>();

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name="product_tags",
            joinColumns = @JoinColumn(
                    name="product_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name="tag_id",
                    referencedColumnName = "tagId"
            )
    )
    private List<Tag> tags = new ArrayList<>();



}
