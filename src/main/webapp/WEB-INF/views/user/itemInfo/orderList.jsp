<%--
  Created by IntelliJ IDEA.
  User: dst06
  Date: 2024-05-17
  Time: 오후 5:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<html>
<head>
    <title>orderList</title>
</head>
<body>
<div class="container mt-5">
    <h2>주문 내역</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>주문 코드</th>
            <th>주문 날짜</th>
            <th>상태</th>
            <th>상품 코드</th>
            <th>상품 이름</th>
            <th>수량</th>
            <th>가격</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orderList}">
            <tr>
                <td>${order.orderCode}</td>
                <td>${order.orderDate}</td>
                <td>${order.statusName}</td>
                <td>${order.itemCode}</td>
                <td>${order.itemName}</td>
                <td>${order.orderQty}</td>
                <td>${order.itemPrice}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
