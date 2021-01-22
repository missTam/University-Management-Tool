<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Student Dashboard</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">
</head>
<body>

<h2>Your Dashboard, ${sessionScope.student.firstname} </h2>

<h4>Your Registered Lectures:</h4>

<c:choose>
    <c:when test="${empty sessionScope.student.lectures}">
        <p class="emphasized"> You are not enrolled in any lecture. </p>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>Title</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Credits</th>
                <th>Lecturer</th>
                <th>Change status</th>
            </tr>

            <c:forEach var="lecture" items="${sessionScope.student.lectures}">

                <tr>
                    <td> ${lecture.name} </td>
                    <td> ${lecture.startDate} </td>
                    <td> ${lecture.endDate} </td>
                    <td> ${lecture.credits} </td>
                    <td> ${lecture.professor.firstName} ${lecture.professor.lastName} </td>
                    <td>
                        <form:form action="${pageContext.request.contextPath}/student-dashboard/disenroll" method="POST">
                            <input type="hidden" name="lectureId" value="${lecture.id}">
                            <input type="submit" value="Disenroll" id="disenrollBtn" onclick="if(!(confirm('Are you sure?'))) return false">
                        </form:form>
                    </td>
                </tr>

            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>

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
            <td> ${lecture.professor.firstName} ${lecture.professor.lastName} </td>
            <td>
                <form:form action="${pageContext.request.contextPath}/student-dashboard/enroll"
                           method="POST">
                    <input type="hidden" name="lectureId" value="${lecture.id}">
                    <input type="submit" value="Enroll" id="enrollBtn">
                </form:form>
            </td>
        </tr>

    </c:forEach>
</table>
<br>

<form:form action="${pageContext.request.contextPath}/logout" method="POST">
    <input type="submit" value="Logout">
</form:form>

</body>
</html>
