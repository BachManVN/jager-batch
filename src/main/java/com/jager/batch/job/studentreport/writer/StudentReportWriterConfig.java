package com.jager.batch.job.studentreport.writer;

import com.jager.batch.job.studentreport.entity.ProcessedStudentGrade;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.jager.batch.job.studentreport.entity.StudentGradeReportRecord;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class StudentReportWriterConfig {

	@Value("${jager-batch.output-folder}")
	String path;
	private final String JOB_PARAMETER_ID = "run.id";
	@StepScope
	@Bean
	public FlatFileItemWriter<StudentGradeReportRecord> studentReportFileWriter(
			@Value("#{jobParameters['"+ JOB_PARAMETER_ID +"']}") String runId
	) {
		String reportName = "report"+ runId +".csv";
		Resource output = new FileSystemResource(path + reportName);
		return new FlatFileItemWriterBuilder<StudentGradeReportRecord>()
				.name("studentReportFileWriter")
				.resource(output)
				.delimited()
				.names(new String[]{"studentCode", "studentName", "studentEmail", "studentGrade", "result"})
				.headerCallback(writer -> writer.write("Code, Name, Email, Grade ,Result"))
				.build();
	}
}
