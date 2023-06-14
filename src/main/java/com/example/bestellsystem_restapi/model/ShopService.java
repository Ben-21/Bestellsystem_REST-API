package com.example.bestellsystem_restapi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
public class ShopService {


    //FIELDS
    private ProductRepo productRepo;
    private OrderRepo orderRepo;

    @Autowired
    //CONSTRUCTOR
    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }


    //METHODS
    public Product getProduct(String id) {

        return productRepo.getById(id);
    }


    public List<Product> listProducts() {
        return productRepo.list();
    }


    public Order getOrder(String id) {
        return orderRepo.getById(id);
    }


    public List<Order> listOrder() {
        return orderRepo.list();
    }


    //add order with existing list of products
    public void addOrder(List<Product> productList) {

        //get size of existing orderList
        int sizeOfOrderRepo = orderRepo.list().size();
        //create new index for new order
        int calculatedIndex = sizeOfOrderRepo + 1;
        //create new order ID
        String newOrderId = "OR" + "2023" + "-" + calculatedIndex;

        //create new order
        Order newOrder = new Order(newOrderId, productList);
        //add new order to repo
        orderRepo.add(newOrder);
    }
 public void addOrderAndId(List<Product> productList, String id) {

        //get size of existing orderList
//        int sizeOfOrderRepo = orderRepo.list().size();
        //create new index for new order
//        String calculatedIndex = id;
        //create new order ID
//        String newOrderId = id;

        //create new order
        Order newOrder = new Order(id, productList);
        //add new order to repo
        orderRepo.add(newOrder);
    }

    //add order by creating product list through console inputs
    public void addOrderWithUserInput() {
        List<Product> productList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        //get size of existing order
        int sizeOfOrderRepo = orderRepo.list().size();
        //create new index for new order
        int calculateIndex = sizeOfOrderRepo + 1;
        //create new order ID
        String newOrderId = "OR" + "2023" + "-" + calculateIndex;
        String goOn = "y";

        System.out.println("Available Products: ");
        System.out.println();
        System.out.println(productRepo.list());
        System.out.println();
        System.out.println("Please type the product ID you want to add.");
        String productIdToAdd = scanner.nextLine();
        productList.add(productRepo.getById(productIdToAdd));

        while (goOn.equals("y")) {
            System.out.println();
            System.out.println("Do you want to add another Product? Type \"y\" for yes and \"n\" for no.");
            goOn = scanner.nextLine();
            if (goOn.equals("y")) {
                System.out.println("Please type the product ID you want to add.");
                productIdToAdd = scanner.nextLine();
                productList.add(productRepo.getById(productIdToAdd));
            }
            if (goOn.equals("n")) {
                break;
            }
        }


        Order newOrder = new Order(newOrderId, productList);
        orderRepo.add(newOrder);

        System.out.println("New order: ");
        System.out.println();
        System.out.println(newOrder);
    }


}
