package org.ikigaidigital.domain;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.ikigaidigital.model.TimeDeposit;
import org.springframework.stereotype.Service;

@Service
public class TimeDepositCalculator {
    private final Map<String, InterestCalculationStrategy> strategyMap = new HashMap<>();

    public TimeDepositCalculator() {
        strategyMap.put("student", new StudentInterestStrategy());
        strategyMap.put("premium", new PremiumInterestStrategy());
        strategyMap.put("basic", new BasicInterestStrategy());
    }

    public void updateBalance(List<TimeDeposit> xs) {
        for (TimeDeposit deposit : xs) {
            double interest = 0;
            InterestCalculationStrategy strategy = strategyMap.get(deposit.getPlanType());
            if (strategy != null) {
                interest = strategy.calculateInterest(deposit);
            }
            double a2d = deposit.getBalance() + (new BigDecimal(interest).setScale(2, RoundingMode.HALF_UP)).doubleValue();
            deposit.setBalance(a2d);
        }
    }
}
