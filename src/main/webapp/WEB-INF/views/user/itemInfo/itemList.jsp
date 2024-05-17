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