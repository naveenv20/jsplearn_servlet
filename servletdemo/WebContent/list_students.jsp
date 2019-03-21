<%@ page import="java.util.*,com.luv2code.web.jdbc.*" %>


<html>
<head>
<title>Student Tracker App</title>
</head>
<%
//get the students from the request object
List<Student> theStudents=(List<Student>)request.getAttribute("STUDENT_LIST");
%>



<body>

<%=theStudents %>

</body>
</html>