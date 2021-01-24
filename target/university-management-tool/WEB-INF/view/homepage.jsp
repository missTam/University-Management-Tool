<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Homepage</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/homepage.css">
</head>
<body>

<header>
    <h2>Welcome to the University Management Tool</h2>
</header>

<main>

    <div class="intro">
        <h2>Manage your lectures:</h2>

        <p>Already have account?</p>
        <p><a href="${pageContext.request.contextPath}/login">Login</a></p>

        <p>No Account?</p>
        <p><a href="${pageContext.request.contextPath}/register/show-registration-form">
            Registration for Students</a></p>

    </div>

    <div class="more-info">
        <h2>More Info:</h2>

        <div class="columns">
            <div class="column">
                <h3>Lectures</h3>
                <a class="hyperlink" href="${pageContext.request.contextPath}/list/lectures">Available lectures at the university</a>
            </div>

            <div class="column">
                <h3>Professors</h3>
                <a class="hyperlink" href="${pageContext.request.contextPath}/list/professors">Who's working at the university?</a>
            </div>

            <div class="column">
                <h3>Students</h3>
                <a class="hyperlink" href="${pageContext.request.contextPath}/list/students">Who's studying at the university?</a>
            </div>
        </div>

    </div>

</main>

</body>
</html>
