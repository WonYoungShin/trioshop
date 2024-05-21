<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품등록</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="my-4">상품등록</h1>
    <form action="" method="post">
        <div class="form-group">
            <label for="itemName">상품이름</label>
            <input type="text" class="form-control" id="itemName" name="itemName" required/>
        </div>
        <div class="form-group">
            <label for="categoryCode">상품분류</label>
            <input type="text" class="form-control" id="categoryCode" name="categoryCode" required/>
        </div>
        <div class="form-group">
            <label for="factoryCode">제조업체</label>
            <input type="text" class="form-control" id="factoryCode" name="factoryCode" required/>
        </div>
        <div class="form-group">
            <label for="itemSize">상품사이즈</label>
            <input type="text" class="form-control" id="itemSize" name="itemSize" required/>
        </div>
        <div class="form-group">
            <label for="itemColor">상품컬러</label>
            <input type="text" class="form-control" id="itemColor" name="itemColor" required/>
        </div>
        <div class="form-group">
            <label for="itemPrice">판매가격</label>
            <input type="number" class="form-control" id="itemPrice" name="itemPrice" required/>
        </div>
        <button type="submit" class="btn btn-primary">등록</button>
        <button type="button" class="btn btn-secondary" onclick="location.href='/trioAdmin'">취소</button>
    </form>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
