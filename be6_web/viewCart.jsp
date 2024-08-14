<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style> 
</head>
<body>
    <h1>Cart Details</h1>
    
    <c:if test="${empty cart.items}">
        <p>Your cart is empty.</p>
    </c:if>
    
    <c:if test="${not empty cart.items}">
        <table>
            <thead>
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Subtotal</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${cart.items}">
                    <tr>
                        <td>${item.product.name}</td>
                        <td>${item.quantity}</td>
                        <td>$<fmt:formatNumber value="${item.product.price}" pattern="#,##0.00"/></td>
                        <td>$<fmt:formatNumber value="${item.quantity * item.product.price}" pattern="#,##0.00"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <h2>Total: $<fmt:formatNumber value="${cart.totalPrice}" pattern="#,##0.00"/></h2>
    </c:if>
    
    <a href="/be6-web/Product">Continue Shopping</a>
    <form method="post" action="/be6-web/Checkout">
    <input type="submit" value="Checkout" />
    </form>
</body>
</html>