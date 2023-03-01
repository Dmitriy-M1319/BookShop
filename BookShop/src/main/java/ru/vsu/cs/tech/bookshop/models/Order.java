package ru.vsu.cs.tech.bookshop.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private Long orderId;
    @Column(name = "customer_surname")
    private String customerSurname;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    private String email;
    @Column
    private String status;

    public Order(String customerSurname, String phoneNumber, String email, String status) {
        this.customerSurname = customerSurname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;
    }

    public Order() {

    }

    public void copyDataFromAnotherOrder(Order order) {
        this.customerSurname = order.customerSurname;
        this.phoneNumber = order.phoneNumber;
        this.email = order.email;
        this.status = order.status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
