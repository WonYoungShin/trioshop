<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
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
    <div class="row mb-4">
        <div class="col-md-12 d-flex justify-content-end">
            <!-- 검색창 및 카테고리 선택 항목 결합 -->
            <form class="form-inline" method="get" action="/searchItems">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="searchText">
                <select class="form-control mr-sm-2" name="categoryName">
                    <option value="">Select Category</option>
                    <c:forEach var="categoryName" items="${categoryList}">
                        <option value="${categoryName}">${categoryName}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </div>
    <div class="row">
        <c:forEach var="item" items="${itemList}">
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card h-100" onclick="redirectToItemPage(${item.itemCode})">
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
                <form id="itemForm-${item.itemCode}" method="post" action="/item/${item.itemCode}">
                    <input type="hidden" name="itemCode" value="${item.itemCode}">
                    <input type="hidden" name="itemName" value="${item.itemName}">
                    <input type="hidden" name="itemPrice" value="${item.itemPrice}">
                    <input type="hidden" name="itemSrc" value="${item.itemSrc}">
                    <input type="hidden" name="categoryName" value="${item.categoryName}">
                    <input type="hidden" name="factoryName" value="${item.factoryName}">
                    <input type="hidden" name="stockQty" value="${item.stockQty}">
                </form>
            </div>
        </c:forEach>
    </div>
</div>

<script>
    function redirectToItemPage(itemCode) {
        const form = document.getElementById('itemForm-' + itemCode);
        form.submit();
    }
</script>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
