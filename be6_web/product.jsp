<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products</title>
    
</head>
<body>
    <jsp:include page="home.jsp"/>
    <hr/>
    <h3>All Products</h3>
    
    <ul>
        <li><a href="Product?category=all">All </a></li>
        <li><a href="Product?category=food">Food</a></li>
        <li><a href="Product?category=drink">Drink</a></li>
    </ul>
    <c:choose>
        <c:when test="${not empty requestScope.products}"> <!-- specified condition -->
            <table>
                <tr>
                    
                    <th>Name</th>
                    <th>Price</th>
                </tr>
                <c:forEach var="product" items="${products}">
               
                    <tr>
                        
                        <td>${product.name}</td>
                        <td>${product.price}</td>
                        <td>
                        
                        <a href ="ProductDetail?productId=${product.id }">Product Details</a>
                    	</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>No products available.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>