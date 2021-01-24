<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/homepage.css">
</head>
<body>

<header>
    <h2>Welcome to the University Management Tool</h2>
</header>

<main>

    <div class="intro">

        <form:form action="${pageContext.request.contextPath}/authenticate-user" method="POST">

            <c:if test="${param.error != null}">
                <p class="error">Invalid username/ password.</p>
            </c:if>

            <c:if test="${param.logout != null}">
                <p class="success">You have been logged out.</p>
            </c:if>

            <input type="text" name="username" placeholder="username" />
            <br>
            <input type="text" name="password" placeholder="password" />
            <br><br>

            <input type="submit" value="Login">
        </form:form>

        <p><a href="${pageContext.request.contextPath}">Back to Homepage</a></p>

    </div>

</body>
</html>
