package com.jager.batch.service.rest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jager.batch.dto.JobDescriptionDTO;
import com.jager.batch.dto.JobExecutionDTO;
import com.jager.batch.dto.JobExecutionParameterInputDTO;
import com.jager.batch.dto.StepExecutionDTO;
import com.jager.batch.dto.mapping.JobDescriptionMapper;
import com.jager.batch.dto.mapping.JobExecutionEntityMapper;
import com.jager.batch.dto.mapping.JobExecutionMapper;
import com.jager.batch.dto.mapping.JobExecutionParameterMapper;
import com.jager.batch.dto.mapping.StepExecutionMapper;
import com.jager.batch.entity.JobExecutionEntity;
import com.jager.batch.service.JobService;

@Service
public class JobRestService {

    private final JobService jobService;
    private final JobDescriptionMapper jobDescriptionMapper;
    private final JobExecutionMapper jobExecutionMapper;
    private final JobExecutionEntityMapper jobExecutionEntityMapper;
    private final StepExecutionMapper stepExecutionMapper;
    private final JobExecutionParameterMapper jobExecutionParameterMapper;

    public JobRestService(JobService jobService, JobDescriptionMapper jobDescriptionMapper,
                          JobExecutionMapper jobExecutionMapper, JobExecutionEntityMapper jobExecutionEntityMapper,
                          StepExecutionMapper stepExecutionMapper, JobExecutionParameterMapper jobExecutionParameterMapper) {
        this.jobService = jobService;
        this.jobDescriptionMapper = jobDescriptionMapper;
        this.jobExecutionMapper = jobExecutionMapper;
        this.jobExecutionEntityMapper = jobExecutionEntityMapper;
        this.stepExecutionMapper = stepExecutionMapper;
        this.jobExecutionParameterMapper = jobExecutionParameterMapper;
    }

    public List<JobDescriptionDTO> getJobs(String jobName) {
        return jobDescriptionMapper.toDTO(jobService.getJobs(jobName)).stream().map(this::updateLastStatusExecution).collect(Collectors.toList());
    }

    public JobDescriptionDTO getJob(String name) {
        return updateLastStatusExecution(jobDescriptionMapper.toDTO(jobService.getJob(name)));
    }

    private JobDescriptionDTO updateLastStatusExecution(JobDescriptionDTO jobDescriptionDTO) {
        JobExecutionEntity jobExecution = jobService.getLastJobExecution(jobDescriptionDTO.getName());
        jobDescriptionDTO.setLastExecutionStatus(jobExecution != null ? jobExecution.getStatus() : null);
        return jobDescriptionDTO;
    }

    public JobExecutionDTO start(String jobName, List<JobExecutionParameterInputDTO> jobParameters) {
        return jobExecutionMapper.toDTO(jobService.start(jobName, jobExecutionParameterMapper.toEntity(jobParameters)));
    }

    public Page<JobExecutionDTO> getJobNameExecutions(Pageable pageable, String name, String status, LocalDateTime executionBeginDate, LocalDateTime executionEndDate) {
        return jobService.getJobNameExecutions(pageable, name, status, executionBeginDate, executionEndDate).map(jobExecutionEntityMapper::toDTO);
    }

    public Page<JobExecutionDTO> getJobExecutions(Pageable pageable, String[] statuses) {
        return jobService.getJobExecutions(pageable, statuses != null ? Arrays.asList(statuses) : null).map(jobExecutionEntityMapper::toDTO);
    }

    public JobExecutionDTO getJobExecution(Long id) {
        return jobExecutionMapper.toDTO(jobService.getJobExecution(id));
    }

    public Boolean stop(Long id) {
        return jobService.stop(id);
    }

    public List<StepExecutionDTO> getJobExecutionStepExecutions(Long id) {
        return stepExecutionMapper.toDTO(jobService.getJobExecutionStepExecutions(id));
    }
}
