package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


	public void addStudent(Student thestudent) throws Exception{
		
		Connection myConn=null;
		PreparedStatement myStmt=null;
		
		try {
		
		
		// get the connection 
			myConn=datasource.getConnection();
			
		//create sql for insert
		String sql="insert into student "
					+"(first_name, last_name, email)"
					+"values (?, ?, ?)";
		myStmt=myConn.prepareStatement(sql);
		//set the param values  for the student
		myStmt.setString(1, thestudent.getFirstname());
		myStmt.setString(2, thestudent.getLastname());
		myStmt.setString(3, thestudent.getEmail());
		//execute the sql insert
		myStmt.execute();
		
		
			
		} 
		finally  {
			//clean up jdbc object
			close(myConn,myStmt,null);
		}
	}


	public Student getStudents(String thestudentid) throws Exception {
		
		Student theStudent=null;
		Connection myConn=null;
		PreparedStatement  myStmt=null;
		ResultSet myRs=null;
		int studentId;
		
		try {
		//convert studentid to int
			studentId=Integer.parseInt(thestudentid);
		//get the connection
			myConn=datasource.getConnection();
		//create sql to get selected student
			String sql="Select * from student where id=?";
			
		//create prepared statement
			myStmt=myConn.prepareStatement(sql);
		//set parameters
			myStmt.setInt(1,studentId);
		//execute the query
			myRs=myStmt.executeQuery();
		//retrieve data from result set
			if(myRs.next()) {
				String firstname=myRs.getString("first_name");
				String lastname=myRs.getString("last_name");
				String email=myRs.getString("email");
			
			theStudent=new Student(studentId, firstname, lastname, email);
			}
			else {
				throw new Exception("Could not found student id: "+ studentId);
			}
		return theStudent;
	}finally {
		close(myConn, myStmt, myRs);
	}
	
	}


	public void updateStudent(Student theStudent) throws Exception {
		
		Connection myConn=null;
		PreparedStatement  myStmt=null;
		
		
		
		try {
			//get db connection
			myConn=datasource.getConnection();
			
			//create sql statetment
			String sql="update student "
					+ "set first_name=?,last_name=?,email=? "
					+ "where id=?";
			//prepare statement
			myStmt=myConn.prepareStatement(sql);
			//set parameter
			myStmt.setString(1,theStudent.getFirstname());
			myStmt.setString(2,theStudent.getLastname());
			myStmt.setString(3,theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
		System.out.println("Hello");	
			//execute the sql
			myStmt.execute();
			
		}
		finally {
		close(myConn, myStmt, null);	
		}
		
	}


	public void deleteStudent(int id) throws Exception {
		// TODO Auto-generated method stub
		Connection myConn=null;
		PreparedStatement  myStmt=null;
		try{
			//get the connection
			myConn=datasource.getConnection();
			//prepare the sql statement
			String sql="delete from student where id=?";
			myStmt=myConn.prepareStatement(sql);
			//set parameters
			myStmt.setInt(1, id);
			//execute query 
			myStmt.execute();
			//System.out.println("Done");
		}
		finally{
		close(myConn, myStmt, null);	
		}
	}
	
	
	
	
}
