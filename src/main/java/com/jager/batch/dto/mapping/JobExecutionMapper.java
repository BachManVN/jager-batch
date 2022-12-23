package com.jager.batch.dto.mapping;

import java.util.List;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.batch.core.JobExecution;

import com.jager.batch.dto.JobExecutionDTO;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
@DecoratedWith(AbstractJobExecutionMapperDecorator.class)
public interface JobExecutionMapper {

    @Mapping(source = "jobInstance.jobName", target = "jobName")
    @Mapping(source = "exitStatus.exitCode", target = "exitCode")
    @Mapping(source = "exitStatus.exitDescription", target = "exitMessage")
    @Mapping(target = "parameters", ignore = true)
    JobExecutionDTO toDTO(JobExecution jobExecution);


    List<JobExecutionDTO> toDTO(List<JobExecution> jobExecution);
}
