package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import db.Product;
import db.ProductDatabaseUtil;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            // Fetch all products using ProductDatabaseUtil
            List<Product> products = ProductDatabaseUtil.getAllProduct();

            // Set the response type to JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            request.setAttribute("products", products);
            request.getRequestDispatcher("/WEB-INF/product.jsp").forward(request, response);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
