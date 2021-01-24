<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student Dashboard</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">
</head>
<body>

<h2>Your Dashboard, ${student.firstName}</h2>

<h4>Registered Lectures:</h4>

<table>
    <tr>
        <th>Title</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Credits</th>
        <th>Lecturer</th>
        <th>Change status</th>
    </tr>

    <c:forEach var="lecture" items="${student.lectures}">

        <tr>
            <td> ${lecture.name} </td>
            <td> ${lecture.startDate} </td>
            <td> ${lecture.endDate} </td>
            <td> ${lecture.credits} </td>
            <td> ${lecture.professor} </td>
            <td>
                <input type="button" value="Deregister" onclick="window.location.href='deregister'; return false;">
            </td>
        </tr>

    </c:forEach>
</table>

<h4>Unregistered Lectures:</h4>

<table>
    <tr>
        <th>Title</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Credits</th>
        <th>Lecturer</th>
        <th>Change status</th>
    </tr>

    <c:forEach var="lecture" items="${lectures}">

        <tr>
            <td> ${lecture.name} </td>
            <td> ${lecture.startDate} </td>
            <td> ${lecture.endDate} </td>
            <td> ${lecture.credits} </td>
            <td>
                <c:choose>
                    <c:when test="${empty lecture.professor}">
                        Coming soon
                    </c:when>
                    <c:otherwise>
                        ${lecture.professor.firstName} ${lecture.professor.lastName}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <input type="button" value="Register" onclick="window.location.href='register'; return false;">
            </td>
        </tr>

    </c:forEach>
</table>
<br>

<input type="button" value="Logout" onclick="window.location.href='logout'; return false;">

</body>
</html>
