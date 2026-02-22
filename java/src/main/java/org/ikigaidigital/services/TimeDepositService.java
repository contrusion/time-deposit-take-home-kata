package org.ikigaidigital.services;

import org.ikigaidigital.dto.TimeDepositDto;
import org.ikigaidigital.mappers.TimeDepositMapper;
import org.ikigaidigital.domain.TimeDepositCalculator;
import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.repositories.TimeDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimeDepositService {

    private final TimeDepositRepository timeDepositRepository;
    private final TimeDepositCalculator calculator;
    private final TimeDepositMapper mapper;

    @Autowired
    public TimeDepositService(TimeDepositRepository timeDepositRepository,
                              TimeDepositCalculator calculator,
                              TimeDepositMapper mapper) {
        this.timeDepositRepository = timeDepositRepository;
        this.calculator = calculator;
        this.mapper = mapper;
    }

    public List<TimeDepositDto> updateDeposits(List<TimeDepositDto> depositDtos) {
        List<TimeDeposit> deposits = mapper.toEntityList(depositDtos);
        calculator.updateBalance(deposits);
        List<TimeDeposit> updated = deposits.stream()
                .map(this::updateTimeDeposit)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        return mapper.toDtoList(updated);
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
        if (timeDeposit.getId() == 0 || !timeDepositRepository.existsById(timeDeposit.getId())) {
            TimeDeposit saved = timeDepositRepository.save(timeDeposit);
            return Optional.of(saved);
        }
        TimeDeposit saved = timeDepositRepository.save(timeDeposit);
        return Optional.of(saved);
    }

    public List<TimeDepositDto> getAllDeposits() {
        List<TimeDeposit> deposits = timeDepositRepository.findAll();
        return mapper.toDtoList(deposits);
    }


}