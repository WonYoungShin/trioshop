<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>업체 목록</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- jQuery 링크 -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-4">상품 목록</h1>
    <div class="row mb-4">
        <div class="col-md-12 d-flex justify-content-end">
            <!-- 업체코드와 업체이름 검색 폼 -->
            <form class="form-inline" method="get" action="">
                <input class="form-control mr-sm-2" type="search" placeholder="업체코드 검색" aria-label="Search" name="factoryCode" value="${param.factoryCode}">
                <input class="form-control mr-sm-2" type="search" placeholder="업체이름 검색" aria-label="Search" name="factoryName" value="${param.factoryName}">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
            </form>
        </div>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>업체코드</th>
            <th>업체명</th>
            <th>전화번호</th>
            <th>업체주소</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${factoryList}">
            <tr>
                <td>${item.factoryCode}</td>
                <td>${item.factoryName}</td>
                <td>${item.factoryTel}</td>
                <td>${item.factoryAddr}</td>
                <td>
                    <button type="button" class="btn btn-primary"
                            onclick="selectFactoryCode('${item.factoryCode}')">
                        선택
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function selectFactoryCode(factoryCode) {
        window.opener.setFactoryCode(factoryCode);
        window.close();
    }
</script>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
