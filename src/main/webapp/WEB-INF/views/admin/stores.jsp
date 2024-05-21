<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>입고</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-4">입고</h1>
    <form action="" method="post">
        <div class="form-group">
            <label for="itemCode">상품코드</label>
            <input type="text" class="form-control" id="itemCode" name="itemCode" required/>
        </div>
        <%--    <div>--%>
        <%--        <label for="itemName">상품이름</label>--%>
        <%--        <input type="text" id="itemName" name="itemName"/>--%>
        <%--    </div>--%>
        <%--    <div>--%>
        <%--        <label for="categoryCode">상품분류</label>--%>
        <%--        <input type="text" id="categoryCode" name="categoryCode"/>--%>
        <%--    </div>--%>
        <%--    <div>--%>
        <%--        <label for="factoryCode">제조업체</label>--%>
        <%--        <input type="text" id="factoryCode" name="factoryCode"/>--%>
        <%--    </div>--%>
        <%--    <div>--%>
        <%--        <label for="itemSize">상품사이즈</label>--%>
        <%--        <input type="text" id="itemSize" name="itemSize"/>--%>
        <%--    </div>--%>
        <%--    <div>--%>
        <%--        <label for="itemColor">상품컬러</label>--%>
        <%--        <input type="text" id="itemColor" name="itemColor"/>--%>
        <%--    </div>--%>


        <div class="form-group">
            <label for="storePrice">입고가격</label>
            <input type="text" class="form-control" id="storePrice" name="storePrice" required/>
        </div>
        <div class="form-group">
            <label for="storeQty">입고수량</label>
            <input type="text" class="form-control" id="storeQty" name="storeQty" required/>
        </div>
        <%--    <div>--%>
        <%--        <label for="purchaseCode">발주번호</label>--%>
        <%--        <input type="text" id="purchaseCode" name="purchaseCode"/>--%>
        <%--    </div>--%>
        <button type="submit" class="btn btn-primary">입고</button>
        <button type="button" class="btn btn-secondary" onclick="location.href='/trioAdmin'">취소</button>
    </form>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
