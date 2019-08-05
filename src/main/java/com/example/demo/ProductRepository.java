package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByName(String name);


    ArrayList<Product> findAllByNameContainingIgnoreCase(Product product);



    Iterable<Product> findAllByCategory(String category);

}
