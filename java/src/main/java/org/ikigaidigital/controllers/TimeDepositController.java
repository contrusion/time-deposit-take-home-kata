package org.ikigaidigital.controllers;

import org.ikigaidigital.dto.TimeDepositDto;
import org.ikigaidigital.services.TimeDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/time-deposits")
public class TimeDepositController {

    private final TimeDepositService timeDepositService;

    @Autowired
    public TimeDepositController(TimeDepositService timeDepositService) {
        this.timeDepositService = timeDepositService;
    }


    /**
     * Requirement: Only Update and GetAll TimeDeposit APIs are required.
     * We need to implement an API that can handle both updating existing TimeDeposit and creating new TimeDeposit
     * entities and creating new ones, since there is no explicit Create API is allowed by the requirements.
     * This API Updates the provided list of TimeDeposit entities. If an entity has an id that does not exist in the repository
     * (or id is 0), it will be saved as a new entity. Otherwise, the existing entity will be updated.
     *
     * @param depositDtos the list of TimeDepositDto objects to update or save
     * @return the list of updated or newly created TimeDepositDto objects
     */
    @PostMapping("/update-deposits")
    public List<TimeDepositDto> updateDeposits(@RequestBody List<TimeDepositDto> depositDtos) {
        return timeDepositService.updateDeposits(depositDtos);
    }

    @GetMapping
    public List<TimeDepositDto> getAllDeposits() {
        return timeDepositService.getAllDeposits();
    }
}