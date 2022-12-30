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
                .sql("UPDATE STUDENT_GRADE_REPORT set student_grade = ?, result = ? where student_code = ?")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .assertUpdates(true)
                .build();
    }

    private ItemPreparedStatementSetter<ProcessedStudentGrade> itemPreparedStatementSetter() {
        return new ItemPreparedStatementSetter<ProcessedStudentGrade>() {
            @Override
            public void setValues(ProcessedStudentGrade processedStudentGrade, PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setDouble(1, processedStudentGrade.getAverageGrade());
                preparedStatement.setString(2, processedStudentGrade.getResult());
                preparedStatement.setString(3, processedStudentGrade.getStudentCode());
            }
        };
    }

}
