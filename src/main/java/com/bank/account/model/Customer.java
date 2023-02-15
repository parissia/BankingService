package com.bank.account.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Table(name = "customers")
@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @NotBlank(message="* Name is a required field")
    private String name;

    @NotBlank(message="* Surname is a required field")
    private String surname;

    @Column(name = "initial_credit")
    private Long initialCredit;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private List<Account> accounts = new ArrayList<>();

    public Customer() {
    }

    public Customer(Long customerId, String name, String surname, Long initialCredit) {
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
        this.initialCredit = initialCredit;
    }
}
