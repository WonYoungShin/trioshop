<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자 페이지</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-4">관리자 페이지</h1>
    <div class="btn-group" role="group" aria-label="관리자 버튼 그룹">
        <button type="button" class="btn btn-primary" onclick="location.href='/trioAdmin/stock'">재고</button>
        <button type="button" class="btn btn-secondary" onclick="location.href='/trioAdmin/stores'">입고</button>
        <button type="button" class="btn btn-secondary" onclick="location.href='/trioAdmin/storesList'">입고내역</button>
        <button type="button" class="btn btn-success" onclick="location.href='/trioAdmin/purchase'">발주</button>
        <button type="button" class="btn btn-success" onclick="location.href='/trioAdmin/purchaseList'">발주내역</button>
        <button type="button" class="btn btn-info" onclick="location.href='/trioAdmin/addItem'">상품등록</button>
    </div>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
