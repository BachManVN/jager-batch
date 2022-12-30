package com.jager.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jager.batch.entity.JobInstanceEntity;

import java.util.List;

@Repository
public interface JobInstanceEntityRepository extends JpaRepository<JobInstanceEntity, Long> {

    @Modifying
    @Query("delete from JobInstanceEntity e where e.id not in (select e2.jobInstance.id from JobExecutionEntity e2)")
    int deleteAllByIdNotInJobExecutionEntity();

    @Modifying
    @Query(nativeQuery = true, value = "SELECT * FROM BATCH_JOB_INSTANCE as job join BATCH_JOB_EXECUTION as jobE on job.JOB_INSTANCE_ID = jobE.JOB_INSTANCE_ID ORDER BY jobE.START_TIME DESC LIMIT 10")
    List<JobInstanceEntity> getJobsInfo();
}
