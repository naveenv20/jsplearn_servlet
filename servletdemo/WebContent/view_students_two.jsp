<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<h2>Student table Demo</h2>
<hr/>
<table border=1>
<tr> 
<th>First Name</th>
<th>Last Name</th>
<th>Email</th>
</tr>
<c:forEach var="temp" items="${student_list}">
<tr>
<td>${temp.firstname}</td>
<td>${temp.lastname}</td>
<td>${temp.email}</td>
</tr>
</c:forEach>
</table>
</body>

</html>