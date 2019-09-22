package org.com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.com.bean.JobDetail;
import org.springframework.util.StringUtils;

public class JdbcPostgresqlConnection {
	/*public static void main (String[] args) throws ClassNotFoundException{*/

	public List<JobDetail> getJobDetailFromSql(String sd) throws ClassNotFoundException{
		List<JobDetail> jobList = new ArrayList<JobDetail>();
	
        Connection conn = null;
        try {
        	Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres","postgres","kavitha");
            if (conn != null) {
                System.out.println("Connected to database");
            }
           
           String subQuer = org.apache.commons.lang3.StringUtils.substring(sd, 0, sd.length()-5);
            String query = "select * from \"jobDetail\" where " + subQuer;
            System.out.println(query);
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            	JobDetail jobs = new JobDetail();
            	jobs.setNameOfOrg(rs.getString("NameOfOrg"));
            	jobs.setJobDesc(rs.getString("JobDesc"));
            	jobs.setFullTime(rs.getString("FullTime"));
            	jobs.setPartTime(rs.getString("PartTime"));
            	jobs.setDate(rs.getDate("Date"));
            	 jobList.add(jobs);
            	         	
            }
          //addlist  
           
            }catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
		return jobList;
	//}
	}
}
