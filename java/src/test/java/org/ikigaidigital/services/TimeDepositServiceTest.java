package org.ikigaidigital.services;

import org.ikigaidigital.dto.TimeDepositDto;
import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.repositories.TimeDepositRepository;
import org.ikigaidigital.mappers.TimeDepositMapper;
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

    @Mock
    private TimeDepositMapper mapper;

    @InjectMocks
    private TimeDepositService timeDepositService;

    @Test
    void givenEntitiesInDb_whenGetAllDeposits_thenReturnDtoList() {
        // given
        List<TimeDeposit> mockEntities = Arrays.asList(
                new TimeDeposit(1, "student", 1000.0, 60),
                new TimeDeposit(2, "premium", 2000.0, 90)
        );
        List<TimeDepositDto> mockDtos = Arrays.asList(
                new TimeDepositDto(1, "student", 1000.0, 60),
                new TimeDepositDto(2, "premium", 2000.0, 90)
        );
        when(timeDepositRepository.findAll()).thenReturn(mockEntities);
        when(mapper.toDtoList(mockEntities)).thenReturn(mockDtos);

        // when
        List<TimeDepositDto> result = timeDepositService.getAllDeposits();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getPlanType()).isEqualTo("student");
        assertThat(result.get(1).getPlanType()).isEqualTo("premium");
        verify(timeDepositRepository, times(1)).findAll();
        verify(mapper, times(1)).toDtoList(mockEntities);
    }

    @Test
    void givenNewEntity_whenUpdateTimeDeposit_thenSaveAndReturn() {
        // given
        TimeDeposit newDeposit = new TimeDeposit(0, "student", 1000.0, 60);
        TimeDeposit savedDeposit = new TimeDeposit(1, "student", 1000.0, 60);
        when(timeDepositRepository.save(newDeposit)).thenReturn(savedDeposit);

        // when
        Optional<TimeDeposit> result = timeDepositService.updateTimeDeposit(newDeposit);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1);
        assertThat(result.get().getPlanType()).isEqualTo("student");
        assertThat(result.get().getBalance()).isEqualTo(1000.0);
        verify(timeDepositRepository, never()).existsById(anyInt());
        verify(timeDepositRepository, times(1)).save(newDeposit);
    }

    @Test
    void givenExistingEntity_whenUpdateTimeDeposit_thenUpdateAndReturn() {
        // given
        TimeDeposit existingDeposit = new TimeDeposit(2, "student", 1000.0, 60);
        TimeDeposit updatedDeposit = new TimeDeposit(2, "student", 1200.0, 60);
        when(timeDepositRepository.existsById(2)).thenReturn(true);
        when(timeDepositRepository.save(existingDeposit)).thenReturn(updatedDeposit);

        // when
        existingDeposit.setBalance(1200.0);
        Optional<TimeDeposit> result = timeDepositService.updateTimeDeposit(existingDeposit);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(2);
        assertThat(result.get().getBalance()).isEqualTo(1200.0);
        verify(timeDepositRepository, times(1)).existsById(2);
        verify(timeDepositRepository, times(1)).save(existingDeposit);
    }
}