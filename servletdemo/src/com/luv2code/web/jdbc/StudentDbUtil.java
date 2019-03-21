package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource datasource;
	
	public StudentDbUtil(DataSource theDataSource) {
		datasource=theDataSource;
	}
	
	
	public List<Student> getStudents() throws Exception{
		List<Student> students=new ArrayList<>();
		Connection myConn=null;
		Statement  myStmt=null;
		ResultSet myRs=null;
		try {
		//get a connection
		myConn=datasource.getConnection();
		
		// create sql statement
		String sql="select * from student order by last_name;";
		myStmt=myConn.createStatement();
		//execute query
		myRs=myStmt.executeQuery(sql);
		//process resultset
		while(myRs.next()) {
			// retrieve  data from result set row
			int id =myRs.getInt("id");
			String firstname=myRs.getString("first_name");
			String lastname=myRs.getString("last_name");
			String email=myRs.getString("email");
			//create  new student object 
			
			Student tempStudent=new Student(id,firstname, lastname, email);
			
			//add it to out list of students
			
			students.add(tempStudent);
		}
		
		
		
		
			
		return students;
		}
		finally {
			//close JDBC objects
			close(myConn,myStmt,myRs);
		}
		
	}


	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		try {
			if(myRs!=null) {
				myRs.close();
			}
			if(myStmt!=null) {
				myStmt.close();
			}
			if(myConn!=null) {
				myConn.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}