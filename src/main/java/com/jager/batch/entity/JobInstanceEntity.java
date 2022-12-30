package com.jager.batch.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BATCH_JOB_INSTANCE")
public class JobInstanceEntity {

    @Id
    @Column(name = "JOB_INSTANCE_ID", updatable = false, insertable = false)
    private long id;

    @Column(name = "VERSION", updatable = false, insertable = false)
    private Long version;

    @Column(name = "JOB_NAME", updatable = false, insertable = false)
    private String jobName;

    @Column(name = "JOB_KEY", updatable = false, insertable = false)
    private String jobKey;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "jobInstance")
    private List<JobExecutionEntity> jobExecutions;

}
