package com.example.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.entity.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}

