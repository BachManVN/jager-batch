package com.jager.batch.service.rest;

import com.jager.batch.entity.JobInstanceEntity;
import com.jager.batch.repository.JobInstanceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobHistoryService {


    private final JobInstanceEntityRepository jobInstanceEntityRepository;

    @Autowired
    public JobHistoryService(final JobInstanceEntityRepository repo) {
        this.jobInstanceEntityRepository = repo;
    }

    public List<JobInstanceEntity> getRunHistory (){
        return jobInstanceEntityRepository.getJobsInfo();
    }
}
