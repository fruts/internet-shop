<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Put your user details below</h1>

<h4 style="color: red">${message}</h4>

<form method="post" action="${pageContext.request.contextPath}/registration">
    Name: <input type="text" name="name" value="<%=(request.getParameter("name") != null)
    ? request.getParameter("name") : ""%>">
    Login: <input type="text" name="login" value="<%=(request.getParameter("login") != null)
    ? request.getParameter("login") : ""%>">
    Password: <input type="password" name="pwd">
    Repeat password: <input type="password" name="pwd-rep">

    <button type="submit">register</button>
</form>
</body>
</html>
