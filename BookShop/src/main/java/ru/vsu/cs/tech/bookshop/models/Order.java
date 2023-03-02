package ru.vsu.cs.tech.bookshop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private Long orderId;

    @OneToMany(mappedBy = "order")
    private List<OrderBook> orderBooks;
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
}
