package com.jager.batch.job.studentreport.processor;

import org.springframework.batch.item.ItemProcessor;

import com.jager.batch.job.studentreport.entity.ProcessedStudentGrade;
import com.jager.batch.job.studentreport.entity.StudentGrade;

public class StudentGradeCalculateProcessor implements ItemProcessor<StudentGrade, ProcessedStudentGrade> {
    @Override
    public ProcessedStudentGrade process(StudentGrade studentGrade) throws Exception {
    	ProcessedStudentGrade processedStudentGrade = new ProcessedStudentGrade();
    	Double sumScore = studentGrade.getOralTest() + studentGrade.getPopQuizTest()
    		+ studentGrade.getLessonTest()*2 + studentGrade.getSemesterTest()*3;

        if(sumScore<=5.5){
            processedStudentGrade.setResult("FAILED");
        }
        else{
            processedStudentGrade.setResult("PASSED");
        }

    	processedStudentGrade.setAverageGrade(sumScore/7);
        processedStudentGrade.setStudentCode(studentGrade.getStudentCode());
        return processedStudentGrade;
    }
}
