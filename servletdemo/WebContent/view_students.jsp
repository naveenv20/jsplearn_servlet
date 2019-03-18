<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>

<c:forEach var="temp" items="${student_list}">
${temp} <br/>
</c:forEach>

</body>

</html>