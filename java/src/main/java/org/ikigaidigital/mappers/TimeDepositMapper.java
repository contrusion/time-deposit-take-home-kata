package org.ikigaidigital.mappers;

import org.ikigaidigital.model.TimeDeposit;
import org.ikigaidigital.dto.TimeDepositDto;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeDepositMapper {
    TimeDepositDto toDto(TimeDeposit entity);
    TimeDeposit toEntity(TimeDepositDto dto);
    List<TimeDepositDto> toDtoList(List<TimeDeposit> entities);
    List<TimeDeposit> toEntityList(List<TimeDepositDto> dtos);
}