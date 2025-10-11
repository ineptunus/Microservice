package com.reykel.orderservice.orderservice.controller;

import com.reykel.orderservice.orderservice.model.Order;
import com.reykel.orderservice.orderservice.service.OrderService;
import com.reykel.orderservice.orderservice.vo.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/produk/{id}")
    public ResponseTemplate getOrderWithProdukById(@PathVariable Long id) {
        return orderService.getOrderWithProdukById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrUpdateOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }
}
