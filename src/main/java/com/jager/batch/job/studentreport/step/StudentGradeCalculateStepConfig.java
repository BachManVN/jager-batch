package com.jager.batch.job.studentreport.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jager.batch.job.studentreport.entity.ProcessedStudentGrade;
import com.jager.batch.job.studentreport.entity.StudentGrade;

@Configuration
public class StudentGradeCalculateStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step studentGradeCalculateStep(
            JdbcPagingItemReader<StudentGrade> studentGradeCalculationReader,
            ItemProcessor<StudentGrade, ProcessedStudentGrade> studentGradeCalculateProcessor,
            JdbcBatchItemWriter<ProcessedStudentGrade> studentGradeCalculateJdbcWriter
    ) {
        return stepBuilderFactory
                .get("studentGradeCalculateStep")
                .<StudentGrade, ProcessedStudentGrade>chunk(1)
                .reader(studentGradeCalculationReader)
                .processor(studentGradeCalculateProcessor)
                .writer(studentGradeCalculateJdbcWriter)
                .build();
    }

}
