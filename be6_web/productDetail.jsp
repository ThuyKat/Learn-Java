<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date" %>


<!DOCTYPE html>
<html>
<head>
<!-- <style>
.product-action{
display:flex;
align-item:center;
}
</style> -->
<meta charset="UTF-8">
<title>Product Details</title>
</head>
<body>
 <jsp:include page="home.jsp"/>
<h1> ${fn:toUpperCase(product.name)} </h1>
<p><b>Category:</b> ${fn:toUpperCase(product.category)} </p>
<p><b>Description:</b> <i> ${product.description} </i></p>
<p><b>Price:</b> ${product.price} </p>

<!-- 	<div class="product-action"> -->
	<form action="/be6-web/Cart" method="post"  >
	    <input type="hidden" name="productId" value="${product.id}">
	    <input type ="hidden" name="returnURI" value="${returnURI}">
	    <input type="hidden" name="action" value="addToCart">
	    <button type="submit" >Add to Cart </button>
	</form>
	
	
	
	<%-- <input type="number" id="quantity" name="quantity" value="0" readonly>
	
	
	
	<form action="/Cart" method="post"  >
	    <input type="hidden" name="productId" value="${product.id}">
	    <input type="hidden" name="action" value="RemoveFromCart">
	    <button type="submit" >Remove From Cart </button>
	</form>
	
	</div> --%>
<hr>
<jsp:include page="productFeedback.jsp"/>

</body>
</html>