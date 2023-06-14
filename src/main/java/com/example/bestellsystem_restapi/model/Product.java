package com.example.bestellsystem_restapi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;



public class Product {


    //FIELDS
    private String id;
    private String name;


//CONSTRUCTOR
    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }


    //GETTER SETTER
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }


//FIELD OPERATIONS
    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
