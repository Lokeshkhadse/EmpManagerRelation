package com.example.Incentive.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.security.PrivateKey;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Incentive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int emp_id;
    private double saleAmount;

    @JsonIgnore
    private String stock;

    @Transient
    List<Stock> stocks;
}
