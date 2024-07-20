<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="login.jsp" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <input type="submit" value="Login">
    </form>

    <%
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username != null && password != null) {
            Connection connection = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            boolean authenticated = false;
            
            try {
                // Database connection
                String url = "jdbc:mysql://localhost:3306/be6_DB";
                String dbUsername = "root";
                String dbPassword = "root";
                connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                
                // Query to get user
                String query = "SELECT * FROM users WHERE username = ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, username);
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    if (storedPassword.equals(password)) {
                        authenticated = true;
                    }
                }
                
                if (authenticated) {
                    out.println("<h3>Login Successful</h3>");
                    out.println("<p>Welcome, " + username + "!</p>");
                } else {
                    out.println("<h3>Login Failed</h3>");
                    out.println("<p>Invalid username or password.</p>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
                if (connection != null) try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    %>
</body>
</html>
