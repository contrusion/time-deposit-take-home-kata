package org.ikigaidigital.repositories;

import org.ikigaidigital.model.TimeDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeDepositRepository extends JpaRepository<TimeDeposit, Integer> {
}
