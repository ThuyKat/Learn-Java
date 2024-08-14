<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout Success</title>
</head>
<body>
<!-- <div class="invoice"> -->
        <h2>Checkout Successful!</h2>
        <p>Thank you for your purchase. Here are your invoice details:</p>

        <!-- <div class="invoice-details"> -->
            <table>
                <tr>
                    <th>Invoice Number:</th>
                    <td>${invoiceNumber}</td>
                </tr>
                <tr>
                    <th>Order Date:</th>
                    <td>${orderDate}</td>
                </tr>
                <tr>
                    <th>Paid Amount:</th>
                    <td>${paidAmount}</td>
                </tr>
            </table>
<!--         </div> -->

        <h3>Items Purchased:</h3>
        <ul>
            <c:forEach var="item" items="${purchasedItems}">
                <li>${item.product.name} - ${item.quantity} x ${item.product.price}</li>
            </c:forEach>
        </ul>

        <p>If you have any questions, please contact our support team.</p>
        <a href="/be6-web/Product">Continue Shopping</a>
   <!--  </div> -->

</body>
</html>