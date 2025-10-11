package com.reykel.orderservice.orderservice.service;

import com.reykel.orderservice.orderservice.model.Order;
import com.reykel.orderservice.orderservice.repository.OrderRepository;
import com.reykel.orderservice.orderservice.vo.Pelanggan;
import com.reykel.orderservice.orderservice.vo.Produk;
import com.reykel.orderservice.orderservice.vo.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrUpdateOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    public ResponseTemplate getOrderWithProdukById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            return null; // atau throw exception
        }

        Order order = optionalOrder.get();

        // PRODUK service
        ServiceInstance produkInstance = discoveryClient.getInstances("PRODUK").get(0);
        Produk produk = restTemplate.getForObject(
                produkInstance.getUri() + "/api/produk/" + order.getProdukId(),
                Produk.class
        );

        // PELANGGAN service
        ServiceInstance pelangganInstance = discoveryClient.getInstances("PELANGGAN").get(0);
        Pelanggan pelanggan = restTemplate.getForObject(
                pelangganInstance.getUri() + "/api/pelanggan/" + order.getPelangganId(),
                Pelanggan.class
        );

        ResponseTemplate vo = new ResponseTemplate();
        vo.setOrder(order);
        vo.setProduk(produk);
        vo.setPelanggan(pelanggan);

        return vo;
    }
}
