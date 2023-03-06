package ru.vsu.cs.tech.bookshop.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vsu.cs.tech.bookshop.models.Order;
import ru.vsu.cs.tech.bookshop.models.OrderBook;
import ru.vsu.cs.tech.bookshop.repositories.OrderBookRepository;

import java.util.List;

@Service
public class OrderBookService {

    private final OrderBookRepository repository;

    private final OrderService orderService;

    public OrderBookService(OrderBookRepository repository, OrderService orderService) {
        this.repository = repository;
        this.orderService = orderService;
    }

    public List<OrderBook> getOrderBooksByOrderId(Long orderId) {
        return repository.findOrderBooksByOrderId(orderId);
    }

    public OrderBook addOrderBook(Long orderId, OrderBook book) throws IllegalArgumentException {
        Order order = orderService.getOrderById(orderId);
        book.setOrder(order);
        return repository.save(book);
    }

    public OrderBook updateExistingBook(Long id, Long orderId, OrderBook book) throws IllegalArgumentException {
        return repository.findById(id).map(b -> {
            b.setBookAuthor(book.getBookAuthor());
            b.setBookTag(book.getBookTag());
            b.setBooksCount(book.getBooksCount());
            return repository.save(b);
        }).orElseThrow(() -> new IllegalArgumentException("Такой книги в заказе не существует"));
    }

    public ResponseEntity<?> deleteExistingBook(Long id) throws IllegalArgumentException {
        return repository.findById(id).map(c -> {
            repository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("Такой книги в заказе не существует"));
    }
}
