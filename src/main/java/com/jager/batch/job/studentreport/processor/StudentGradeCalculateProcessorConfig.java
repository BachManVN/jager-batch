package com.jager.batch.job.studentreport.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jager.batch.job.studentreport.entity.ProcessedStudentGrade;
import com.jager.batch.job.studentreport.entity.StudentGrade;

@Configuration
public class StudentGradeCalculateProcessorConfig {

    @Bean
    public ItemProcessor<StudentGrade, ProcessedStudentGrade> studentGradeCalculateProcessor() {
        return new ClassifierCompositeItemProcessorBuilder<StudentGrade, ProcessedStudentGrade>()
                .classifier(new StudentGradeGenerateClassifier())
                .build();
    }
}
