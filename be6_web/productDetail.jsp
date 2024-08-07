<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Details</title>
</head>
<body>
 <jsp:include page="home.jsp"/>
<h1> ${fn:toUpperCase(product.name)} </h1>
<p><b>Category:</b> ${fn:toUpperCase(product.category)} </p>
<p><b>Description:</b> <i> ${product.description} </i></p>
<p><b>Price:</b> ${product.price} </p>
<hr>
<jsp:include page="productFeedback.jsp"/>

</body>
</html>