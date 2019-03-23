<%@ page import="java.util.*,com.luv2code.web.jdbc.*" %>

<!-- ABC -->
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<title>Student Tracker App</title>
<link type="text/css" rel="stylesheet" href="css/style.css">  
</head>
<%
//get the students from the request object
List<Student> theStudents=(List<Student>)request.getAttribute("STUDENT_LIST");
%>



<body>
<% 
out.println(theStudents);
out.println(request.getAttribute("STUDENT_LIST"));
out.println("***");
%>
	<div id="wrapper">
		<div id="header">
			<h2>FooBar</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
		
		<table>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			
		</tr>
		
		<c:forEach var="tempStudent" items="${STUDENT_LIST}">
		<tr>
		<td>${tempStudent.firstname}</td>
		<td>${tempStudent.lastname}</td>
		<td>${tempStudent.email}</td>
		</tr>
		
		</c:forEach>
		
		</table>
		
		</div>
		
	
	
	</div>



</body>
</html>