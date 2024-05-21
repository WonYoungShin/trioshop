<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>발주 목록</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="my-4">발주 목록</h1>
    <div class="row">
        <table>
            <thead>
                <th>발주번호</th>
                <th>상품코드</th>
                <th>발주수량</th>
                <th>제조업체</th>
                <th>상품이름</th>
                <th>카테고리</th>
                <th>사이즈</th>
                <th>상품색깔</th>
            </thead>
            <tbody>
            <c:forEach var="item" items="${purchaseList}">
                <tr>
                    <td>${item.purchaseCode}</td>
                    <td>${item.itemCode}</td>
                    <td>${item.purchaseQty}</td>
                    <td>${item.factoryCode}</td>
                    <td>${item.itemName}</td>
                    <td>${item.categoryName}</td>
                    <td>${item.itemSize}</td>
                    <td>${item.itemColor}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>