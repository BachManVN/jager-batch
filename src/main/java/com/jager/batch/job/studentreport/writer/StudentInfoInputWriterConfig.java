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

import com.jager.batch.job.studentreport.entity.StudentInfo;

@Configuration
public class StudentInfoInputWriterConfig {

    @Bean
    public JdbcBatchItemWriter<StudentInfo> studentInfoInputJdbcWriter(
            @Qualifier("springDataSource") DataSource dataSource
    ) {
        return new JdbcBatchItemWriterBuilder<StudentInfo>()
                .dataSource(dataSource)
                .sql("UPDATE STUDENT_GRADE_REPORT SET student_name = ?, student_email= ?  WHERE student_code = ?")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .assertUpdates(false)
                .build();
    }

    private ItemPreparedStatementSetter<StudentInfo> itemPreparedStatementSetter() {
        return new ItemPreparedStatementSetter<StudentInfo>() {
            @Override
            public void setValues(StudentInfo studentInfo, PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, studentInfo.getStudentName());
                preparedStatement.setString(2, studentInfo.getStudentEmail());
                preparedStatement.setString(3, studentInfo.getStudentCode());
            }
        };
    }

}
