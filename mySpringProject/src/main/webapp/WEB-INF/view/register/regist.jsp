<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-09-25
  Time: 오후 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>regist</title>
</head>
<body>
    <form:form modelAttribute="registerRequest">
        <label>
            ID:
            <form:input path="id"/>
            <form:errors path="id"/>
        </label><br/>
        <label>
            PW:
            <form:input path="password"/>
            <form:errors path="password"/>
        </label><br/>
        <label>
            PW Confirm:
            <form:input path="passwordConfirm"/>
            <form:errors path="passwordConfirm"/>
        </label>

        <br/>
        <label>
            Email:
            <form:input path="email"/>
            <form:errors path="email"/>
        </label><br/>
        <input type="submit" value="submit" name="submit"></input>
    </form:form>
<%--    <form action="done" method="post">--%>
<%--        <label>--%>
<%--            ID: <input type="text" name="id" id="id" value="${registerRequest.id}">--%>
<%--        </label><br/>--%>
<%--        <label>--%>
<%--            PW: <input type="text" name="password" id="password" value="${registerRequest.password}">--%>
<%--        </label><br/>--%>

<%--        <label>--%>
<%--            PW Confirm: <input type="text" name="passwordConfirm" id="passwordConfirm" value="${registerRequest.passwordConfirm}">--%>
<%--        </label><br/>--%>
<%--        <label>--%>
<%--            Email: <input type="text" name="email" id="email" value="${registerRequest.email}">--%>
<%--        </label><br/>--%>
<%--        <input type="submit" value="Submit">--%>
<%--    </form>--%>
</body>
</html>
