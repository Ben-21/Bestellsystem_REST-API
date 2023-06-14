package com.example.bestellsystem_restapi;


import com.example.bestellsystem_restapi.model.Order;
import com.example.bestellsystem_restapi.model.OrderRepo;
import com.example.bestellsystem_restapi.model.Product;
import com.example.bestellsystem_restapi.model.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ShopIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    OrderRepo orderRepo;

    @Test
    @DirtiesContext
    void expectProductList_whenRequestingProductList() throws Exception {
        //GIVEN

        String expected = """
                    [
                        {
                            "id": "1",
                            "name": "Ball"
                        },{
                            "id": "2",
                            "name": "TV"
                        }
                    ]
                        
                        
                """;


        //WHEN
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/products")
                )
                //THEN
                .andExpect(
                        MockMvcResultMatchers.content().json(expected)
                );
    }


    @Test
    @DirtiesContext
    void expectProduct_whenRequestingProduct() throws Exception {
        //GIVEN
        String expected = """
                {
                    "id": "1",
                    "name": "Ball"
                }
                """;

        //WHEN
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/products/1")
                )
                //THEN
                .andExpect(
                        MockMvcResultMatchers.content().json(expected)
                );
    }

    @Test
    @DirtiesContext
    void expectOrder_whenRequestingOrders() throws Exception {
        //GIVEN
        Product ball = new Product("1", "Ball");
        Product tv = new Product("2", "TV");
        List<Product> products = new ArrayList<>();
        products.add(ball);
        products.add(tv);

        Order order1 = new Order ("1", products);
//      Order order2 = new Order ("2", products);


        orderRepo.add(order1);
        orderRepo.add(new Order("2", List.of(ball, tv)));


        String orderListExpected = """
                [
                    {
                        "id": "1",
                        "products": [
                            {
                                "id": "1",
                                "name": "Ball"
                            },
                            {
                                "id": "2",
                                "name": "TV"
                            }
                        ]
                    },
                    {
                        "id": "2",
                        "products": [
                            {
                                "id": "1",
                                "name": "Ball"
                            },
                            {
                                "id": "2",
                                "name": "TV"
                            }
                        ]
                    }
                ]
                """;

//WHEN
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/orders")
                )
                //THEN
                .andExpect(
                        MockMvcResultMatchers.content().json(orderListExpected)
                );
    }


}
