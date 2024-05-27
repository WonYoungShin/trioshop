<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>입고 품목 상세</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%@ include file="adminSidebar.jsp" %>
<div class="container mt-4">
    <h1 class="mb-4 text-center">입고 품목 상세</h1>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h5 class="card-title mb-0">입고 정보</h5>
                </div>
                <div class="card-body">
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>입고번호:</strong></div>
                        <div class="col-sm-8">${store.storeCode}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>입고수량:</strong></div>
                        <div class="col-sm-8">${store.storesQty}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>발주코드:</strong></div>
                        <div class="col-sm-8">
                            <c:choose>
                                <c:when test="${store.purchaseCode != null}">
                                    ${store.purchaseCode}
                                </c:when>
                                <c:otherwise>
                                    N/A
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>상품코드:</strong></div>
                        <div class="col-sm-8">${store.itemCode}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>제조업체:</strong></div>
                        <div class="col-sm-8">${store.factoryCode}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>상품이름:</strong></div>
                        <div class="col-sm-8">${store.itemName}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>카테고리:</strong></div>
                        <div class="col-sm-8">${store.categoryName}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>입고가격:</strong></div>
                        <div class="col-sm-8">${store.storesPrice}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>사이즈:</strong></div>
                        <div class="col-sm-8">${store.itemSize}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-4"><strong>상품색깔:</strong></div>
                        <div class="col-sm-8">${store.itemColor}</div>
                    </div>
                    <div class="text-right">
                        <a href="/trioAdmin/stores/list" class="btn btn-primary">목록으로 돌아가기</a>
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
