<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lectures</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">
</head>
<body>

<h2>Lectures</h2>

<table>
    <tr>
        <th>Title</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Credits</th>
        <th>Lecturer</th>
    </tr>

    <c:forEach var="lecture" items="${lectures}">

        <tr>
            <td> ${lecture.name} </td>
            <td>
                <c:choose>
                    <c:when test="${empty lecture.endDate}">
                        TBD
                    </c:when>
                    <c:otherwise>
                        ${lecture.startDate}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${empty lecture.endDate}">
                        TBD
                    </c:when>
                    <c:otherwise>
                        ${lecture.endDate}
                    </c:otherwise>
                </c:choose>
            </td>
            <td> ${lecture.credits} </td>
            <td>
                <c:choose>
                    <c:when test="${empty lecture.professor}">
                        TBD
                    </c:when>
                    <c:otherwise>
                        ${lecture.professor.firstName} ${lecture.professor.lastName}
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>

    </c:forEach>
</table>
<br>

<p>
    <a class="hyperlink" href="${pageContext.request.contextPath}">Go back</a>
</p>

</body>
</html>