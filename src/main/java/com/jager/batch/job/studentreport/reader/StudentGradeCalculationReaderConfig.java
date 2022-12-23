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

@Configuration
public class StudentGradeCalculationReaderConfig {

    @Bean
    public JdbcPagingItemReader<StudentGrade> studentGradeCalculationReader(
            @Qualifier("gradeDataSource") DataSource dataSource,
            PagingQueryProvider studentGradeCalculationQueryProvider
    ) {
        return new JdbcPagingItemReaderBuilder<StudentGrade>()
                .name("studentGradeCalculationReader")
                .dataSource(dataSource)
                .queryProvider(studentGradeCalculationQueryProvider)
                .pageSize(1)
                .rowMapper(new BeanPropertyRowMapper<StudentGrade>(StudentGrade.class))
                .build();
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean studentGradeCalculationQueryProvider(
            @Qualifier("gradeDataSource") DataSource dataSource
    ) {
        SqlPagingQueryProviderFactoryBean sqlPagingQueryProviderFactoryBean = new SqlPagingQueryProviderFactoryBean();
        sqlPagingQueryProviderFactoryBean.setDataSource(dataSource);
        sqlPagingQueryProviderFactoryBean.setSelectClause("select *");
        sqlPagingQueryProviderFactoryBean.setFromClause("from student_grade");
        sqlPagingQueryProviderFactoryBean.setSortKey("id");
        return sqlPagingQueryProviderFactoryBean;
    }

}
