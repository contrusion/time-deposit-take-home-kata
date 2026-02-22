package org.ikigaidigital.domain;

import org.ikigaidigital.model.TimeDeposit;

public interface InterestCalculationStrategy {
    double calculateInterest(TimeDeposit deposit);
}
