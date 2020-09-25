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
    <title>register</title>
</head>
<body>
    <form action="done" method="post">
        <label>
            ID: <input type="text" name="id" id="id" value="${registerRequest.id}">
        </label><br/>
        <label>
            PW: <input type="text" name="password" id="password" value="${registerRequest.password}">
        </label><br/>

        <label>
            PW Confirm: <input type="text" name="passwordConfirm" id="passwordConfirm" value="${registerRequest.passwordConfirm}">
        </label><br/>
        <label>
            Email: <input type="text" name="email" id="email" value="${registerRequest.email}">
        </label><br/>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
