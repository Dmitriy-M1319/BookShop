package ru.vsu.cs.tech.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.tech.bookshop.models.Order;
import ru.vsu.cs.tech.bookshop.models.OrderBook;
import ru.vsu.cs.tech.bookshop.repositories.OrderBookRepository;
import ru.vsu.cs.tech.bookshop.repositories.OrderRepository;

import java.util.List;

@RestController
public class OrderBookController {

    @Autowired
    private OrderBookRepository repository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders/{id}/books")
    public List<OrderBook> getBooksByOrder(@PathVariable Long id) {
        return repository.findOrderBooksByOrderId(id);
    }

    @PostMapping("/orders/{id}/books/create")
    public OrderBook createOrderBook(@PathVariable Long id, @RequestBody OrderBook orderBook) throws Exception{
        Order order = orderRepository.findById(id).orElseThrow(() -> new Exception("Такого заказа не существует"));
        orderBook.setOrder(order);
        return repository.save(orderBook);
    }

    @PutMapping("/orders/{id}/books/{bookId}/update")
    public OrderBook updateOrderBook(@PathVariable Long id, @PathVariable Long bookId, @RequestBody OrderBook book) throws Exception {
        return repository.findById(bookId).map(b -> {
            b.setBookAuthor(book.getBookAuthor());
            b.setBookTag(book.getBookTag());
            b.setBooksCount(book.getBooksCount());
            return repository.save(b);
        }).orElseThrow(() -> new Exception("Такой книги в заказе не существует"));
    }

    @DeleteMapping("/orders/{id}/books/{bookId}/delete")
    public ResponseEntity<?> deleteOrderBook(@PathVariable Long bookId) throws Exception {
        return repository.findById(bookId).map(c -> {
            repository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new Exception("Такого заказа не существует"));
    }
}
