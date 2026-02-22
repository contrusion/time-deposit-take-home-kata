package org.ikigaidigital.controllers;

import org.ikigaidigital.dto.TimeDepositDto;
import org.ikigaidigital.services.TimeDepositService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@WebMvcTest(TimeDepositController.class)
class TimeDepositControllerTest {

    @MockBean
    private TimeDepositService timeDepositService;

    @Autowired
    private TimeDepositController timeDepositController;

    @Test
    void givenDtos_whenUpdateDeposits_thenReturnUpdatedDtos() {
        // given
        List<TimeDepositDto> dtos = Arrays.asList(
                new TimeDepositDto(0, "student", 1000.0, 60),
                new TimeDepositDto(0, "premium", 2000.0, 90)
        );
        List<TimeDepositDto> updatedDtos = Arrays.asList(
                new TimeDepositDto(1, "student", 1100.0, 60),
                new TimeDepositDto(2, "premium", 2100.0, 90)
        );

        when(timeDepositService.updateDeposits(dtos)).thenReturn(updatedDtos);

        // when
        List<TimeDepositDto> result = timeDepositController.updateDeposits(dtos);

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(1);
        assertThat(result.get(1).getId()).isEqualTo(2);
        assertThat(result.get(0).getBalance()).isEqualTo(1100.0);
        assertThat(result.get(1).getBalance()).isEqualTo(2100.0);

        verify(timeDepositService, times(1)).updateDeposits(dtos);
    }

    @Test
    void givenDepositsInService_whenGetAllDeposits_thenReturnDtos() {
        // given
        List<TimeDepositDto> dtos = Arrays.asList(
                new TimeDepositDto(1, "student", 1100.0, 60),
                new TimeDepositDto(2, "premium", 2100.0, 90)
        );
        when(timeDepositService.getAllDeposits()).thenReturn(dtos);

        // when
        List<TimeDepositDto> result = timeDepositController.getAllDeposits();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getPlanType()).isEqualTo("student");
        assertThat(result.get(1).getPlanType()).isEqualTo("premium");

        verify(timeDepositService, times(1)).getAllDeposits();
    }
}