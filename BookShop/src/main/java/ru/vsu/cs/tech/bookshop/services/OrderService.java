package ru.vsu.cs.tech.bookshop.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vsu.cs.tech.bookshop.dto.OrderDto;
import ru.vsu.cs.tech.bookshop.mappers.OrderMapper;
import ru.vsu.cs.tech.bookshop.models.Order;
import ru.vsu.cs.tech.bookshop.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

    public OrderService(OrderMapper mapper, OrderRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<OrderDto> getAllOrders() {
       return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Order getOrderModelById(Long id) throws IllegalArgumentException {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Такого заказа не существует"));
    }

    public OrderDto getOrderById(Long id) throws IllegalArgumentException {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Такого заказа не существует"));
    }

    public List<OrderDto> getOrdersByCustomer(String surname) {
        return repository.findOrdersByCustomerSurname(surname).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public List<OrderDto> getOrdersByStatus(String status) {
        return repository.findOrdersByStatus(status).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public OrderDto addOrder(OrderDto newOrder) {
        return mapper.toDto(repository.save(mapper.toModel(newOrder)));
    }

    public OrderDto updateExistingOrder(Long id, OrderDto order) throws IllegalArgumentException {
        return repository.findById(id).map(o -> {
            o.setCustomerSurname(order.getCustomerSurname());
            o.setPhoneNumber(order.getPhoneNumber());
            o.setEmail(order.getEmail());
            o.setStatus(order.getStatus());
            return mapper.toDto(repository.save(o));
        }).orElseThrow(() -> new IllegalArgumentException("Такого заказа не существует"));
    }

    public ResponseEntity<?> deleteExistingOrder(Long id) throws IllegalArgumentException {
        return repository.findById(id).map(c -> {
            repository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("Такого заказа не существует"));
    }
}
