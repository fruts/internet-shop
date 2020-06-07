<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Login</h1>

<h4 style="color: red">${errMsg}</h4>

<form method="post" action="${pageContext.request.contextPath}/login">
    Login: <input type="text" name="login" value="<%=(request.getParameter("login") != null)
    ? request.getParameter("login") : ""%>">
    Password: <input type="password" name="pwd">

    <button type="submit">Login</button>
</form>
    <a href="/registration">Register</a>
</body>
</html>
