package com.fin.eteller.controller;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    ConfigurableApplicationContext ctx;

    @RequestMapping("/transactionJob")
    public String runTransactionJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        UUID id = UUID.randomUUID();
        JobParametersBuilder jobParams = new JobParametersBuilder();
        jobParams.addString("id",id.toString());
        jobLauncher.run(ctx.getBean("transactionJob",Job.class), jobParams.toJobParameters());
        return "TransactionJob is executed!";
    }

}
