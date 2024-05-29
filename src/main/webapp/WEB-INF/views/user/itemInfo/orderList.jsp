<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order List</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Order List</h1>
    <c:if test="${not empty orderList}">
        <table class="table table-bordered">
            <thead class="thead-light">
            <tr>
                <th scope="col">Order Code</th>
                <th scope="col">Order Date</th>
                <th scope="col">Status</th>
                <th scope="col">Item Names</th>
                <th scope="col">Quantities</th>
                <th scope="col">Prices</th>
                <th scope="col">Total Price</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orderList}">
                <tr>
                    <td>${order.orderCode}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.statusName}</td>
                    <td>${order.itemNames}</td>
                    <td>${order.orderQtys}</td>
                    <td>${order.itemPrices}</td>
                    <td>
                        <c:set var="totalPrice" value="0"/>
                        <c:forEach var="price" items="${fn:split(order.itemPrices, ', ')}" varStatus="loop">
                            <c:set var="qty" value="${fn:split(order.orderQtys, ', ')[loop.index]}"/>
                            <c:set var="totalPrice" value="${totalPrice + (price * qty)}"/>
                        </c:forEach>
                        ₩${totalPrice}
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/orderDetails?orderCode=${order.orderCode}" class="btn btn-primary btn-sm">Details</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty orderList}">
        <div class="alert alert-info" role="alert">
            No orders found.
        </div>
    </c:if>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
