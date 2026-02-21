package org.ikigaidigital.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "time_deposits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeDeposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @javax.persistence.Column(nullable = false)
    private String planType;

    @NonNull
    @javax.persistence.Column(nullable = false)
    private Double balance;

    private int days;
}
