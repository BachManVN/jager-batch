package com.jager.batch.job.studentreport.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentGrade {

	Integer id;
	String studentCode;
	Double oralTest;
	Double popQuizTest;
	Double lessonTest;
	Double semesterTest;
}
