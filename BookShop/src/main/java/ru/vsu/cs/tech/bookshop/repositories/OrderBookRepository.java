package ru.vsu.cs.tech.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.tech.bookshop.models.Order;
import ru.vsu.cs.tech.bookshop.models.OrderBook;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {
    @Query(value = "SELECT o FROM OrderBook o WHERE o.order.orderId = ?1")
    List<OrderBook> findOrderBooksByOrderId(Long orderId);
}
