package org.ikigaidigital.domain;

import org.ikigaidigital.model.TimeDeposit;

public class BasicInterestStrategy implements InterestCalculationStrategy {
    @Override
    public double calculateInterest(TimeDeposit deposit) {
        if (deposit.getDays() > 30 && deposit.getPlanType().equals("basic")) {
            return deposit.getBalance() * 0.01 / 12;
        }
        return 0;
    }
}
