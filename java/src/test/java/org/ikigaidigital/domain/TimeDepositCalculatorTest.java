package org.ikigaidigital.domain;

import org.ikigaidigital.model.TimeDeposit;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TimeDepositCalculatorTest {
    @Test
    public void updateBalance_Test() {
        // Previously this test was not really testing the functionality of the method.
        // It was merely doing an assertion that would always pass:  assertThat(1).isEqualTo(1);
        // I have updated the test to actually create a TimeDeposit, call the updateBalance method, and then check if the balance was updated correctly based on the interest calculation logic.
        TimeDepositCalculator calc = new TimeDepositCalculator();
        List<TimeDeposit> plans = Arrays.asList(
                new TimeDeposit(1, "basic", 1234567.00, 45)
        );
        calc.updateBalance(plans);

        // For "basic" plan, days > 30, interest = balance * 0.01 / 12
        double expectedInterest = 1234567.00 * 0.01 / 12;
        double expectedBalance = 1234567.00 + Math.round(expectedInterest * 100.0) / 100.0;

        assertThat(plans.get(0).getBalance()).isEqualTo(expectedBalance);


    }
}