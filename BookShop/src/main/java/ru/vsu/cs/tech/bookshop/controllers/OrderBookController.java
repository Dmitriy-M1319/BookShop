package ru.vsu.cs.tech.bookshop.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.tech.bookshop.dto.OrderBookGetDto;
import ru.vsu.cs.tech.bookshop.dto.OrderBookPostDto;
import ru.vsu.cs.tech.bookshop.models.Order;
import ru.vsu.cs.tech.bookshop.models.OrderBook;
import ru.vsu.cs.tech.bookshop.repositories.OrderBookRepository;
import ru.vsu.cs.tech.bookshop.repositories.OrderRepository;
import ru.vsu.cs.tech.bookshop.services.OrderBookService;

import java.io.Serializable;
import java.util.List;
@RestController
public class OrderBookController {

    @Autowired
    private OrderBookService service;

    @GetMapping("/orders/{id}/books")
    public List<OrderBookGetDto> getBooksByOrder(@PathVariable Long id) {
        return service.getOrderBooksByOrderId(id);
    }

    @PostMapping("/orders/{id}/books/create")
    public ResponseEntity<?> createOrderBook(@PathVariable Long id, @RequestBody OrderBookPostDto orderBook) {
        try {
            return ResponseEntity.ok(service.addOrderBook(orderBook));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @PutMapping("/orders/{id}/books/{bookId}/update")
    public ResponseEntity<?> updateOrderBook(@PathVariable Long bookId, @RequestBody OrderBookPostDto book) {
        try {
            return ResponseEntity.ok(service.updateExistingBook(bookId, book));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @DeleteMapping("/orders/{id}/books/{bookId}/delete")
    public ResponseEntity<?> deleteOrderBook(@PathVariable Long id, @PathVariable Long bookId) {
        try {
            return ResponseEntity.ok(service.deleteExistingBook(bookId));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }
}
