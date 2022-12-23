package com.jager.batch.job.studentreport.entity;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentInfo {

	Integer id;
	
	String studentCode;
	
	String studentName;
	
	String studentEmail;
	
}
