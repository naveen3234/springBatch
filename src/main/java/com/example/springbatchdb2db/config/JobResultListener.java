package com.example.springbatchdb2db.config;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobResultListener implements JobExecutionListener {

	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Called beforeJob().");
	}

	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED ) {
			System.out.println("job success...");
	    }
	    else if (jobExecution.getStatus() == BatchStatus.FAILED) {
			System.out.println("job failures...");
	    }
	}
}