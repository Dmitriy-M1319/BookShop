package ru.vsu.cs.tech.bookshop.repositories;

import ru.vsu.cs.tech.bookshop.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private List<Order> database;
    private long id;

    public OrderRepository() {
        database = new ArrayList<>();
        id = 1;
    }

    public OrderRepository(List<Order> orders) {
        database = orders;
        id = orders.size() + 1;
    }

    public List<Order> getAllOrders() {
        return database;
    }
    public Order getOrderById(long id) throws Exception{
        Order result = (Order) database.stream().filter(o->o.getOrderId() == id);
        if (result.getOrderId() != id) {
            throw new Exception("No such order with id " + id);
        } else {
            return result;
        }
    }

    public List<Order> getOrderByCustomer(String customer) {
        return database.stream().filter(o->o.getCustomerSurname().equals(customer)).toList();
    }

    public List<Order> getOrdersByCustomerAndStatus(String customer, String status) {
        return getOrderByCustomer(customer).stream().filter(o->o.getStatus().equals(status)).toList();
    }


    public void insert(Order order) {
        order.setOrderId(id);
        database.add(order);
        id++;
    }

    public void update(long orderId, Order order) throws Exception{
        Order updated = getOrderById(orderId);
        updated.copyDataFromAnotherOrder(order);
        database.add((int)orderId - 1, updated);
    }

    public void delete(long orderId) throws Exception{
        Order deleted = getOrderById(orderId);
        database.remove(deleted);
    }
}
