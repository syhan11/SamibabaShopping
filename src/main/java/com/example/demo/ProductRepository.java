package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByName(String name);
    Product findById(long id);

    ArrayList<Product> findAllByNameContainingIgnoreCase(Product product);
    ArrayList<Product>findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String searchstring, String str2);


    Iterable<Product> findAllByCategory(String category);

}
