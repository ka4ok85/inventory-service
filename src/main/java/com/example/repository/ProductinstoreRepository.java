package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Productinstore;

@Repository
public interface ProductinstoreRepository extends CrudRepository<Productinstore, Long> {

}

