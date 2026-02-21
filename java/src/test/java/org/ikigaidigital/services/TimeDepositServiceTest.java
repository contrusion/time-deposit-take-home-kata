package org.ikigaidigital.services;

import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.repositories.TimeDepositRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TimeDepositServiceTest {

    @Mock
    private TimeDepositRepository timeDepositRepository;

    @InjectMocks
    private TimeDepositService timeDepositService;

    @Test
    void updateTimeDeposit_shouldSaveNewEntity() {
        TimeDeposit newDeposit = new TimeDeposit(0, "student", 1000.0, 60);

        when(timeDepositRepository.save(newDeposit)).thenReturn(new TimeDeposit(1, "student", 1000.0, 60));

        Optional<TimeDeposit> savedOpt = timeDepositService.updateTimeDeposit(newDeposit);

        assertThat(savedOpt).isPresent();
        TimeDeposit saved = savedOpt.get();
        assertThat(saved.getId()).isEqualTo(1);
        assertThat(saved.getPlanType()).isEqualTo("student");
        assertThat(saved.getBalance()).isEqualTo(1000.0);
        verify(timeDepositRepository, never()).existsById(anyInt());
        verify(timeDepositRepository, times(1)).save(newDeposit);
    }

    @Test
    void updateTimeDeposit_shouldUpdateExistingEntity() {
        TimeDeposit existingDeposit = new TimeDeposit(2, "student", 1000.0, 60);

        when(timeDepositRepository.existsById(2)).thenReturn(true);
        when(timeDepositRepository.save(existingDeposit)).thenReturn(new TimeDeposit(2, "student", 1200.0, 60));

        existingDeposit.setBalance(1200.0);
        Optional<TimeDeposit> updatedOpt = timeDepositService.updateTimeDeposit(existingDeposit);

        assertThat(updatedOpt).isPresent();
        TimeDeposit updated = updatedOpt.get();
        assertThat(updated.getId()).isEqualTo(2);
        assertThat(updated.getBalance()).isEqualTo(1200.0);

        verify(timeDepositRepository, times(1)).existsById(2);
        verify(timeDepositRepository, times(1)).save(existingDeposit);
    }

    @Test
    void getAllTimeDeposits_shouldReturnAllEntities() {
        List<TimeDeposit> mockDeposits = Arrays.asList(
                new TimeDeposit(1, "student", 1000.0, 60),
                new TimeDeposit(2, "premium", 2000.0, 90)
        );

        when(timeDepositRepository.findAll()).thenReturn(mockDeposits);

        List<TimeDeposit> result = timeDepositService.getAllTimeDeposits();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getPlanType()).isEqualTo("student");
        assertThat(result.get(1).getPlanType()).isEqualTo("premium");

        verify(timeDepositRepository, times(1)).findAll();
    }
}