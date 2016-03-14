package com.example.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "product_locations")
@AssociationOverrides({ @AssociationOverride(name = "pk.product", joinColumns = @JoinColumn(name = "product_id") ),
@AssociationOverride(name = "pk.store", joinColumns = @JoinColumn(name = "store_id") ) })
public class Productlocation {

    private ProductlocationID pk = new ProductlocationID();

    @JsonView(com.example.entity.Productlocation.class)
    @Column(name = "shelf", nullable = false)
    private int shelf;

    @JsonView(com.example.entity.Productlocation.class)
    @Column(name = "slot", nullable = false)
    private int slot;

    @JsonView(com.example.entity.Productlocation.class)
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @EmbeddedId
    public ProductlocationID getPk() {
        return pk;
    }

    public void setPk(ProductlocationID pk) {
        this.pk = pk;
    }

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
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
        return "Productlocation [shelf=" + shelf + ", slot=" + slot + ", quantity=" + quantity + "]";
    }

}