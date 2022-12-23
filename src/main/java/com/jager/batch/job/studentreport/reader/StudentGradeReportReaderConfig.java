package com.jager.batch.job.studentreport.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.jager.batch.job.studentreport.entity.StudentGrade;
import com.jager.batch.job.studentreport.entity.StudentGradeReportRecord;

@Configuration
public class StudentGradeReportReaderConfig {

    @Bean
    public JdbcPagingItemReader<StudentGradeReportRecord> studentGradeReportReader(
            @Qualifier("springDataSource") DataSource dataSource,
            PagingQueryProvider studentGradeReportQueryProvider
    ) {
        return new JdbcPagingItemReaderBuilder<StudentGradeReportRecord>()
                .name("studentGradeReportReader")
                .dataSource(dataSource)
                .queryProvider(studentGradeReportQueryProvider)
                .pageSize(1)
                .rowMapper(new BeanPropertyRowMapper<StudentGradeReportRecord>(StudentGradeReportRecord.class))
                .build();
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean studentGradeReportQueryProvider(
            @Qualifier("springDataSource") DataSource dataSource
    ) {
        SqlPagingQueryProviderFactoryBean sqlPagingQueryProviderFactoryBean = new SqlPagingQueryProviderFactoryBean();
        sqlPagingQueryProviderFactoryBean.setDataSource(dataSource);
        sqlPagingQueryProviderFactoryBean.setSelectClause("select *");
        sqlPagingQueryProviderFactoryBean.setFromClause("from STUDENT_GRADE_REPORT");
        sqlPagingQueryProviderFactoryBean.setSortKey("id");
        return sqlPagingQueryProviderFactoryBean;
    }

}
