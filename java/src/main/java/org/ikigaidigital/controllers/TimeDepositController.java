package org.ikigaidigital.controllers;

import org.ikigaidigital.model.TimeDeposit;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/time-deposits")
public class TimeDepositController {

    @PostMapping("/update-deposits")
    public List<TimeDeposit> updateDeposits(@RequestBody List<TimeDeposit> deposits) {
        
        //Todo: I will introduce the time deposit calculator service to update the balance of the 
        // time deposits based on the business rules defined in the TimeDepositCalculator class. 
        // This will allow us to calculate the new balances for each time deposit based on 
        // their respective plan types and durations.
        return deposits;
    }

    @GetMapping
    public List<TimeDeposit> getAllDeposits() {
        
        //Todo: I will introduce the time deposit service to fetch all time deposits from the database repository. 
        // This will allow us to retrieve the list of time deposits stored in the database and return them to the client.
        return new ArrayList<>();
    }
}