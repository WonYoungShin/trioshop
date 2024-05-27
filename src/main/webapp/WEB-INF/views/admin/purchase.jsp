<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>발주</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- jQuery 링크 -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<%@ include file="adminSidebar.jsp" %>
<div class="container mt-4">
    <h1 class="mb-4 text-center">발주</h1>
    <form action="" method="post">
        <div class="form-row justify-content-center">
            <div class="form-group col-md-6">
                <label for="itemCode">상품코드</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="itemCode" name="itemCode" required readonly/>
                    <div class="input-group-append">
                        <button type="button" class="btn btn-secondary" onclick="openPopup()">선택</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-row justify-content-center">
            <div class="form-group col-md-6">
                <label for="itemName">상품이름</label>
                <input type="text" class="form-control" id="itemName" name="itemName" readonly/>
            </div>
        </div>
        <div class="form-row justify-content-center">
            <div class="form-group col-md-6">
                <label for="categoryCode">상품분류</label>
                <input type="text" class="form-control" id="categoryCode" name="categoryCode" readonly/>
            </div>
        </div>
        <div class="form-row justify-content-center">
            <div class="form-group col-md-6">
                <label for="factoryCode">제조업체</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="factoryCode" name="factoryCode" required readonly/>
                    <div class="input-group-append">
                        <button type="button" class="btn btn-secondary" onclick="openFactoryPopup()">선택</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-row justify-content-center">
            <div class="form-group col-md-6">
                <label for="itemSize">상품사이즈</label>
                <input type="text" class="form-control" id="itemSize" name="itemSize" readonly/>
            </div>
        </div>
        <div class="form-row justify-content-center">
            <div class="form-group col-md-6">
                <label for="itemColor">상품컬러</label>
                <input type="text" class="form-control" id="itemColor" name="itemColor" readonly/>
            </div>
        </div>
        <div class="form-row justify-content-center">
            <div class="form-group col-md-6">
                <label for="purchasePrice">발주가격</label>
                <input type="text" class="form-control" id="purchasePrice" name="purchasePrice" required/>
            </div>
        </div>
        <div class="form-row justify-content-center">
            <div class="form-group col-md-6">
                <label for="purchaseQty">발주수량</label>
                <input type="text" class="form-control" id="purchaseQty" name="purchaseQty" required/>
            </div>
        </div>
        <div class="form-row justify-content-center">
            <div class="form-group col-md-6 text-center">
                <button type="submit" class="btn btn-primary">발주</button>
                <button type="button" class="btn btn-secondary" onclick="location.href='/trioAdmin'">취소</button>
            </div>
        </div>
    </form>
</div>


<!-- 팝업 창 열기 스크립트 -->
<script>
    function openFactoryPopup() {
        window.open('/popupFactoryList', 'popup', 'width=1000,height=600,scrollbars=yes,resizable=yes');
    }

    function setFactoryCode(factoryCode) {
        document.getElementById('factoryCode').value = factoryCode;
    }

    function openPopup() {
        window.open('/popupItemList', 'popup', 'width=1000,height=600,scrollbars=yes,resizable=yes');
    }

    function setItemDetails(itemCode, itemName, categoryCode, factoryCode, itemSize, itemColor) {
        document.getElementById('itemCode').value = itemCode;
        document.getElementById('itemName').value = itemName;
        document.getElementById('categoryCode').value = categoryCode;
        document.getElementById('factoryCode').value = factoryCode;
        document.getElementById('itemSize').value = itemSize;
        document.getElementById('itemColor').value = itemColor;
    }
</script>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
