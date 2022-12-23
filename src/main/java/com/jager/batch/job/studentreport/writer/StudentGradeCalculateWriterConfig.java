package com.jager.batch.job.studentreport.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jager.batch.job.studentreport.entity.ProcessedStudentGrade;

@Configuration
public class StudentGradeCalculateWriterConfig {

    @Bean
    public JdbcBatchItemWriter<ProcessedStudentGrade> studentGradeCalculateJdbcWriter(
            @Qualifier("springDataSource") DataSource dataSource
    ) {
        return new JdbcBatchItemWriterBuilder<ProcessedStudentGrade>()
                .dataSource(dataSource)
                .sql("INSERT INTO STUDENT_GRADE_REPORT (student_code,student_grade,result) VALUES (?,?,?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();
    }

    private ItemPreparedStatementSetter<ProcessedStudentGrade> itemPreparedStatementSetter() {
        return new ItemPreparedStatementSetter<ProcessedStudentGrade>() {
            @Override
            public void setValues(ProcessedStudentGrade processedStudentGrade, PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, processedStudentGrade.getStudentCode());
                preparedStatement.setDouble(2, processedStudentGrade.getAverageGrade());
                preparedStatement.setString(3, processedStudentGrade.getResult());
            }
        };
    }

}
