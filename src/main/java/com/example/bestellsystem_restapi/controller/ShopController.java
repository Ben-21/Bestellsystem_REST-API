package com.example.bestellsystem_restapi.controller;


import com.example.bestellsystem_restapi.model.Order;
import com.example.bestellsystem_restapi.model.Product;
import com.example.bestellsystem_restapi.model.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShopController {
    //FIELDS
    private ShopService shopService;


    //CONSTRUCTOR
    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }




    //ENDPOINTS
    @GetMapping("/products")
    public List<Product> getProducts() {
        return shopService.listProducts();
    }


    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable String id) {
        return shopService.getProduct(id);
    }


    @GetMapping("/orders")
    public List<Order> getOrders() {
        return shopService.listOrder();
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable String id) {
        return shopService.getOrder(id);
    }


    @PostMapping("/orders/{id}")
    public Order addOrders(@PathVariable String id, @RequestBody List<Product> products) {

        shopService.addOrderAndId(products, id);
        return shopService.getOrder(id);
    }

    @PostMapping("/ordersbybody")
    public Order addOrdersByBody(@RequestBody Order order) {

        shopService.addOrderAndId(order.getProducts(), order.getId());
        return shopService.getOrder(order.getId());
    }


    @PostMapping("/ordersbybodystring")
    public String addOrdersByBodyString(@RequestBody String name) {

        return "That is your " + name;
    }

    @GetMapping("/paramtest")
    public String hello(@RequestParam String name, int age) {
        return "Hello " + name + " your age is: " + age;

    }


}
