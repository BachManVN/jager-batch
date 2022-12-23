package com.jager.batch.dto.mapping;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jager.batch.dto.JobExecutionDTO;
import com.jager.batch.dto.JobExecutionParameterDTO;

public abstract class AbstractJobExecutionMapperDecorator implements JobExecutionMapper {


    @Autowired
    @Qualifier("delegate")
    private JobExecutionMapper delegate;

    @Override
    public JobExecutionDTO toDTO(JobExecution job) {
        JobExecutionDTO jobExecutionDTO = delegate.toDTO(job);
        if (job.getJobParameters() != null) {
            jobExecutionDTO.setParameters(job.getJobParameters().getParameters().entrySet().stream()
                    .map(this::mapJobParameter)
                    .collect(Collectors.toList()));
        }
        return jobExecutionDTO;
    }

    private JobExecutionParameterDTO mapJobParameter(Map.Entry<String, JobParameter> entry) {
        JobExecutionParameterDTO dto = new JobExecutionParameterDTO();
        dto.setName(entry.getKey());
        if (entry.getValue() != null) {
            dto.setType(entry.getValue().getType().name());
            dto.setValue(entry.getValue().getValue() == null ? "" : entry.getValue().getValue().toString());
            dto.setIdentifyCharacter(Boolean.toString(entry.getValue().isIdentifying()));
        }
        return dto;
    }
}
