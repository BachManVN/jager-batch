package com.jager.batch.job;

import com.jager.batch.job.studentreport.client.StudentInfoRestClient;
import com.jager.batch.job.studentreport.entity.StudentInfo;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SayHelloWorldTasklet implements Tasklet {
    @Autowired
    private StudentInfoRestClient studentInfoRestClient;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Hello, World!");
        for(StudentInfo info : studentInfoRestClient.getAllStudentInfo()){
            System.out.println("studnet "+info.getStudentCode());
        }
        return RepeatStatus.FINISHED;
    }

}
