package com.jager.batch.job.studentreport;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class StudentGradeReportJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job studentGradeReportJob(Step studentReportCleanUpStep,Step studentGradeCalculateStep,Step studentInfoInputStep,Step studentGradeReportStep) {
        return jobBuilderFactory
                .get("studentGradeReportJob")
                .incrementer(new RunIdIncrementer())
                .start(studentReportCleanUpStep)
                .next(studentInfoInputStep)
                .next(studentGradeCalculateStep)
                .next(studentGradeReportStep)
                .build();
    }
}
