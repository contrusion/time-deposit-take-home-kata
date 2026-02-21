package org.ikigaidigital.services;

import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.repositories.TimeDepositRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
class TimeDepositServiceTest {

    @Autowired
    private TimeDepositService timeDepositService;

    @Autowired
    private TimeDepositRepository timeDepositRepository;

    @AfterEach
    void cleanUp() {
        timeDepositRepository.deleteAll();
    }

    @Test
    void updateTimeDeposit_shouldSaveAndUpdateEntity() {
        // Save new entity (id = 0)
        TimeDeposit newDeposit = new TimeDeposit(0, "student", 1000.0, 60);
        Optional<TimeDeposit> savedOpt = timeDepositService.updateTimeDeposit(newDeposit);

        assertThat(savedOpt).isPresent();
        TimeDeposit saved = savedOpt.get();
        assertThat(saved.getId()).isNotZero();
        assertThat(saved.getPlanType()).isEqualTo("student");
        assertThat(saved.getBalance()).isEqualTo(1000.0);

        // Update entity
        saved.setBalance(1200.0);
        Optional<TimeDeposit> updatedOpt = timeDepositService.updateTimeDeposit(saved);

        assertThat(updatedOpt).isPresent();
        TimeDeposit updated = updatedOpt.get();
        assertThat(updated.getId()).isEqualTo(saved.getId());
        assertThat(updated.getBalance()).isEqualTo(1200.0);
    }
}