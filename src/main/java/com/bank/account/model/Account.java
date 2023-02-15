package com.bank.account.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Table(name = "accounts")
@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @NotNull(message = "* Customer is required")
    @Column(name = "customer_id")
    private Long customerId;

    @NotBlank(message="* Account number is a required field")
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "available_balance")
    private Long availableBalance;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private List<Transaction> transactions = new ArrayList<>();
}
