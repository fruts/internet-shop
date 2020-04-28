<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping cart</title>
</head>
<body>
<h1>Products in shopping cart</h1>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.id}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href="/deleteproductfromshoppingcart?id=${product.id}">X</a>
            </td>
        </tr>
    </c:forEach>
</table>
<button type="button">
    <a href="/order/complete">order</a>
</button>
<button type="button">
    <a href="/index">go to main</a>
</button>
</body>
</html>