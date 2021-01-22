<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Students</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">
</head>
<body>

<h2>Students</h2>

<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Semester</th>
    </tr>

    <c:forEach var="student" items="${students}">

        <tr>
            <td> ${student.firstName} </td>
            <td> ${student.lastName} </td>
            <td> ${student.semester} </td>
        </tr>

    </c:forEach>

</table>
<br>

<p>
    <a class="hyperlink" href="${pageContext.request.contextPath}">Go back</a>
</p>

</body>
</html>