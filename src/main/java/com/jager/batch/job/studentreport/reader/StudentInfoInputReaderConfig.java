package com.jager.batch.job.studentreport.reader;

import com.jager.batch.job.studentreport.client.StudentInfoRestClient;
import com.jager.batch.job.studentreport.entity.StudentInfo;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentInfoInputReaderConfig {


    @Bean
    public ItemReader<StudentInfo> studentInfoInputReader() {
        return new StudentInfoInputReader();
    }
}
