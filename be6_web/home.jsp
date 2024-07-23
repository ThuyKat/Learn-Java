<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- LOGGED IN USER -->
<% if (request.getAttribute("username") != null){ %>

<a href ="logout.jsp">LOG OUT</a>

<!--  OPTION 1: Params in URL  -->
 
 <%-- Welcome <%= request.getParameter("username")%> ! --%>
 
 <!--  OPTION 2: create a new POST method -->

<%-- <p>Welcome ${param.username}!</p> --%>

<!-- OPTION 3: requestDispatcher--> 
<br>
Welcome <%= request.getAttribute("username")%> 

<!--  OPTION 4: use session scope -->
  <%--  Welcome <%= session.getAttribute("username").toString() %> !  --%>
  
<!--   LOGGED OUT USER -->
  
<%} else { %>

<a href ="login2.jsp">LOG IN</a>
<br>
Welcome user!

<%} %>

<br>
<a href = "product.html"> go to product page here</a>
</body>
</html>