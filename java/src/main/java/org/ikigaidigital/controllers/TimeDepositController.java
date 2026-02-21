package org.ikigaidigital.controllers;

import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.domain.TimeDepositCalculator;
import org.ikigaidigital.services.TimeDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/time-deposits")
public class TimeDepositController {

    private final TimeDepositCalculator calculator;
    private final TimeDepositService timeDepositService;

    @Autowired
    public TimeDepositController(TimeDepositCalculator calculator, TimeDepositService timeDepositService) {
        this.calculator = calculator;
        this.timeDepositService = timeDepositService;
    }

    @PostMapping("/update-deposits")
    public List<TimeDeposit> updateDeposits(@RequestBody List<TimeDeposit> deposits) {
        calculator.updateBalance(deposits);
        return deposits.stream()
                .map(timeDepositService::updateTimeDeposit)
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<TimeDeposit> getAllDeposits() {
        return timeDepositService.getAllTimeDeposits();
    }
}