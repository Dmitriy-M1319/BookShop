package ru.vsu.cs.tech.bookshop.models;

import java.io.Serializable;

public class Order implements Serializable {
    private Long orderId;
    private String customerSurname;
    private String phoneNumber;
    private String email;
    private String Status;

    public Order(String customerSurname, String phoneNumber, String email, String status) {
        this.customerSurname = customerSurname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        Status = status;
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
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
