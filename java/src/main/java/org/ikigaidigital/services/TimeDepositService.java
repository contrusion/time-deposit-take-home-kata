package org.ikigaidigital.services;

import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.repositories.TimeDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TimeDepositService {
    private final TimeDepositRepository timeDepositRepository;

    @Autowired
    public TimeDepositService(TimeDepositRepository timeDepositRepository) {
        this.timeDepositRepository = timeDepositRepository;
    }

    public List<TimeDeposit> getAllTimeDeposits() {
        return timeDepositRepository.findAll();
    }

    /**
     * Updates an existing TimeDeposit entity if it exists (by id), or saves it as a new entity if not.
     * <p>
     * Requirement: Only Update and GetAll TimeDeposit APIs are required.
     * Since an entity must exist in order to be updated, and there is no explicit Create API,
     * this method combines the logic for both update and save. If the provided TimeDeposit has an id
     * that does not exist in the repository (or id is 0), it will be saved as a new entity.
     * Otherwise, the existing entity will be updated.
     *
     * @param timeDeposit the TimeDeposit entity to update or save
     * @return the saved or updated TimeDeposit wrapped in an Optional
     */
    public Optional<TimeDeposit> updateTimeDeposit(TimeDeposit timeDeposit) {
        // If id is 0 or not present, treat as new entity
        if (timeDeposit.getId() == 0 || !timeDepositRepository.existsById(timeDeposit.getId())) {
            TimeDeposit saved = timeDepositRepository.save(timeDeposit);
            return Optional.of(saved);
        }
        // If id exists, update the entity
        TimeDeposit saved = timeDepositRepository.save(timeDeposit);
        return Optional.of(saved);
    }
}