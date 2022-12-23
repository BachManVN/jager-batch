package com.jager.batch.job.studentreport.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import com.jager.batch.job.studentreport.entity.ProcessedStudentGrade;
import com.jager.batch.job.studentreport.entity.StudentGrade;

public class StudentGradeGenerateClassifier implements Classifier<StudentGrade, ItemProcessor<?, ? extends ProcessedStudentGrade>> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6062332085423719422L;

	@Override
    public ItemProcessor<StudentGrade, ProcessedStudentGrade> classify(StudentGrade client) {
        return new StudentGradeCalculateProcessor();
    }
}
