package ru.vsu.cs.tech.bookshop.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.tech.bookshop.models.Order;
import ru.vsu.cs.tech.bookshop.repositories.OrderRepository;
import ru.vsu.cs.tech.bookshop.services.OrderService;

import java.io.Serializable;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getOrderById(id));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @GetMapping("/orders/customer/{surname}")
    public List<Order> getOrdersBySurname(@PathVariable String surname) {
        return service.getOrdersByCustomer(surname);
    }

    @GetMapping("/orders/status/{status}")
    public List<Order> getOrdersByStatus(@PathVariable String status) {
        return service.getOrdersByStatus(status);
    }

    @PostMapping("/orders/create")
    public Order createOrder(@RequestBody Order order) {
        return service.addOrder(order);
    }

    @PutMapping("/orders/{id}/update")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        try {
            return ResponseEntity.ok(service.updateExistingOrder(id, order));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @DeleteMapping("/orders/{id}/{delete}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.deleteExistingOrder(id));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }

    }
}
