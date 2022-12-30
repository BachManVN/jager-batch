package com.jager.batch.job.studentreport.writer;

import com.google.common.collect.Iterables;
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
import java.io.IOException;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class StudentReportWriterConfig {

	@Value("${jager-batch.output-folder}")
	String path;
	private static final String JOB_PARAMETER_ID = "run.id";
	private static final String IS_MANUAL_GRADING = "isManualGrading";
	@StepScope
	@Bean
	public FlatFileItemWriter<StudentGradeReportRecord> studentReportFileWriter(
			@Value("#{jobParameters['"+ JOB_PARAMETER_ID +"']}") String runId,
			@Value("#{jobParameters['"+ IS_MANUAL_GRADING +"']}") boolean isManualGrading

	) {
		String reportName = "report"+ runId +".csv";
		Resource output = new FileSystemResource(path + reportName);
		String [] colValues;
		if(!isManualGrading){
			colValues = new String[]{"studentCode", "studentName", "studentEmail", "studentGrade", "result"};
		}
		else{
			colValues = new String[]{"studentCode", "studentName", "studentEmail", "studentGrade"};
		}

		List<String> colNames;
		if(!isManualGrading){
			colNames = Arrays.asList("Code, Name, Email, Grade ,Result");
		}
		else{
			colNames = Arrays.asList("Code, Name, Email, Grade, Final Grade, Result");
		}

		return new FlatFileItemWriterBuilder<StudentGradeReportRecord>()
				.name("studentReportFileWriter")
				.resource(output)
				.delimited()
				.names(colValues)
				.headerCallback(writer -> writeHeader(writer,colNames))
				.build();
	}

	private void writeHeader(Writer writer, List<String> listStr){
		listStr.stream().forEach(str -> {
			try {
				writer.write(str);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
}
