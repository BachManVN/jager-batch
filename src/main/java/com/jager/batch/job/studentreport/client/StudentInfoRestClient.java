package com.jager.batch.job.studentreport.client;

import com.jager.batch.job.studentreport.entity.StudentInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class StudentInfoRestClient {

    public List<StudentInfo> getAllStudentInfo(){
        RestTemplate restTemplate = new RestTemplate();
        StudentInfo[] infos = restTemplate.getForObject("http://localhost:8081/api/students", StudentInfo[].class);
        return Arrays.asList(infos);
    }

    public Integer getPlusScore(){
        RestTemplate restTemplate = new RestTemplate();
        Integer plus = restTemplate.getForObject("http://localhost:6969/api/plusscore", Integer.class);
        return plus;
    }
}
