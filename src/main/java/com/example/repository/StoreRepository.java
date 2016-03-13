package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Store;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {

}

