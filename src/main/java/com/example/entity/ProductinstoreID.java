package com.example.entity;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

@Embeddable
public class ProductinstoreID implements Persistable<Long> {
    private static final long serialVersionUID = 9036301906172917822L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Product product;
    private Store store;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @ManyToOne
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "ProductinstoreID [id=" + id + ", product=" + product + ", store=" + store + "]";
    }

    @Override
    @Transient
    public boolean isNew() {
        return null == getId();
    }
}