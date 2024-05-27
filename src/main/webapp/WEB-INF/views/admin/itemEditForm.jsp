<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품 수정</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%@ include file="adminSidebar.jsp" %>
<div class="container mt-4">
    <h1 class="mb-4 text-center">상품 수정</h1>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h5 class="card-title mb-0">상품 정보</h5>
                </div>
                <div class="card-body">
                    <form action="/trioAdmin/stock/${item.itemCode}/edit" method="post">
                        <div class="row mb-3">
                            <div class="col-sm-4"><strong>상품코드:</strong></div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="itemCode" value="${item.itemCode}" disabled>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-4"><strong>상품이름:</strong></div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="itemName" value="${item.itemName}" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-4"><strong>카테고리:</strong></div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="categoryName" value="${item.categoryName}" disabled>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-4"><strong>업체코드:</strong></div>
                            <div class="col-sm-8">
                                <div class="input-group">
                                    <input type="text" class="form-control" id="factoryCode" name="factoryCode" value="${item.factoryCode}" required readonly/>
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-secondary" onclick="openPopup()">선택</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-4"><strong>판매가격:</strong></div>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" name="itemPrice" value="${item.itemPrice}" disabled>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-4"><strong>재고수량:</strong></div>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" name="stockQty" value="${item.stockQty}" disabled>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-4"><strong>사이즈:</strong></div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="itemSize" value="${item.itemSize}" disabled>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-4"><strong>상품색깔:</strong></div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="itemColor" value="${item.itemColor}" required>
                            </div>
                        </div>
                        <div class="text-right">
                            <button type="submit" class="btn btn-secondary">저장</button>
                            <a href="/trioAdmin/stock/list" class="btn btn-primary">목록으로 돌아가기</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 팝업 창 열기 스크립트 -->
<script>
    function openPopup() {
        window.open('/popupFactoryList', 'popup', 'width=1000,height=600,scrollbars=yes,resizable=yes');
    }

    function setFactoryCode(factoryCode) {
        document.getElementById('factoryCode').value = factoryCode;
    }
</script>
<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
