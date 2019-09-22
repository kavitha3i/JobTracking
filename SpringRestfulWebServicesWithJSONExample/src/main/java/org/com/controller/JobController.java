package org.com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.com.bean.JobDetail;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
	JdbcPostgresqlConnection databaseCon = new JdbcPostgresqlConnection();

	@RequestMapping(value = "/jobdetails/create", method = RequestMethod.POST)
	public @ResponseBody JobDetail createJob(@RequestBody JobDetail job) {

		JobDetail inputJob = new JobDetail();
		inputJob.setDate(new Date());
		inputJob.setJobDesc(job.getJobDesc());
		inputJob.setFullTime(job.getFullTime());
		inputJob.setPartTime(job.getPartTime());
		inputJob.setNameOfOrg(job.getNameOfOrg());
		System.out.println(job.getJobDesc());

		String jobDetails = inputJob.getJobDesc() + ","
				+ inputJob.getNameOfOrg()+","+inputJob.getFullTime()+","+inputJob.getPartTime()+inputJob.getDate().toString();
		System.out.println(jobDetails);

		JobProducer producerJob = new JobProducer();
		producerJob.kafkamethod(jobDetails);

		JobConsumer consumeJob = new JobConsumer();
		consumeJob.kafkaConsume();

		return inputJob;

	}

	@RequestMapping(value = "/jobdetails/search", method = RequestMethod.POST)
	public @ResponseBody List<JobDetail> getJob(@RequestBody JobDetail job)
			throws ClassNotFoundException {

		Map<String, String> getJob = new HashMap<String, String>();

		getJob.put("Fulltime", job.getFullTime());
		getJob.put("JobDesc", job.getJobDesc());
		getJob.put("NameOfOrg", job.getNameOfOrg());
		getJob.put("PartTime", job.getPartTime());
		String quereyJoin = "";

		for (Map.Entry<String, String> getJobs : getJob.entrySet()) {

			if (getJobs.getValue() != null && !getJobs.getValue().isEmpty()) {

				quereyJoin = quereyJoin + "\"" + getJobs.getKey() + "\""
						+ " = " + "\'" + getJobs.getValue() + "\'" + " and ";
			}
		}
		System.out.println(quereyJoin);
		System.out.println(getJob.size());

		JdbcPostgresqlConnection finalCon = new JdbcPostgresqlConnection();
		List<JobDetail> finalResult = finalCon.getJobDetailFromSql(quereyJoin);

		return finalResult;

	}

}
