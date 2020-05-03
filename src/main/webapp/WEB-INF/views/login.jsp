<%--
  Created by IntelliJ IDEA.
  User: Serhii
  Date: 03.05.2020
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login or register</h1>

<h4 style="color: red">${errMsg}</h4>

<form method="post" action="${pageContext.request.contextPath}/login">
    Login: <input type="text" name="login" value="<%=(request.getParameter("login") != null)
    ? request.getParameter("login") : ""%>">
    Password: <input type="password" name="pwd">

    <button type="submit">Login</button>
</form>
<button type="button">
    <a href="/registration">Register</a>
</button>
</body>
</html>
