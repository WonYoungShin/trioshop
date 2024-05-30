<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 목록</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- jQuery 링크 -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style>
        th, .btn {
            white-space: nowrap;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-4">발주 목록</h1>
    <div class="row mb-4">
        <div class="col-md-12 d-flex justify-content-end">
            <!-- 검색창 및 카테고리 선택 항목 결합 -->
            <form class="form-inline" method="get" action="">
                <input class="form-control mr-sm-2" type="search" placeholder="아이템 이름" aria-label="Search" name="itemName" value="${param.itemName}">
                <select class="form-control mr-sm-2" name="category">
                    <option value="">카테고리 선택</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.categoryCode}" <c:if test="${param.category == category.categoryCode}">selected</c:if>>${category.categoryName}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
            </form>
        </div>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>발주번호</th>
            <th>상품코드</th>
            <th>발주수량</th>
            <th>제조업체</th>
            <th>상품이름</th>
            <th>가격</th>
            <th>카테고리</th>
            <th>사이즈</th>
            <th>상품색깔</th>
            <th>발주총액</th>
            <th>선택</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${purchaseList}">
            <tr>
                <td>${item.purchaseCode}</td>
                <td>${item.itemCode}</td>
                <td>${item.purchaseQty}</td>
                <td>${item.factoryCode}</td>
                <td>${item.itemName}</td>
                <td><fmt:formatNumber value="${item.purchasePrice}" type="currency" currencySymbol="" /></td>
                <td>${item.categoryName}</td>
                <td>${item.itemSize}</td>
                <td>${item.itemColor}</td>
                <td><fmt:formatNumber value="${item.purchaseQty * item.purchasePrice}" type="currency" currencySymbol="" /></td>
                <td>
                    <button type="button" class="btn btn-primary"
                            onclick="selectItem('${item.itemCode}', '${item.itemName}', '${item.categoryCode}', '${item.factoryCode}', '${item.itemSize}', '${item.itemColor}', '${item.purchasePrice}', '${item.purchaseQty}', '${item.purchaseCode}')">
                        선택
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function selectItem(itemCode, itemName, categoryCode, factoryCode, itemSize, itemColor, purchasePrice, purchaseQty, purchaseCode) {
        window.opener.setDetails(itemCode, itemName, categoryCode, factoryCode, itemSize, itemColor, purchasePrice, purchaseQty, purchaseCode);
        window.close();
    }
</script>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
