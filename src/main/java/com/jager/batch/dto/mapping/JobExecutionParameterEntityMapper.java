package com.jager.batch.dto.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jager.batch.dto.JobExecutionParameterDTO;
import com.jager.batch.entity.JobExecutionParameterEntity;

@Mapper(componentModel = "spring")
public interface JobExecutionParameterEntityMapper {

    @Mapping(source = "identifying", target = "identifyCharacter")
    JobExecutionParameterDTO toDTO(JobExecutionParameterEntity jobExecutionEntity);

    List<JobExecutionParameterDTO> toDTO(List<JobExecutionParameterEntity> jobExecutionEntity);
}
