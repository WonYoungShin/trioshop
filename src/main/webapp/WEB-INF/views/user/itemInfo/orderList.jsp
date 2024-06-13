<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<%@ include file="/WEB-INF/views/etc/hdarea.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>주문 목록</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<style>

    .alert-info {
        top: 100px;
    }

    .mt-5  {
        margin-top: 6rem !important;
    }

    @media (min-width: 1200px) {
        .container, .container-lg, .container-md, .container-sm, .container-xl {
            max-width: 1250px;
        }
    }
    .table {
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border-radius: 5px;
        overflow: hidden;
    }
    .table thead th {
        vertical-align: middle;
        text-align: center;
        background-color: #f8f9fa;
        box-shadow: inset 0 -1px 0 rgba(0, 0, 0, 0.1);
    }
    .table tbody td {
        vertical-align: middle;
        text-align: center;
    }
    .table tbody tr {
        cursor: pointer;
        transition: box-shadow 0.2s;
    }
    .table tbody tr:hover {
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
    }
    .content {
        padding-top: 85px;
        margin-left: 100px;
        width: calc(100% - 250px);
    }
    .pagination .page-item.active .page-link {
        background-color: #83bdfb;
        border-color: #66c2fa;
        color: white;
    }

    .pagination .page-item .page-link {
        color: #535353;
    }

    .pagination .page-item .page-link:hover {
        background-color: #e9ecef;
        border-color: #dee2e6;
    }

</style>
<body>
<div class="container mt-5">
    <h1 class="mb-4">주문 목록</h1>
    <c:if test="${not empty orderList}">
        <table class="table table-bordered">
            <thead class="thead-light">
            <tr>
                <th scope="col">주문 코드</th>
                <th scope="col">주문 날짜</th>
                <th scope="col">주문 상태</th>
                <th scope="col">상품명</th>
                <th scope="col">수량</th>
                <th scope="col">가격</th>
                <th scope="col">총가격</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orderList}">
                <tr>
                    <td>${order.orderCode}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.statusName}</td>
                    <td class="nowrap">
                        <c:forEach var="itemName" items="${order.itemNames.split(',')}">
                            <div>${itemName}</div>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach var="orderQty" items="${order.orderQtys.split(',')}">
                            <div>${orderQty}</div>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach var="itemPrice" items="${order.itemPrices.split(',')}">
                            <div>${itemPrice}</div>
                        </c:forEach>
                    </td>
                    <td>
                        <c:set var="totalPrice" value="0"/>
                        <c:forEach var="price" items="${fn:split(order.itemPrices, ', ')}" varStatus="loop">
                            <c:set var="qty" value="${fn:split(order.orderQtys, ', ')[loop.index]}"/>
                            <c:set var="totalPrice" value="${totalPrice + (price * qty)}"/>
                        </c:forEach>
                        ₩${totalPrice}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <div class="row">
        <div class="col-12 d-flex justify-content-center">
            <nav>
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${totalPages != 1}">
                            <c:if test="${param.page > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="?page=${param.page - 1}">&lt 이전</a>
                                </li>
                            </c:if>
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="page-item ${i == param.page ? 'active' : ''}">
                                    <a class="page-link" href="?page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${param.page < totalPages || param.page == null}">
                                <li class="page-item">
                                    <a class="page-link" href="?page=${param.page==null ? 2 : param.page + 1}">다음 &gt</a>
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
    <c:if test="${empty orderList}">
        <div class="alert alert-info" role="alert">
            No orders found.
        </div>
    </c:if>
</div>




<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
