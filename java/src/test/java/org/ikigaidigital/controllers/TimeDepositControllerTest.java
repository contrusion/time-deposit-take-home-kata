package org.ikigaidigital.controllers;

import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.domain.TimeDepositCalculator;
import org.ikigaidigital.services.TimeDepositService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@WebMvcTest(TimeDepositController.class)
class TimeDepositControllerTest {

    @MockBean
    private TimeDepositCalculator calculator;

    @MockBean
    private TimeDepositService timeDepositService;

    @Autowired
    private TimeDepositController timeDepositController;

    @Test
    void updateDeposits() {
        List<TimeDeposit> deposits = Arrays.asList(
                new TimeDeposit(0, "student", 1000.0, 60),
                new TimeDeposit(0, "premium", 2000.0, 90),
                new TimeDeposit(0, "basic", 500.0, 40)
        );

        doNothing().when(calculator).updateBalance(deposits);
        when(timeDepositService.updateTimeDeposit(any(TimeDeposit.class)))
                .thenAnswer(invocation -> Optional.of(invocation.getArgument(0)));

        List<TimeDeposit> updated = timeDepositController.updateDeposits(deposits);

        assertThat(updated).hasSize(3);
        verify(calculator, times(1)).updateBalance(deposits);
        verify(timeDepositService, times(3)).updateTimeDeposit(any(TimeDeposit.class));
    }

    @Test
    void getAllDeposits() {
        List<TimeDeposit> mockDeposits = Arrays.asList(
                new TimeDeposit(1, "student", 1000.0, 60),
                new TimeDeposit(2, "premium", 2000.0, 90)
        );

        when(timeDepositService.getAllTimeDeposits()).thenReturn(mockDeposits);

        List<TimeDeposit> allDeposits = timeDepositController.getAllDeposits();

        assertThat(allDeposits).hasSize(2);
        assertThat(allDeposits.stream().anyMatch(d -> d.getPlanType().equals("student"))).isTrue();
        assertThat(allDeposits.stream().anyMatch(d -> d.getPlanType().equals("premium"))).isTrue();
        verify(timeDepositService, times(1)).getAllTimeDeposits();
    }
}