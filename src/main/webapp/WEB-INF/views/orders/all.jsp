<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<h1>Orders:</h1>
<table border="1">
    <tr>
        <th>Id</th>
        <th>User id</th>
        <th>User name</th>
        <th>Details</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}"/>
            </td>
            <td>
                <c:out value="${order.user.id}"/>
            </td>
            <td>
                <c:out value="${order.user.name}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/order?id=${order.id}">
                    details
                </a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/order/delete?id=${order.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/index">go to main</a>
</body>
</html>
