package com.jager.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.support.AutomaticJobRegistrar;
import org.springframework.batch.core.configuration.support.JobLoader;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.ReferenceJobFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldBatchJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    
    @Autowired
    JobRegistry jobRegistry;
    
    
    @Bean(name="helloWorld")
    public Job sayHelloWorld(Step stepToSayHelloWorld) throws DuplicateJobException {
    	Job job = jobBuilderFactory
                .get("helloWorld")
                .start(stepToSayHelloWorld)
                .incrementer(new RunIdIncrementer())
                .build();
    	return job;
    }
}
