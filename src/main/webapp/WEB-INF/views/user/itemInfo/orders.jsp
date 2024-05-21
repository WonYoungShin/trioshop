<%--
  Created by IntelliJ IDEA.
  User: dst06
  Date: 2024-05-17
  Time: 오후 5:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Items</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="my-4">Order Items</h1>
    <form method="post" action="/confirmOrder">
        <div class="row">
            <c:forEach var="item" items="${orderItemList}">
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100">
                        <img class="card-img-top" src="${item.itemSrc}" alt="${item.itemName}">
                        <div class="card-body">
                            <h4 class="card-title">${item.itemName}</h4>
                            <h5>₩${item.itemPrice}</h5>
                        </div>
                        <input type="hidden" name="itemCode" value="${item.itemCode}">
                        <input type="hidden" name="itemName" value="${item.itemName}">
                        <input type="hidden" name="itemPrice" value="${item.itemPrice}">
                    </div>
                </div>
            </c:forEach>
        </div>
        <button type="submit" class="btn btn-primary">Confirm Order</button>
    </form>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
