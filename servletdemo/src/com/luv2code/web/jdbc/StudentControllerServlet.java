package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private  StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//create our student db  util  and pass in the coon pool / datasource
		
		try {
			studentDbUtil=new StudentDbUtil(dataSource);
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			//read the command parameter and route if accordingly  
			
			String thecommand=request.getParameter("command");
			
			
			//if the command is missing  ,then simply route to listing page
			
			if(thecommand==null) {
				thecommand="LIST";
			}
			
			
			//route to appropiate method 
			switch (thecommand) {
			case "LIST":
				//list the students in .... MVC fashion
				 listStudents(request,response);
				
				break;
			case "ADD":
				//list the students in .... MVC fashion
				 addStudents(request,response);
				
				break;

			default:
				 listStudents(request,response);
			}
		
		
		}catch (Exception e) {
			throw new ServletException(e);
		}
	
		
	}

	private void addStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//read the student info from form data
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		//create a new student object 
		
		Student thestudent=new Student(firstname, lastname, email);
		
		//add the student to the database
		studentDbUtil.addStudent(thestudent);
		
		//send back to the main page ( the updated student list again display) 
		listStudents(request,response);
		
		
		
		
		
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//get the students from Dbutil 
		List<Student> students=studentDbUtil.getStudents();
		//add students to request object 
		request.setAttribute("STUDENT_LIST", students);
		//send to JSP (view )
		RequestDispatcher dispatcher=request.getRequestDispatcher("list_students.jsp");
		dispatcher.forward(request, response);
	}

	
	
	
	
}
