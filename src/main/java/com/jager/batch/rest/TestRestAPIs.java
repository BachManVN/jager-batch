package com.jager.batch.rest;

import com.jager.batch.dto.JobExecutionDTO;
import com.jager.batch.dto.JobExecutionParameterInputDTO;
import com.jager.batch.service.rest.JobRestService;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestRestAPIs {
	@Autowired
	JobRestService jobRestService;
	
	@Autowired
	JobOperator jobOperator;
	
//	@Autowired
//	@Qualifier("helloWorld")
//	Job job;
	
	
//	@PostMapping("/api/startJob")
//	public List<JobExecutionParameterInputDTO> adminAccess() {
		
//		System.out.println("job "+ job.getName());
//		jobOperator.getJobNames().forEach(name ->{
//			System.out.println("name job "+ name);
//		});
//
//		List<JobExecutionParameterInputDTO> listParas = new LinkedList<JobExecutionParameterInputDTO>();
//		listParas.add(new JobExecutionParameterInputDTO().builder().name("isDocked").value("true").build());
		//listParas.add(new JobExecutionParameterInputDTO().builder().name("runtId").value(System.currentTimeMillis()+"").build());
		//jobRestService.start("studentGradeReportJob",listParas);

//		jobRestService.start("studentGradeReportJob",listParas);
//		return listParas;
//	}

	@PostMapping("/jobs/{jobName}")
	public ResponseEntity<?> startJob(@PathVariable("jobName") String jobName,
									  @RequestBody HashMap<String,String> jobParams){
		List<JobExecutionParameterInputDTO> listParas = new LinkedList<JobExecutionParameterInputDTO>();
		for(Map.Entry<String, String> entry : jobParams.entrySet()) {
			listParas.add(new JobExecutionParameterInputDTO().builder()
					.name(entry.getKey()).value(entry.getValue()).build());
		}
		JobExecutionDTO jobExecutionDTO = jobRestService.start(jobName,listParas);
		return new ResponseEntity<>(jobExecutionDTO, HttpStatus.OK);
	}
}