package com.jager.batch.job.studentreport.step;

import com.jager.batch.job.studentreport.entity.ProcessedStudentGrade;
import com.jager.batch.job.studentreport.entity.StudentGrade;
import com.jager.batch.job.studentreport.entity.StudentGradeReportRecord;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentGradeReportStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step studentGradeReportStep(
            JdbcPagingItemReader<StudentGradeReportRecord> studentGradeReportReader,
            FlatFileItemWriter<StudentGradeReportRecord> studentReportFileWriter
    ) {
        return stepBuilderFactory
                .get("studentGradeReportStep")
                .<StudentGradeReportRecord, StudentGradeReportRecord>chunk(1)
                .reader(studentGradeReportReader)
                .writer(studentReportFileWriter)
                .build();
    }

}
