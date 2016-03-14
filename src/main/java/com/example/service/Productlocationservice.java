package com.example.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.NotFoundException;
import com.example.entity.Product;
import com.example.entity.Productinstore;
import com.example.entity.Productlocation;
import com.example.entity.Store;
import com.example.repository.ProductRepository;
import com.example.repository.StoreRepository;

@ComponentScan
@Service
public class Productlocationservice {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    public Productlocation scanProduct(Long productId, Long storeId, int quantity, int shelf, int slot) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }

        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        Productlocation newProductinlocation = new Productlocation();
        newProductinlocation.setProduct(product);
        newProductinlocation.setStore(store);
        newProductinlocation.setShelf(shelf);
        newProductinlocation.setSlot(slot);
        newProductinlocation.setQuantity(quantity);

        product.getProductlocationes().add(newProductinlocation);
        productRepository.save(product);

        return newProductinlocation;
    }

    public Boolean sellProduct(Long productId, Long storeId, int shelf, int slot) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }

        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        Boolean productlocationExists = false;
        Productlocation existedProductlocation = new Productlocation();
        Set<Productlocation> productlocationes = product.getProductlocationes();
        for (Productlocation productlocation : productlocationes) {
            if (productlocation.getStore().getId().equals(storeId) == true &&
                productlocation.getShelf() == shelf &&
                productlocation.getSlot() == slot
            ) {

                //Boolean x1 = product.getProductlocationes().remove(product);
                //Boolean x2 = store.getProductlocationes().remove(store);
                //System.out.println(x1);
                //System.out.println(x2);

                //Boolean x3 = product.getProductlocationes().remove(productlocation);
                //Boolean x4 = store.getProductlocationes().remove(productlocation);

                //System.out.println(x3);
                //System.out.println(x4);
                existedProductlocation = productlocation;

                productlocationExists = true;

            }
        }

        if (productlocationExists == false) {
            throw new NotFoundException("Can not sell " + productId + " item");
        }


/*
        Boolean res = productlocationes.remove(existedProductlocation);
        System.out.println(res);
        System.out.println(productlocationes.size());
        product.setProductlocationes(productlocationes);

        Set<Productlocation> productlocationes2 = store.getProductlocationes();
        Boolean res2 = productlocationes2.remove(existedProductlocation);
        System.out.println(res2);
        System.out.println(productlocationes2.size());
        store.setProductlocationes(productlocationes2);
*/
        Boolean x3 = product.getProductlocationes().remove(existedProductlocation);
        System.out.println(x3);
        productRepository.save(product);
        //load.getSessions().remove(session);
        //session.getLoads().remove(load);
        //productRepository.
        return true;
    }
}
