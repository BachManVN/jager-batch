package com.jager.batch.job.studentreport.entity;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentGradeReportRecord {
	Integer id;

	String studentCode;
	
	String studentName;
	
	String studentEmail;
	
	Double studentGrade;
	
	String result;
}
