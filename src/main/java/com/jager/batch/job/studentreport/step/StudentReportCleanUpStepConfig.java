package com.jager.batch.job.studentreport.step;

import com.jager.batch.job.studentreport.entity.StudentGradeReportRecord;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentReportCleanUpStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step studentReportCleanUpStep(JdbcPagingItemReader<StudentGradeReportRecord> studentGradeReportReader,
                                               JdbcBatchItemWriter<StudentGradeReportRecord> studentReportCleanUpWriter) {
        return stepBuilderFactory
                .get("studentReportCleanUpStep")
                .<StudentGradeReportRecord,StudentGradeReportRecord>chunk(100)
                .reader(studentGradeReportReader)
                .writer(studentReportCleanUpWriter)
                .build();
    }
}
