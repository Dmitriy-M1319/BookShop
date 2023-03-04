package ru.vsu.cs.tech.bookshop.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vsu.cs.tech.bookshop.models.Order;
import ru.vsu.cs.tech.bookshop.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> getAllOrders() {
       return repository.findAll();
    }

    public Order getOrderById(Long id) throws IllegalArgumentException {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Такого заказа не существует"));
    }

    public List<Order> getOrdersByCustomer(String surname) {
        return repository.findOrdersByCustomerSurname(surname);
    }

    public List<Order> getOrdersByStatus(String status) {
        return repository.findOrdersByStatus(status);
    }

    public Order addOrder(Order newOrder) {
        return repository.save(newOrder);
    }

    public Order updateExistingOrder(Long id, Order order) throws IllegalArgumentException {
        return repository.findById(id).map(o -> {
            o.setCustomerSurname(order.getCustomerSurname());
            o.setPhoneNumber(order.getPhoneNumber());
            o.setEmail(order.getEmail());
            o.setStatus(order.getStatus());
            return repository.save(o);
        }).orElseThrow(() -> new IllegalArgumentException("Такого заказа не существует"));
    }

    public ResponseEntity<?> deleteExistingOrder(Long id) throws IllegalArgumentException {
        return repository.findById(id).map(c -> {
            repository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("Такого заказа не существует"));
    }
}
