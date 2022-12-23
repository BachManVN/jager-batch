package com.jager.batch.entity;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "BATCH_JOB_EXECUTION")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobExecutionEntity {

    @Id
    @Column(name = "JOB_EXECUTION_ID", updatable = false, insertable = false)
    private long id;

    @Column(name = "VERSION", updatable = false, insertable = false)
    private Long version;

    @Column(name = "CREATE_TIME", updatable = false, insertable = false)
    private LocalDateTime createTime;

    @Column(name = "START_TIME", updatable = false, insertable = false)
    private LocalDateTime startTime;

    @Column(name = "END_TIME", updatable = false, insertable = false)
    private LocalDateTime endTime;

    @Column(name = "LAST_UPDATED", updatable = false, insertable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "STATUS", updatable = false, insertable = false)
    private String status;

    @Column(name = "EXIT_CODE", updatable = false, insertable = false)
    private String exitCode;

    @Column(name = "EXIT_MESSAGE", updatable = false, insertable = false)
    private String exitMessage;

    @Column(name = "JOB_CONFIGURATION_LOCATION", updatable = false, insertable = false)
    private String jobConfigurationLocation;

    @Column(name = "CREATE_BY", updatable = false, insertable = false)
    private String createBy;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JOB_INSTANCE_ID", updatable = false, insertable = false)
    private JobInstanceEntity jobInstance;

    @OneToMany(mappedBy = "jobExecutionId", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<JobExecutionParameterEntity> parameters;

    @OneToMany(mappedBy = "jobExecutionId", orphanRemoval = true)
    private List<JobStepExecutionEntity> steps;

    @OneToMany(mappedBy = "id", orphanRemoval = true)
    private List<JobExecutionContextEntity> contexts;

}
