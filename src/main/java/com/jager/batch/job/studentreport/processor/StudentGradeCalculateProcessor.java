package com.jager.batch.job.studentreport.processor;

import com.jager.batch.job.studentreport.client.StudentInfoRestClient;
import org.springframework.batch.item.ItemProcessor;

import com.jager.batch.job.studentreport.entity.ProcessedStudentGrade;
import com.jager.batch.job.studentreport.entity.StudentGrade;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class StudentGradeCalculateProcessor implements ItemProcessor<StudentGrade, ProcessedStudentGrade> {
//    @Autowired
//    StudentInfoRestClient studentInfoRestClient;
int abc =0;
    @Override
    public ProcessedStudentGrade process(StudentGrade studentGrade) throws Exception {
        System.out.println(studentGrade.getStudentCode()+" " + LocalDateTime.now());
    	ProcessedStudentGrade processedStudentGrade = new ProcessedStudentGrade();
    	Double sumScore = studentGrade.getOralTest() + studentGrade.getPopQuizTest()
    		+ studentGrade.getLessonTest()*2 + studentGrade.getSemesterTest()*3;

    	processedStudentGrade.setAverageGrade(sumScore/7);
        processedStudentGrade.setStudentCode(studentGrade.getStudentCode());

        if(sumScore<=5.5){
            processedStudentGrade.setResult("FAILED");
        }
        else{
            processedStudentGrade.setResult("PASSED");
        }

        return processedStudentGrade;
    }
}
