package com.bridgelebz.addressBook.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "address_book")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String city;
}
