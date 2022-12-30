package com.jager.batch.rest;

import com.jager.batch.dto.JobExecutionParameterInputDTO;
import com.jager.batch.service.rest.JobRestService;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestRestAPIs {
	@Autowired
	JobRestService jobRestService;
	
	@Autowired
	JobOperator jobOperator;
	
	@Autowired
	@Qualifier("helloWorld")
	Job job;
	
	
	@PostMapping("/api/startJob")
	public List<JobExecutionParameterInputDTO> adminAccess() {
		
		System.out.println("job "+ job.getName());
		jobOperator.getJobNames().forEach(name ->{
			System.out.println("name job "+ name);
		});
		
		List<JobExecutionParameterInputDTO> listParas = new LinkedList<JobExecutionParameterInputDTO>();
		listParas.add(new JobExecutionParameterInputDTO().builder().name("isDocked").value("true").build());
		//listParas.add(new JobExecutionParameterInputDTO().builder().name("runtId").value(System.currentTimeMillis()+"").build());
		//jobRestService.start("studentGradeReportJob",listParas);
		jobRestService.start("studentGradeReportJob",listParas);
		return listParas;
	}
}