package ru.vsu.cs.tech.bookshop.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.tech.bookshop.dto.OrderDto;
import ru.vsu.cs.tech.bookshop.models.Order;
import ru.vsu.cs.tech.bookshop.services.OrderService;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/orders")
    public List<OrderDto> getAllOrders() {
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
    public List<OrderDto> getOrdersBySurname(@PathVariable String surname) {
        return service.getOrdersByCustomer(surname);
    }

    @GetMapping("/orders/status/{status}")
    public List<OrderDto> getOrdersByStatus(@PathVariable String status) {
        return service.getOrdersByStatus(status);
    }

    @PostMapping("/orders/create")
    public OrderDto createOrder(@RequestBody OrderDto order) {
        return service.addOrder(order);
    }

    @PutMapping("/orders/{id}/update")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrderDto order) {
        try {
            return ResponseEntity.ok(service.updateExistingOrder(id, order));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @DeleteMapping("/orders/{id}/delete")
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
