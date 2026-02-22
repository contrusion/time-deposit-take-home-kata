package org.ikigaidigital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeDepositDto {
    private int id;
    private String planType;
    private Double balance;
    private int days;
}