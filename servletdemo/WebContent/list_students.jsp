	<%@ page import="java.util.*,com.luv2code.web.jdbc.*" %>
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

	<div id="wrapper">
		<div id="header">
			<h2>FooBar</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
	
	<!-- Put a new button : Add student -->
		<input type="button" value="Add Student" 
		onclick="window.location.href='add-student-form.jsp';return false;" 
		class="add-student-button"
		/>
		<table>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Action</th>
			
		</tr>
		
		<c:forEach var="tempStudent" items="${STUDENT_LIST}">
		
		<!-- set a link for each student -->
		<c:url var="tempLink" value="StudentControllerServlet">
		<c:param name="command" value="LOAD"></c:param>
		<c:param name="studentID" value="${tempStudent.id}"></c:param>
		
		</c:url>
		
		<!-- set a Del for each student -->
		<c:url var="delLink" value="StudentControllerServlet">
		<c:param name="command" value="DEL"></c:param>
		<c:param name="studentID" value="${tempStudent.id}"></c:param>
		
		</c:url>
		<tr>
		<td>${tempStudent.firstname}</td>
		<td>${tempStudent.lastname}</td>
		<td>${tempStudent.email}</td>
		<td><a href="${tempLink}">Update</a>
		| <a href="${delLink}"
		onclick="if (!(confirm('Are you sure you want to delete the student?'))) return false">
		Delete</a>
		</td>
		
		
		</tr>
		
		</c:forEach>
		
		</table>
		
		</div>
		
	
	
	</div>



</body>
</html>