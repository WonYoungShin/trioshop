<%--
  Created by IntelliJ IDEA.
  User: dst06
  Date: 2024-05-17
  Time: 오후 5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="my-4">Shopping Cart</h1>
    <c:if test="${not empty cartItems}">
        <form method="post" action="/cart/checkout">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Item Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Subtotal</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="cartItem" items="${cartItems}">
                    <tr>
                        <td>${cartItem.itemName}</td>
                        <td>₩${cartItem.itemPrice}</td>
                        <td>
                            <form method="post" action="/cart/update/${cartItem.cartCode}">
                                <input type="number" name="quantity" value="${cartItem.cartItemQty}" min="1" class="form-control" style="width: 70px;">
                                <button type="submit" class="btn btn-sm btn-primary">Update</button>
                            </form>
                        </td>
                        <td>₩${cartItem.itemPrice * cartItem.cartItemQty}</td>
                        <td>
                            <form method="post" action="/cart/remove/${cartItem.cartCode}">
                                <button type="submit" class="btn btn-sm btn-danger">Remove</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="3" class="text-right"><strong>Total:</strong></td>
                    <td colspan="2">₩<c:out value="${totalPrice}" /></td>
                </tr>
                </tbody>
            </table>
            <button type="submit" class="btn btn-primary">Checkout</button>
        </form>
    </c:if>
    <c:if test="${empty cartItems}">
        <p>Your cart is empty. <a href="/items">Continue shopping</a></p>
    </c:if>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
