package ru.vsu.cs.tech.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.tech.bookshop.models.Order;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByCustomerSurname(String surname);
    List<Order> findOrdersByStatus(String status);
}
