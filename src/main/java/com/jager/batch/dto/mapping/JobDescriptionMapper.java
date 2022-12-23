package com.jager.batch.dto.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

import com.jager.batch.domain.JobDescription;
import com.jager.batch.dto.JobDescriptionDTO;

@Service
@Mapper(componentModel = "spring", uses = {JobDescriptionParameterMapper.class})
public interface JobDescriptionMapper {

    @Mapping(target = "lastExecutionStatus", ignore = true)
    JobDescriptionDTO toDTO(JobDescription jobDescription);

    List<JobDescriptionDTO> toDTO(List<JobDescription> jobDescription);
}
