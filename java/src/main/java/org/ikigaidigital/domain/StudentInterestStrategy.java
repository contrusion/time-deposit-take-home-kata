package org.ikigaidigital.domain;

import org.ikigaidigital.model.TimeDeposit;

public class StudentInterestStrategy implements InterestCalculationStrategy {
    @Override
    public double calculateInterest(TimeDeposit deposit) {
        if (deposit.getDays() > 30 && deposit.getDays() < 366) {
            return deposit.getBalance() * 0.03 / 12;
        }
        return 0;
    }
}
