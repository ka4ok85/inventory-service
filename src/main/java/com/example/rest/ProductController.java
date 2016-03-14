package com.example.rest;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.example.entity.Productinstore;
import com.example.entity.Productlocation;
import com.example.service.Productlocationservice;
import com.example.service.Productstore;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ProductController {

    @Autowired
    private Productstore productstore;

    @Autowired
    private Productlocationservice productlocationservice;

    @RequestMapping(value = "/api/sellProduct/{productId}/{storeId}/{shelf}/{slot}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Productinstore.class)
    @Transactional
    public Boolean sellProduct(@PathVariable("productId") Long productId, @PathVariable("storeId") Long storeId, @PathVariable("shelf") int shelf, @PathVariable("slot") int slot) {
        productlocationservice.sellProduct(productId, storeId, shelf, slot);
        productstore.sellProduct(productId, storeId, 1);

        return true;
    }

    @RequestMapping(value = "/api/scanProduct/{productId}/{storeId}/{quantity}/{shelf}/{slot}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Productinstore.class)
    @Transactional
    public Productinstore scanProduct(@PathVariable("productId") Long productId, @PathVariable("storeId") Long storeId, @PathVariable("quantity") int quantity, @PathVariable("shelf") int shelf, @PathVariable("slot") int slot) {
        Productinstore newProductinstore = productstore.scanProduct(productId, storeId, quantity);
        Productlocation newProductlocation = productlocationservice.scanProduct(productId, storeId, quantity, shelf, slot);

        return newProductinstore;
    }

}
