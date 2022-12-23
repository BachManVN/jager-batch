package com.jager.batch.dto.mapping;

import java.util.List;

import org.mapstruct.Mapper;

import com.jager.batch.domain.JobDescriptionParameter;
import com.jager.batch.dto.JobDescriptionParameterDTO;

@Mapper(componentModel = "spring")
public interface JobDescriptionParameterMapper {

    JobDescriptionParameterDTO toDTO(JobDescriptionParameter jobDescriptionParameter);

    List<JobDescriptionParameterDTO> toDTO(List<JobDescriptionParameter> jobsDescription);

}
