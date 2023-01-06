package com.jager.batch.rest;

import com.jager.batch.dto.JobExecutionParameterInputDTO;
import com.jager.batch.dto.JobRunHistory;
import com.jager.batch.entity.JobExecutionEntity;
import com.jager.batch.entity.JobExecutionParameterEntity;
import com.jager.batch.entity.JobInstanceEntity;
import com.jager.batch.repository.JobInstanceEntityRepository;
import com.jager.batch.service.rest.JobHistoryService;
import com.jager.batch.service.rest.JobRestService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class JobRestAPI {

	
	@Autowired
	JobHistoryService jobHistoryService;

	private final String JOB_PARAMETER_ID = "run.id";

	@GetMapping("/api/jobInfo")
	@ResponseBody
	public List<JobRunHistory> getHistory() {
		List<JobInstanceEntity> jobs = jobHistoryService.getRunHistory();
		List<JobRunHistory> jobRunHistories = new ArrayList<>();
		jobs.forEach(job -> {
			List<JobExecutionEntity> jobExecutions = job.getJobExecutions();
			if(!jobExecutions.isEmpty()){
				List<JobExecutionParameterEntity> parameters = jobExecutions.get(0).getParameters().stream().collect(Collectors.toList());
				JobExecutionParameterEntity jobExecutionParameterEntity = parameters.stream().filter(parameter -> parameter.getName().equals(JOB_PARAMETER_ID)).findFirst().orElse(null);
				JobRunHistory runHis = JobRunHistory.builder()
						.jobName(job.getJobName())
						.jobInstanceId(job.getId())
						.startTime(jobExecutions.get(0).getStartTime())
						.endTime(jobExecutions.get(0).getEndTime())
						.status(jobExecutions.get(0).getStatus())
						.runId(jobExecutionParameterEntity != null ? jobExecutionParameterEntity.getValue() : null)
						.build();
				jobRunHistories.add(runHis);
			}


		});
		return jobRunHistories;
	}

}