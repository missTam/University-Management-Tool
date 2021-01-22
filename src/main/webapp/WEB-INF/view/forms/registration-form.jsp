<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student Registration Form</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form.css">
</head>
<body>

<h2>Student Registration Form</h2>

<!-- Registration Form -->
<form:form action="${pageContext.request.contextPath}/register/process-registration-form"
           method="POST"
           modelAttribute="userDTO">

    <!-- Place for messages: error, alert etc ... -->

    <!-- Check for registration error -->
    <c:if test="${registrationError != null}">

        <div class="error">
                ${registrationError}
        </div>

    </c:if>

    <!-- Username -->
    <form:input path="username" placeholder="username (*)"/>
    <form:errors path="username" cssClass="error"/>
    <br>

    <!-- Password -->
    <form:input path="password" placeholder="password (*)"/>
    <form:errors path="password" cssClass="error"/>
    <br>

    <!-- Confirm Password -->
    <form:input path="matchingPassword" placeholder="confirm password (*)"/>
    <form:errors path="matchingPassword" cssClass="error"/>
    <br>

    <!-- First name -->
    <form:input path="firstname" placeholder="first name (*)"/>
    <form:errors path="firstname" cssClass="error"/>
    <br>

    <!-- Last name -->
    <form:input path="lastname" placeholder="last name (*)"/>
    <form:errors path="lastname" cssClass="error"/>
    <br>

    <!-- Email -->
    <form:input path="email" placeholder="email (*)"/>
    <form:errors path="email" cssClass="error"/>
    <br><br>

    <!-- Register Button -->
    <input type="submit" value="Register">

</form:form>

<p>
    <a class="hyperlink" href="${pageContext.request.contextPath}">Back to Homepage</a>
</p>

</body>
</html>
