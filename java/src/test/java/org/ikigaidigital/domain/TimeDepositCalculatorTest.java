package org.ikigaidigital.domain;

import org.ikigaidigital.model.TimeDeposit;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TimeDepositCalculatorTest {
    @Test
    public void givenBasicPlanWithValidDays_whenUpdateBalance_thenBalanceIsUpdatedCorrectly() {
        TimeDepositCalculator calc = new TimeDepositCalculator();
        List<TimeDeposit> plans = Arrays.asList(
                new TimeDeposit(1, "basic", 1234567.00, 45)
        );
        calc.updateBalance(plans);
        double expectedInterest = 1234567.00 * 0.01 / 12;
        double expectedBalance = 1234567.00 + Math.round(expectedInterest * 100.0) / 100.0;
        assertThat(plans.get(0).getBalance()).isEqualTo(expectedBalance);
    }

    @Test
    public void givenStudentPlanWithValidDays_whenUpdateBalance_thenBalanceIsUpdatedCorrectly() {
        TimeDepositCalculator calc = new TimeDepositCalculator();
        List<TimeDeposit> plans = Arrays.asList(
                new TimeDeposit(2, "student", 1000.0, 60)
        );
        calc.updateBalance(plans);
        double expectedInterest = 1000.0 * 0.03 / 12;
        double expectedBalance = 1000.0 + Math.round(expectedInterest * 100.0) / 100.0;
        assertThat(plans.get(0).getBalance()).isEqualTo(expectedBalance);
    }

    @Test
    public void givenPremiumPlanWithValidDays_whenUpdateBalance_thenBalanceIsUpdatedCorrectly() {
        TimeDepositCalculator calc = new TimeDepositCalculator();
        List<TimeDeposit> plans = Arrays.asList(
                new TimeDeposit(3, "premium", 2000.0, 50)
        );
        calc.updateBalance(plans);
        double expectedInterest = 2000.0 * 0.05 / 12;
        double expectedBalance = 2000.0 + Math.round(expectedInterest * 100.0) / 100.0;
        assertThat(plans.get(0).getBalance()).isEqualTo(expectedBalance);
    }

    @Test
    public void givenStudentPlanWithShortDays_whenUpdateBalance_thenNoInterestIsApplied() {
        TimeDepositCalculator calc = new TimeDepositCalculator();
        List<TimeDeposit> plans = Arrays.asList(
                new TimeDeposit(4, "student", 1000.0, 20)
        );
        calc.updateBalance(plans);
        assertThat(plans.get(0).getBalance()).isEqualTo(1000.0);
    }
}