package com.fproject.fcommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Column(nullable = false, length = 255)
    private String street;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String state;

    @Column(nullable = false)
    private String pincode;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "is_default", nullable = false)
    private boolean defaultAddress = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", nullable = false)
    private AddressType addressType;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
