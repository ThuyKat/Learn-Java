<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<style>
.input-group{
display:flex;
flex-direction:column

}
</style>
<meta charset="UTF-8">
<title>Feedback</title>
</head>
<body>
<h2> <i>Feedbacks and discussions</i> </h2>
 <c:forEach var="feedback" items="${feedbacks}">  
 <fmt:formatDate value="${feedback.createAt}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedDate"/>
     <tr>
     		
         <td>${formattedDate} ${feedbackUsernames[feedback.id]} <i>wrote</i>: </td>
         <br>
         <td><b>${feedback.subject}</b></td>
         <br>
         <td> ${feedback.feedbackText}</td>
        
     </tr>
     <br>
     
 </c:forEach>
<hr>
<div class=container>
<form action="/be6-web/Feedback" method="post">

<input type="text" name="productId" value= ${param.productId} hidden=true>

<div class="input-group">
<label for="subject"> Subject line: </label>
<input type="text" id="subject" name="subject" required>
</div>
<br>
<div class="input-group">
<label for="feedback" >Comment:</label>
<textarea id="feedback" name="feedback" rows="5" required></textarea>
</div>
<br>
<div>
<input type =submit value=submit > 
</div>

</form>
</div>
</body>
</html>