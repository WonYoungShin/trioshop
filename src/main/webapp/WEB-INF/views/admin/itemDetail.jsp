<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%@ include file="adminSidebar.jsp" %>
<div class="container mt-4">
    <h1 class="mb-4 text-center">상품 상세</h1>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h5 class="card-title mb-0">상품 정보</h5>
                </div>
                <div class="card-body">
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>상품코드:</strong></div>
                        <div class="col-sm-8">${item.itemCode}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>상품이름:</strong></div>
                        <div class="col-sm-8">${item.itemName}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>카테고리:</strong></div>
                        <div class="col-sm-8">${item.categoryName}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>제조업체:</strong></div>
                        <div class="col-sm-8">${item.factoryCode}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>판매가격:</strong></div>
                        <div class="col-sm-8">${item.itemPrice}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>재고수량:</strong></div>
                        <div class="col-sm-8">${item.stockQty}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>사이즈:</strong></div>
                        <div class="col-sm-8">${item.itemSize}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>상품색깔:</strong></div>
                        <div class="col-sm-8">${item.itemColor}</div>
                    </div>
                    <div class="text-right">
                        <a href="/trioAdmin/stock/${item.itemCode}/edit" class="btn btn-secondary">수정</a>
                        <a href="/trioAdmin/stock/list" class="btn btn-primary">목록으로 돌아가기</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
