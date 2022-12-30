package com.jager.batch.job.studentreport.reader;

import com.jager.batch.job.studentreport.client.StudentInfoRestClient;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class StudentInfoInputReader<StudentInfo> implements ItemReader<StudentInfo> {

    @Autowired
    private StudentInfoRestClient studentInfoRestClient;

    private List<StudentInfo> studentInfo;

    private int currentIndex;

    private StudentInfo getCurrentStudentInfo(){
        if(!checkAvailableStudentInfo()){
            return null;
        }
        if(studentInfo.size()  <= currentIndex){
            return null;
        }

        return studentInfo.get(currentIndex);
    }

    private boolean checkAvailableStudentInfo(){
        if(studentInfo == null){
            studentInfo = (List<StudentInfo>) studentInfoRestClient.getAllStudentInfo();
        }
        if(studentInfo==null || studentInfo.size() == 0){
            return false;
        }
        return true;
    }

    @Override
    public StudentInfo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException
    {
        StudentInfo studentInfo = getCurrentStudentInfo();
        currentIndex++;
        return studentInfo;
    }
}