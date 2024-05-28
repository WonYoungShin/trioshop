<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 상태 관리</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- jQuery 링크 -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style>
        .content {
            margin-left: 250px; /* 사이드바의 너비만큼 마진을 줍니다 */
            padding: 20px;
            width: calc(100% - 250px);
        }
        .table thead th {
            vertical-align: middle;
            text-align: center;
        }
        .table tbody td {
            vertical-align: middle;
            text-align: center;
        }
    </style>
</head>
<body>
<%@ include file="adminSidebar.jsp" %>
<div class="content">
    <div class="container">
        <h1 class="my-4 text-center">주문 목록</h1>
        <div class="row mb-4">
            <div class="col-md-12 d-flex justify-content-end">
                <!-- 검색창 및 카테고리 선택 항목 결합 -->
                <form class="form-inline" method="get" action="">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="itemName" value="${param.itemName}">
                    <select class="form-control mr-sm-2" name="status">
                        <option value="">상태 선택</option>
                        <c:forEach var="status" items="${statusList}">
                            <option value="${status.statusCode}" <c:if test="${param.status == status.statusCode}">selected</c:if>>${status.statusName}</option>
                        </c:forEach>
                    </select>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <table class="table table-bordered table-hover">
                    <thead class="thead-light">
                    <tr>
                        <th>주문번호</th>
                        <th>유저코드</th>
                        <th>주문날짜</th>
                        <th>상품이름</th>
                        <th>주문수량</th>
                        <th>상품가격</th>
                        <th>주문상태</th>
                        <th>도구</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orderList}">
                        <tr>
                            <td>${order.orderCode}</td>
                            <td>${order.userCode}</td>
                            <td>${order.orderDate}</td>
                            <td>
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
                            <td>${order.statusName}</td>
                            <td>
                                <c:if test="${order.statusCode eq '10' || order.statusCode eq '20'}">
                                    <button class="btn btn-secondary" onclick="">운송장입력</button>
                                </c:if>
                                <c:if test="${order.statusCode != '10' && order.statusCode != '20'}">
                                    <button class="btn btn-info" onclick="openPopup('${order.orderCode}','${order.statusCode}')">상태변경</button>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    function openPopup(orderCode, statusCode) {
        var width = 100;
        var height = 200;
        var left = (screen.width - width) / 2;
        var top = (screen.height - height) / 2;
        window.open('/trioAdmin/orderStatus/edit/' + encodeURIComponent(orderCode) + "?statusCode=" + statusCode, 'popup', 'width=' + width + ',height=' + height + ',top=' + top + ',left=' + left + ',scrollbars=yes,resizable=yes');
    }
</script>
</body>
</html>
