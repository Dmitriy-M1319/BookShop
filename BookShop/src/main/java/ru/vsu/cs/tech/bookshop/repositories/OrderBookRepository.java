package ru.vsu.cs.tech.bookshop.repositories;

import ru.vsu.cs.tech.bookshop.models.Order;
import ru.vsu.cs.tech.bookshop.models.OrderBook;

import java.util.ArrayList;
import java.util.List;

public class OrderBookRepository {
    private List<OrderBook> database;
    private long id;

    public OrderBookRepository() {
        database = new ArrayList<>();
        id = 1;
    }

    public OrderBookRepository(List<OrderBook> orderBooks) {
        database = orderBooks;
        id = orderBooks.size() + 1;
    }

    public OrderBook getOrderBookById(long id) throws Exception{
        OrderBook result = (OrderBook) database.stream().filter(o->o.getId() == id);
        if (result.getId() != id) {
            throw new Exception("No such order book with id " + id);
        } else {
            return result;
        }
    }

    public List<OrderBook> getBooksByOrder(Order order) {
        return database.stream().filter(o->o.getOrder().equals(order)).toList();
    }

    public void insert(OrderBook book) {
        book.setId(id);
        database.add(book);
        id++;
    }

    public void update(long bookId, OrderBook book) throws Exception{
        OrderBook updated = getOrderBookById(bookId);
        updated.copyDataFromAnotherOrderBook(book);
        database.add((int)bookId - 1, updated);
    }

    public void delete(long bookId) throws Exception{
        OrderBook deleted = getOrderBookById(bookId);
        database.remove(deleted);
    }
}
