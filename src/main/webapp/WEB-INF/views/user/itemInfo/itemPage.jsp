<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Item Details</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="my-4">${item.itemName}</h1>
    <div class="row">
        <div class="col-md-6">
            <img class="img-fluid" src="${item.itemSrc}" alt="${item.itemName}">
        </div>
        <div class="col-md-6">
            <h3>₩${item.itemPrice}</h3>
            <p><strong>Category:</strong> ${item.categoryName}</p>
            <p><strong>Factory:</strong> ${item.factoryName}</p>
            <p><strong>Stock Quantity:</strong> ${item.stockQty}</p>
            <button class="btn btn-primary">Add to Cart</button>
        </div>
    </div>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
