package org.ikigaidigital.domain;

import org.ikigaidigital.model.TimeDeposit;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InterestStrategyTest {
    @Test
    void givenStudentPlanWithValidDays_whenCalculateInterest_thenCorrectInterestReturned() {
        InterestCalculationStrategy strategy = new StudentInterestStrategy();
        TimeDeposit deposit = new TimeDeposit(1, "student", 1000.0, 60);
        double interest = strategy.calculateInterest(deposit);
        assertThat(interest).isEqualTo(1000.0 * 0.03 / 12);
    }

    @Test
    void givenStudentPlanWithShortDays_whenCalculateInterest_thenNoInterestReturned() {
        InterestCalculationStrategy strategy = new StudentInterestStrategy();
        TimeDeposit deposit = new TimeDeposit(2, "student", 1000.0, 20);
        double interest = strategy.calculateInterest(deposit);
        assertThat(interest).isEqualTo(0);
    }

    @Test
    void givenPremiumPlanWithValidDays_whenCalculateInterest_thenCorrectInterestReturned() {
        InterestCalculationStrategy strategy = new PremiumInterestStrategy();
        TimeDeposit deposit = new TimeDeposit(3, "premium", 2000.0, 50);
        double interest = strategy.calculateInterest(deposit);
        assertThat(interest).isEqualTo(2000.0 * 0.05 / 12);
    }

    @Test
    void givenPremiumPlanWithShortDays_whenCalculateInterest_thenNoInterestReturned() {
        InterestCalculationStrategy strategy = new PremiumInterestStrategy();
        TimeDeposit deposit = new TimeDeposit(4, "premium", 2000.0, 40);
        double interest = strategy.calculateInterest(deposit);
        assertThat(interest).isEqualTo(0);
    }

    @Test
    void givenBasicPlanWithValidDays_whenCalculateInterest_thenCorrectInterestReturned() {
        InterestCalculationStrategy strategy = new BasicInterestStrategy();
        TimeDeposit deposit = new TimeDeposit(5, "basic", 1500.0, 40);
        double interest = strategy.calculateInterest(deposit);
        assertThat(interest).isEqualTo(1500.0 * 0.01 / 12);
    }

    @Test
    void givenBasicPlanWithShortDays_whenCalculateInterest_thenNoInterestReturned() {
        InterestCalculationStrategy strategy = new BasicInterestStrategy();
        TimeDeposit deposit = new TimeDeposit(6, "basic", 1500.0, 20);
        double interest = strategy.calculateInterest(deposit);
        assertThat(interest).isEqualTo(0);
    }
}
