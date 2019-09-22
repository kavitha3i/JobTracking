package org.com.bean;

import java.util.Date;

public class JobDetail {

	String jobDesc;
	String nameOfOrg;
	Date date;
	String partTime;
	String fullTime;
	
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public String getNameOfOrg() {
		return nameOfOrg;
	}
	public void setNameOfOrg(String nameOfOrg) {
		this.nameOfOrg = nameOfOrg;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPartTime() {
		return partTime;
	}
	public void setPartTime(String partTime) {
		this.partTime = partTime;
	}
	public String getFullTime() {
		return fullTime;
	}
	public void setFullTime(String fullTime) {
		this.fullTime = fullTime;
	}
	
}
