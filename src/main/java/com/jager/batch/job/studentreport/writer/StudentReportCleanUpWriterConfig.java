package com.jager.batch.job.studentreport.writer;

import com.jager.batch.job.studentreport.entity.StudentGradeReportRecord;
import com.jager.batch.job.studentreport.entity.StudentInfo;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class StudentReportCleanUpWriterConfig {

    @Bean
    public JdbcBatchItemWriter<StudentGradeReportRecord> studentReportCleanUpJdbcWriter(
            @Qualifier("springDataSource") DataSource dataSource
    ) {
        return new JdbcBatchItemWriterBuilder<StudentGradeReportRecord>()
                .dataSource(dataSource)
                .sql("Delete from STUDENT_GRADE_REPORT where id = ? ")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .assertUpdates(false)
                .build();
    }

    private ItemPreparedStatementSetter<StudentGradeReportRecord> itemPreparedStatementSetter() {
        return new ItemPreparedStatementSetter<StudentGradeReportRecord>() {
            @Override
            public void setValues(StudentGradeReportRecord studentGradeReportRecord, PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, studentGradeReportRecord.getId().toString());
            }
        };
    }

}
