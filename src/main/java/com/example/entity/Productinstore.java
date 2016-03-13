package com.example.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "products_in_stores")
@AssociationOverrides({ @AssociationOverride(name = "pk.product", joinColumns = @JoinColumn(name = "product_id") ),
@AssociationOverride(name = "pk.store", joinColumns = @JoinColumn(name = "store_id") ) })
public class Productinstore {

    private ProductinstoreID pk = new ProductinstoreID();

    @JsonView(com.example.entity.Productinstore.class)
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @EmbeddedId
    public ProductinstoreID getPk() {
        return pk;
    }

    public void setPk(ProductinstoreID pk) {
        this.pk = pk;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Transient
    public Store getStore() {
        return getPk().getStore();
    }

    public void setStore(Store store) {
        getPk().setStore(store);
    }

    @Transient
    public Product getProduct() {
        return getPk().getProduct();
    }

    public void setProduct(Product product) {
        getPk().setProduct(product);
    }

    @Override
    public String toString() {
        return "Productinstore [quantity=" + quantity + "]";
    }


}
