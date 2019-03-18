package com.luv2code.servletdemo.mvctwo;

import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {

	public static List<Student> getStudents(){
		
		//This is for *******MODEL************ sample
		//create empty list
		
		List<Student> students= new ArrayList<Student>();
		//add sample data
		students.add(new Student("sai","ram","sai.ram@gmail.com"));
		students.add(new Student("sai","baba","sai.baba@gmail.com"));
		students.add(new Student("sai","krish","sai.krish@gmail.com"));
		// return list
		return students;
			
	}
	
	
}
