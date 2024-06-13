<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<style>

    .container {
        margin-top: 135px;
    }

    .col-md-12 {
        padding-top: 50px;
    }
    .justify-content-center{
        margin-top: 30px;
    }

</style>
<body>

<div class="container">
    <div class="row mb-4">
        <div class="col-md-12 d-flex justify-content-end">
            <!-- 검색창 및 카테고리 선택 항목 결합 -->
            <form class="form-inline" method="get" action="${pageContext.request.contextPath}/itemList">
                <input class="form-control mr-sm-2" type="search" placeholder="상품 이름" aria-label="Search"
                       name="itemName" value="${param.itemName}">
                <select class="form-control mr-sm-2" name="category">
                    <option value="">카테고리 선택</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.categoryName}"
                                <c:if test="${param.category == category.categoryName}">selected</c:if>>${category.categoryName}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
            </form>
        </div>
    </div>
    <div class="row">
        <c:forEach var="item" items="${itemList}">
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card h-100"
                     onclick="location.href='${pageContext.request.contextPath}/item/${item.itemCode}'"
                     style="cursor: pointer;">
                    <img class="card-img-top" src="${item.itemSrc}" alt="${item.itemName}">
                    <div class="card-body">
                        <h4 class="card-title">${item.itemName}</h4>
                        <h5 class="text-primary">₩${item.itemPrice}</h5>
                        <p class="card-text">
                            <strong>카테고리:</strong> ${item.categoryName}<br>
                            <strong>제조업체:</strong> ${item.factoryName}<br>
                            <strong>제고:</strong> ${item.stockQty}
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="row">
        <div class="col-12 d-flex justify-content-center">
            <nav>
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${totalPages != 1}">
                            <c:if test="${param.page > 1}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="?page=${param.page - 1}&itemName=${param.itemName}&category=${param.category}">&lt 이전</a>
                                </li>
                            </c:if>
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="page-item ${i == param.page ? 'active' : ''}">
                                    <a class="page-link"
                                       href="?page=${i}&itemName=${param.itemName}&category=${param.category}">${i}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${param.page < totalPages || param.page == null}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="?page=${param.page==null ? 2 : param.page + 1}&itemName=${param.itemName}&category=${param.category}">다음 &gt</a>
                                </li>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item active">
                                <a class="page-link" href="?page=1">1</a>
                            </li>
                        </c:otherwise>
                    </c:choose>

                </ul>
            </nav>
        </div>
    </div>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
