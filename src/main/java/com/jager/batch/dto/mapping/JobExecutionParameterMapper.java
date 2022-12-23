package com.jager.batch.dto.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jager.batch.domain.JobExecutionParameter;
import com.jager.batch.dto.JobExecutionParameterInputDTO;

@Mapper(componentModel = "spring")
public interface JobExecutionParameterMapper {

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "identifyCharacter", ignore = true)
    JobExecutionParameter toEntity(JobExecutionParameterInputDTO jobExecutionParameterInputDTO);

    List<JobExecutionParameter> toEntity(List<JobExecutionParameterInputDTO> jobsExecutionParameterInputDTO);

}
