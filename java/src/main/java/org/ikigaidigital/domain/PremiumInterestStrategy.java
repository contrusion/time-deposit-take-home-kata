package org.ikigaidigital.domain;

import org.ikigaidigital.model.TimeDeposit;

public class PremiumInterestStrategy implements InterestCalculationStrategy {
    @Override
    public double calculateInterest(TimeDeposit deposit) {
        if (deposit.getDays() > 30 && deposit.getPlanType().equals("premium") && deposit.getDays() > 45) {
            return deposit.getBalance() * 0.05 / 12;
        }
        return 0;
    }
}
