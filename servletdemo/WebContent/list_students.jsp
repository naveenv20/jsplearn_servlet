<%@ page import="java.util.*,com.luv2code.web.jdbc.*" %>
<!DOCTYPE html>

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

<%=theStudents %>

</body>
</html>