package ru.vsu.cs.tech.bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vsu.cs.tech.bookshop.dto.OrderBookGetDto;
import ru.vsu.cs.tech.bookshop.dto.OrderBookPostDto;
import ru.vsu.cs.tech.bookshop.dto.OrderDto;
import ru.vsu.cs.tech.bookshop.mappers.OrderBookMapper;
import ru.vsu.cs.tech.bookshop.repositories.OrderBookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderBookService {

    @Autowired
    private OrderBookRepository repository;

    @Autowired
    private OrderService orderService;
    private OrderBookMapper mapper;

    public OrderBookService(OrderBookMapper mapper) {
        this.mapper = mapper;
    }

    public List<OrderBookGetDto> getOrderBooksByOrderId(Long orderId) {
        return repository.findOrderBooksByOrderId(orderId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public OrderBookGetDto addOrderBook(OrderBookPostDto book) throws IllegalArgumentException {
        OrderDto order = orderService.getOrderById(book.getOrderId());
        OrderBookGetDto newBook = new OrderBookGetDto();
        newBook.setOrder(order);
        newBook.setBookAuthor(book.getBookAuthor());
        newBook.setBooksCount(book.getBooksCount());
        newBook.setBookTag(book.getBookTag());
        newBook.setBookName(book.getBookName());
        return mapper.toDto(repository.save(mapper.toModel(newBook)));
    }

    public OrderBookGetDto updateExistingBook(Long id, OrderBookPostDto book) throws IllegalArgumentException {
        return repository.findById(id).map(b -> {
            b.setOrder(orderService.getOrderModelById(book.getOrderId()));
            b.setBookAuthor(book.getBookAuthor());
            b.setBookTag(book.getBookTag());
            b.setBooksCount(book.getBooksCount());
            return mapper.toDto(repository.save(b));
        }).orElseThrow(() -> new IllegalArgumentException("Такой книги в заказе не существует"));
    }

    public ResponseEntity<?> deleteExistingBook(Long id) throws IllegalArgumentException {
        return repository.findById(id).map(c -> {
            repository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("Такой книги в заказе не существует"));
    }
}
