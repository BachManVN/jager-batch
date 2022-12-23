package com.jager.batch.dto.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jager.batch.dto.JobExecutionDTO;
import com.jager.batch.entity.JobExecutionEntity;

@Mapper(componentModel = "spring", uses = {DateMapper.class, JobExecutionParameterEntityMapper.class})
public interface JobExecutionEntityMapper {

    @Mapping(source = "jobInstance.jobName", target = "jobName")
    JobExecutionDTO toDTO(JobExecutionEntity jobExecutionEntity);
}
