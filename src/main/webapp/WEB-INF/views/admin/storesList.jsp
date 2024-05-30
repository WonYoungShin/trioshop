<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>입고 목록</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- jQuery 링크 -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style>
        .table thead th {
            vertical-align: middle;
            text-align: center;
        }
        .table tbody td {
            vertical-align: middle;
            text-align: center;
        }
        .table tbody tr {
            cursor: pointer;
        }
        .content {
            margin-left: 250px; /* 사이드바의 너비만큼 마진을 줍니다 */
            padding: 20px;
            width: calc(100% - 250px);
        }
    </style>
</head>
<body>
<div class="d-flex">
    <%@ include file="adminSidebar.jsp" %>
    <div class="content">
        <div class="container">
            <h1 class="my-4 text-center">입고 목록</h1>
            <div class="row mb-4">
                <div class="col-md-12 d-flex justify-content-end">
                    <!-- 검색창 및 카테고리 선택 항목 결합 -->
                    <form class="form-inline" method="get" action="">
                        <input class="form-control mr-sm-2" type="search" placeholder="아이템 이름" aria-label="Search" name="itemName" value="${param.itemName}">
                        <select class="form-control mr-sm-2" name="category">
                            <option value="">카테고리 선택</option>
                            <c:forEach var="category" items="${categoryList}">
                                <option value="${category.categoryCode}" <c:if test="${param.category == category.categoryCode}">selected</c:if>>${category.categoryName}</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <table class="table table-bordered table-hover">
                        <thead class="thead-light">
                        <tr>
                            <th>입고번호</th>
                            <th>입고수량</th>
                            <th>발주코드</th>
                            <th>상품코드</th>
                            <th>제조업체</th>
                            <th>상품이름</th>
                            <th>카테고리</th>
                            <th>입고가격</th>
                            <th>사이즈</th>
                            <th>상품색깔</th>
                            <th>삭제</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${storesList}">
                            <tr onclick="location.href='/trioAdmin/stores/${item.storeCode}'">
                                <td>${item.storeCode}</td>
                                <td>${item.storesQty}</td>
                                <c:if test="${item.purchaseCode != null}">
                                    <td>${item.purchaseCode}</td>
                                </c:if>
                                <c:if test="${item.purchaseCode == null}">
                                    <td></td>
                                </c:if>
                                <td>${item.itemCode}</td>
                                <td>${item.factoryCode}</td>
                                <td>${item.itemName}</td>
                                <td>${item.categoryName}</td>
                                <td>${item.storesPrice}</td>
                                <td>${item.itemSize}</td>
                                <td>${item.itemColor}</td>
                                <td>
                                    <button class="btn btn-danger" onclick="deletePurchase(event, ${item.storeCode})">삭제</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function deletePurchase(event, storeCode) {
        event.stopPropagation(); // 클릭 이벤트 전파 방지
        if (confirm('정말 삭제하시겠습니까?')) {
            $.ajax({
                url: '/trioAdmin/stores/' + storeCode,
                type: 'DELETE',
                success: function(result) {
                    alert('삭제되었습니다.');
                    location.reload(); // 페이지 새로고침
                },
                error: function(err) {
                    alert('삭제 중 오류가 발생했습니다.');
                }
            });
        }
    }
</script>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
