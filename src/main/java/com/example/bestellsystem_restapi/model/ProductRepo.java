package com.example.bestellsystem_restapi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Repository
public class ProductRepo {


    //FIELDS
    Product ball = new Product("1", "Ball");
    Product tv = new Product("2", "TV");

//    private List<Product> products = new ArrayList<>();
//    @Autowired
    private List<Product> products;





    //CONSTRUCTOR
    public ProductRepo() {
        this.products = new ArrayList<>();
        this.products.add(ball);
        this.products.add(tv);
    }


    //METHODS
    public Product getByName(String name) {
        Product foundProduct = null;

        for (Product product : products) {
            if (product.getName().equals(name)) {
                foundProduct = product;
                break;
            }
        }
        if (foundProduct == null) {
            System.out.println("Product not found");
        }
        return foundProduct;
    }


    public Product getById(String id) {
        Product foundProduct = null;

        for (Product product : products) {
            if (product.getId().equals(id)) {
                foundProduct = product;
                break;
            }
        }
        if (foundProduct == null) {
            System.out.println("Product not found");
        }
        return foundProduct;
    }


    public List<Product> list() {
        return products;
    }


    //FIELD OPERATIONS
    @Override
    public String toString() {
        return "ProductRepo{" +
                "products=" + products +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRepo that = (ProductRepo) o;
        return Objects.equals(products, that.products);
    }


    @Override
    public int hashCode() {
        return Objects.hash(products);
    }
}
