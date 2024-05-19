<%--
  Created by IntelliJ IDEA.
  User: SWY
  Date: 2024-05-18-018
  Time: 오후 1:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shop All Items</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<!-- 네비게이션 바 -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">MyShop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="/login">로그인</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/join">회원가입</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/orderlist/${usercode}">주문내역</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/itemlist">상품조회</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/cart/${usercode}">장바구니</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">로그아웃</a>
            </li>
        </ul>
    </div>
</nav>

<!-- 메인 콘텐츠 -->
<div class="container">
    <h1 class="my-4">All Items</h1>
    <div class="row">
        <c:forEach var="item" items="${itemList}">
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card h-100">
                    <img class="card-img-top" src="${item.itemSrc}" alt="${item.itemName}">
                    <div class="card-body">
                        <h4 class="card-title">${item.itemName}</h4>
                        <h5>₩${item.itemPrice}</h5>
                        <p class="card-text">
                            Category: ${item.categoryName}<br>
                            Factory: ${item.factoryName}<br>
                            Stock Quantity: ${item.stockQty}
                        </p>
                    </div>
                    <div class="card-footer">
                        <button class="btn btn-primary">Add to Cart</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>