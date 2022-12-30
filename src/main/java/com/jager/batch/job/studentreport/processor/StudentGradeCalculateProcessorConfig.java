package com.jager.batch.job.studentreport.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jager.batch.job.studentreport.entity.ProcessedStudentGrade;
import com.jager.batch.job.studentreport.entity.StudentGrade;

@Configuration
public class StudentGradeCalculateProcessorConfig {

    private static final String IS_SIMULATION = "isSimulation";
    @Bean
    @StepScope
    public ItemProcessor<StudentGrade, ProcessedStudentGrade> studentGradeCalculateProcessor(
            @Value("#{jobParameters['"+ IS_SIMULATION +"']}") boolean isSimulation
    ) {
        StudentGradeCalculateProcessor gradeCalculateProcessor = new StudentGradeCalculateProcessor();
        gradeCalculateProcessor.setSimulation(isSimulation);
        return gradeCalculateProcessor;
    }
}
