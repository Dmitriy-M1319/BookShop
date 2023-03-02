package ru.vsu.cs.tech.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.tech.bookshop.models.Order;
import ru.vsu.cs.tech.bookshop.repositories.OrderRepository;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Такого заказа не существует"));
    }

    @GetMapping("/orders/customer/{surname}")
    public List<Order> getOrdersBySurname(@PathVariable String surname) {
        return repository.findOrdersByCustomerSurname(surname);
    }

    @GetMapping("/orders/status/{status}")
    public List<Order> getOrdersByStatus(@PathVariable String status) {
        return repository.findOrdersByStatus(status);
    }

    @PostMapping("/orders/create")
    public Order createOrder(@RequestBody Order order) {
        return repository.save(order);
    }

    @PutMapping("/orders/{id}/update")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) throws Exception {
        return repository.findById(id).map(o -> {
            o.setCustomerSurname(order.getCustomerSurname());
            o.setPhoneNumber(order.getPhoneNumber());
            o.setEmail(order.getEmail());
            o.setStatus(order.getStatus());
            return repository.save(o);
        }).orElseThrow(() -> new Exception("Такого заказа не существует"));
    }

    @DeleteMapping("/orders/{id}/{delete}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) throws Exception{
        return repository.findById(id).map(c -> {
            repository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new Exception("Такого заказа не существует"));
    }
}
