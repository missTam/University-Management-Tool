<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Professors</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">
</head>
<body>

<h2>Professors</h2>

<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Expertise</th>
        <th>Room</th>
    </tr>

    <c:forEach var="professor" items="${professors}">

        <tr>
            <td> ${professor.firstName} </td>
            <td> ${professor.lastName} </td>
            <td> ${professor.expertise} </td>
            <td> ${professor.room} </td>
        </tr>

    </c:forEach>

</table>
<br>

<p>
    <a class="hyperlink" href="${pageContext.request.contextPath}">Go back</a>
</p>

</body>
</html>
