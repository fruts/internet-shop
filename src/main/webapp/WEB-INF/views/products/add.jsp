<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New product</title>
</head>
<body>
<h1>Put product details below</h1>


<form method="post" action="${pageContext.request.contextPath}/products/add">
    Name: <input type="text" name="name">
    Price: <input type="number" name="price">

    <button type="submit">add</button>
</form>
</body>
</html>
