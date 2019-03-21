package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
    @Resource(name="jdbc/web_student_tracker")
    private DataSource datasource;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//step 1: set up print writer and set context type
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		//step 2: get a connection to db
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try{
			myConn=	datasource.getConnection();
		
		
		//step 3: create a sql statement
		String  sql="select * from student;";
		myStmt=myConn.createStatement();
		//step 4: execute the sql statement
		myRs=myStmt.executeQuery(sql);
		//step 5: process results set
		while(myRs.next()){
			String email=myRs.getString("email");
			String firstname=myRs.getString("first_name");
			String lastname=myRs.getString("last_name");
			 
			out.println(email+firstname+lastname);
			out.println("<br/>");
		}
		
		
		}
		catch(Exception exc){
			exc.printStackTrace();
		}	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
