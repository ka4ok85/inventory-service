package com.example.service;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.NotFoundException;
import com.example.entity.Product;
import com.example.entity.Productinstore;
import com.example.entity.Store;
import com.example.repository.ProductRepository;
import com.example.repository.StoreRepository;

@ComponentScan
@Service
public class Productstore {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    public Productstore() {
    }

    public Productinstore scanProduct(Long productId, Long storeId, int quantity) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }

        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        Boolean productinstoreExists = false;
        Productinstore existedProductinstore = new Productinstore();
        Set<Productinstore> productinstores = product.getProductinstores();
        for (Productinstore productinstore : productinstores) {
            if (productinstore.getStore().getId().equals(storeId) == true) {
                productinstoreExists = true;
                existedProductinstore = productinstore;
                break;
            }
        }

        Productinstore newProductinstore = new Productinstore();
        if (productinstoreExists == false) {
            newProductinstore.setProduct(product);
            newProductinstore.setStore(store);
            newProductinstore.setQuantity(quantity);
        } else {
            existedProductinstore.setQuantity(existedProductinstore.getQuantity() + quantity);
            newProductinstore = existedProductinstore;
        }


        product.setQuantity(quantity + product.getQuantity());
        product.getProductinstores().add(newProductinstore);
        productRepository.save(product);

        return newProductinstore;
    }

    public Product sellProduct(Long productId, Long storeId, int quantity) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }

        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        if ((product.getQuantity() - quantity) < 0) {
            throw new NotFoundException("Can not sell " + quantity + " items");
        }

        Productinstore existedProductinstore = new Productinstore();
        Set<Productinstore> productinstores = product.getProductinstores();
        for (Productinstore productinstore : productinstores) {
            if (productinstore.getStore().getId().equals(storeId) == true) {
                if ((productinstore.getQuantity() - quantity) < 0) {
                    throw new NotFoundException("Can not sell " + quantity + " items");
                }

                existedProductinstore = productinstore;
                existedProductinstore.setQuantity(existedProductinstore.getQuantity() - quantity);
                break;
            }
        }

        product.setQuantity(product.getQuantity() - quantity);
        product.getProductinstores().add(existedProductinstore);
        productRepository.save(product);

        return product;
    }
}
