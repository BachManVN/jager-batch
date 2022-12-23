package com.jager.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jager.batch.entity.JobInstanceEntity;

@Repository
public interface JobInstanceEntityRepository extends JpaRepository<JobInstanceEntity, Long> {

    @Modifying
    @Query("delete from JobInstanceEntity e where e.id not in (select e2.jobInstance.id from JobExecutionEntity e2)")
    int deleteAllByIdNotInJobExecutionEntity();
}
