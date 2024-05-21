<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품 목록</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- jQuery 링크 -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-4">상품 목록</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>상품코드</th>
            <th>상품이름</th>
            <th>상품분류</th>
            <th>제조업체</th>
            <th>사이즈</th>
            <th>상품색깔</th>
            <th>선택</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${itemList}">
            <tr>
                <td>${item.itemCode}</td>
                <td>${item.itemName}</td>
                <td>${item.categoryCode}</td>
                <td>${item.factoryCode}</td>
                <td>${item.itemSize}</td>
                <td>${item.itemColor}</td>
                <td>
                    <button type="button" class="btn btn-primary"
                            onclick="selectItem('${item.itemCode}', '${item.itemName}', '${item.categoryCode}', '${item.factoryCode}', '${item.itemSize}', '${item.itemColor}')">
                        선택
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function selectItem(itemCode, itemName, categoryCode, factoryCode, itemSize, itemColor) {
        window.opener.setItemDetails(itemCode, itemName, categoryCode, factoryCode, itemSize, itemColor);
        window.close();
    }
</script>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
