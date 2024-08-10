<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
</head>
<body>

<!-- LOGGED IN USER -->
<%-- <% if (request.getAttribute("username") != null){ %> --%>
<% if (session.getAttribute("username") != null){ %>

<a href ="/be6-web/Logout">LOG OUT</a>

<!--  OPTION 1: Params in URL  -->
 
 <%-- Welcome <%= request.getParameter("username")%> ! --%>
 
 <!--  OPTION 2: create a new POST method -->

<%-- <p>Welcome ${param.username}!</p> --%>

<!-- OPTION 3: requestDispatcher--> 
<br>
<%-- Welcome <%= request.getAttribute("username")%>  --%>

<!--  OPTION 4: use session scope -->
   Welcome <%= session.getAttribute("username").toString() %> !  
  
<!--   LOGGED OUT USER -->
  
<%} else { %>

<a href ="/be6-web/Login">LOG IN</a>
<br>
Welcome user!

<%} %>

<br>
<a href = "/be6-web/Product"> PRODUCT</a>
<form action ="/be6-web/Cart" method="get">
 <!-- <input type="hidden" name="action" value="viewCart"> -->
<button type="submit">Cart (${sessionScope.countItem})</button>
   

</form>
</body>
</html>