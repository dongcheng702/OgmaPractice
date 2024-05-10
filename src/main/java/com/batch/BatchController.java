package com.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Component
public class BatchController {

    private final Job job;
    private final JobLauncher jobLauncher;

    @Autowired
    public BatchController(Job job, JobLauncher jobLauncher) {
        this.job = job;
        this.jobLauncher = jobLauncher;
    }

    @GetMapping("/runBatch")
//    @Scheduled(cron = "0 55 18 * * ?") // Fire at 9:15 AM every day
    public String runBatch() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
            .addLong("timestamp", System.currentTimeMillis())
            .toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        return "Batch job started with id: " + jobExecution.getId();
    }
}
