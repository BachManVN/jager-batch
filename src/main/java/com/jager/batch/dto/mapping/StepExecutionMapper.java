package com.jager.batch.dto.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.batch.core.StepExecution;

import com.jager.batch.dto.StepExecutionDTO;

@Mapper(componentModel = "spring", uses = {DateMapper.class} )
public interface StepExecutionMapper {

    @Mapping(source = "stepName", target = "name")
    @Mapping(source = "exitStatus.exitCode", target = "exitCode")
    @Mapping(source = "exitStatus.exitDescription", target = "exitMessage")
    StepExecutionDTO toDTO(StepExecution object);

    List<StepExecutionDTO> toDTO(List<StepExecution> object);
}
