<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>재고</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="my-4">재고 목록</h1>
    <div class="row mb-4">
        <div class="col-md-12 d-flex justify-content-end">
            <!-- 검색창 및 카테고리 선택 항목 결합 -->
            <form class="form-inline" method="get" action="">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="itemName" value="${param.itemName}">
                <select class="form-control mr-sm-2" name="category">
                    <option value="">Select Category</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.categoryCode}" <c:if test="${param.category == category.categoryCode}">selected</c:if>>${category.categoryName}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>상품코드</th>
                    <th>상품이름</th>
                    <th>카테고리</th>
                    <th>판매가격</th>
                    <th>재고수량</th>
                    <th>제조업체</th>
                    <th>사이즈</th>
                    <th>상품색깔</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${stockList}">
                    <tr>
                        <td><a href="/trioAdmin/stock/${item.itemCode}">${item.itemCode}</a></td>
                        <td>${item.itemName}</td>
                        <td>${item.categoryName}</td>
                        <td>${item.itemPrice}</td>
                        <td>${item.stockQty}</td>
                        <td>${item.factoryCode}</td>
                        <td>${item.itemSize}</td>
                        <td>${item.itemColor}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
