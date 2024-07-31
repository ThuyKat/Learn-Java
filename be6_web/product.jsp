<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>

<jsp:include page="home.jsp"/>

<hr/>
<h1>All products</h1>

<table>
<tr>
<th>ID</th>
<th>Name</th>
<th>Price</th>
 </tr>
 <c:forEach var="product" items="${sessionScope.products}">
 <tr>
 <td>${product.id}</td>
 <td>${product.name}</td>
 <td>${product.price}</td>
</tr>
</c:forEach>
</table>
</body>
</html>