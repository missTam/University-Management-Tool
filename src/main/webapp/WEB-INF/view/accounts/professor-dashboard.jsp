<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Professor Dashboard</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">
</head>
<body>

<h2>Your Dashboard, ${sessionScope.professor.firstname} </h2>

<h4>Your Registered Lectures:</h4>

<c:choose>
    <c:when test="${empty sessionScope.professor.lectures}">
        <p class="emphasized"> No registered lectures! </p>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>Title</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Credits</th>
                <th>Change status</th>
            </tr>

            <c:forEach var="lecture" items="${sessionScope.professor.lectures}">

                <tr>
                    <td> ${lecture.name} </td>
                    <td> ${lecture.startDate} </td>
                    <td> ${lecture.endDate} </td>
                    <td> ${lecture.credits} </td>
                    <td>
                        <form:form action="${pageContext.request.contextPath}/professor-dashboard/deregister-lecture" method="POST">
                            <input type="hidden" name="lectureId" value="${lecture.id}">
                            <input type="submit" value="Deregister" id="deregisterBtn" onclick="if(!(confirm('Are you sure?'))) return false">
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
            <td>
                <c:choose>
                    <c:when test="${empty lecture.professor}">
                        <div class="emphasized">Open</div>
                    </c:when>
                    <c:when test="${lecture.professor.firstName == sessionScope.professor.firstname
                    and lecture.professor.lastName == sessionScope.professor.lastname}">
                        <div class="emphasized">You</div>
                    </c:when>
                    <c:otherwise>
                        ${lecture.professor.firstName} ${lecture.professor.lastName}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${empty lecture.professor}">
                        <form:form action="${pageContext.request.contextPath}/professor-dashboard/register-lecture" method="POST">
                            <input type="hidden" name="lectureId" value="${lecture.id}">
                            <input type="submit" value="Register" id="registerLectureBtn">
                        </form:form>
                    </c:when>
                    <c:otherwise></c:otherwise>
                </c:choose>
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
