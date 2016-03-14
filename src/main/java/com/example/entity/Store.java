package com.example.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "stores")
public class Store implements Persistable<Long> {

    private static final long serialVersionUID = -3349982366942999969L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @JsonView(com.example.entity.Store.class)
    @Column(name = "name", unique = false, nullable = false, length = 255)
    private String name;

    @JsonView(com.example.entity.Store.class)
    @Column(name = "address", unique = false, nullable = false, length = 255)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.store", cascade={CascadeType.PERSIST, CascadeType.MERGE})
    Set<Productinstore> productinstores = new HashSet<Productinstore>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.store", cascade={CascadeType.PERSIST, CascadeType.MERGE})
    Set<Productlocation> productlocationes = new HashSet<Productlocation>();

    public Store() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Productinstore> getProductinstores() {
        return productinstores;
    }

    public void setProductinstores(Set<Productinstore> productinstores) {
        this.productinstores = productinstores;
    }

    public Set<Productlocation> getProductlocationes() {
        return productlocationes;
    }

    public void setProductlocationes(Set<Productlocation> productlocationes) {
        this.productlocationes = productlocationes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Store [name=" + name + ", address=" + address + "]";
    }

    @Override
    public Long getId() {
        return id;
    }

    protected void setId(final Long id) {
        this.id = id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return null == getId();
    }

}
