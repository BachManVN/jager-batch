package com.jager.batch.job.studentreport.step;

import com.jager.batch.job.studentreport.reader.StudentInfoInputReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jager.batch.job.studentreport.entity.StudentInfo;

@Configuration
public class StudentInfoInputStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step studentInfoInputStep(
            ItemReader<StudentInfo> studentInfoInputReader,
            ItemWriter<StudentInfo> studentInfoInputJdbcWriter
    ) {
        return stepBuilderFactory
                .get("studentInfoInputStep")
                .<StudentInfo,StudentInfo>chunk(100)
                .reader(studentInfoInputReader)
                .writer(studentInfoInputJdbcWriter)
                .faultTolerant()
                .build();
    }

}
