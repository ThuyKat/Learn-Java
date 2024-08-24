package api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserApi extends HttpServlet {

	public UserApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
		resp.setHeader("Access-Control-Max-Age", "3600");
	}

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
//		// Set response content type
		response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET");
		response.setHeader("Access-Control-Allow-Headers", "*");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// Send a response back to the client
		PrintWriter out = response.getWriter();
		out.print("{\"status\":\"success\",\"message\":\"User registered successfully!\"}");
		out.flush();

		// Read JSON data from request body
		StringBuilder jsonBuffer = new StringBuilder();
		String line;
		try (BufferedReader reader = request.getReader()) {
			while ((line = reader.readLine()) != null) {
				jsonBuffer.append(line);
			}
		}

		// Remove the extra quotes at the beginning and end of the string
		String jsonString = jsonBuffer.toString();
		// Unescape the JSON string if it's enclosed in quotes and contains escaped
		// quotes
		if (jsonString.startsWith("\"") && jsonString.endsWith("\"")) {
			jsonString = jsonString.substring(1, jsonString.length() - 1).replace("\\\"", "\"");
		}
		try {
		// Convert JSON string to a Map
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonString, User.class);
		System.out.println(user);
		}catch(JsonProcessingException e) {
			throw new ServletException("Error parsing JSON", e);
		}

	}

}
