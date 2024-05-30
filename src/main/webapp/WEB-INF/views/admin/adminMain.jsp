<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>관리자 페이지</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            display: flex;
            min-height: 100vh;
            flex-direction: column;
        }
        .wrapper {
            display: flex;
            flex: 1;
        }
        .sidebar .nav-link {
            color: #fff;
        }
        .sidebar .nav-link:hover {
            background-color: #495057;
        }
        .content {
            flex: 1;
            padding: 20px;
            margin-left: 250px;
        }
        .card-title {
            font-size: 1.25rem;
            margin-bottom: 0.75rem;
        }
        .nowrap {
            white-space: nowrap;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <!-- 사이드바 -->
    <%@ include file="adminSidebar.jsp" %>
    <!-- 메인 컨텐츠 -->
    <main class="content">
        <h1>대시보드</h1>
        <div class="row">
            <div class="col-md-6">
                <!-- 예시 통계 카드 -->
                <div class="card mb-4">
                    <div class="card-header">
                        매출
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">총 매출</h5>
                        <p class="card-text"><fmt:formatNumber value="${dashboard.allOrderPrice}" type="currency" currencySymbol=""/></p>
                        <a href="/trioAdmin/sales" class="btn btn-primary">자세히 보기</a>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <!-- 예시 통계 카드 -->
                <div class="card mb-4">
                    <div class="card-header">
                        발주 내역
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">총 발주 수량</h5>
                        <p class="card-text">${dashboard.allPurchaseCount}</p>
                        <a href="/trioAdmin/purchase/list" class="btn btn-primary">자세히 보기</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row mb-4">
                <div class="col-md-12 d-flex justify-content-end">
                    <form class="form-inline" method="get" action="">
                        <input class="form-control mr-sm-2" type="search" placeholder="유저코드" aria-label="Search" name="userCode" value="${param.userCode}">
                        <select class="form-control mr-sm-2" name="statusCode">
                            <option value="">전체</option>
                            <c:forEach var="status" items="${statusList}">
                                <option value="${status.statusCode}" <c:if test="${param.statusCode == status.statusCode}">selected</c:if>>${status.statusName}</option>
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
                            <th>주문번호</th>
                            <th>유저코드</th>
                            <th>주문날짜</th>
                            <th>상품이름</th>
                            <th>주문수량</th>
                            <th>상품가격</th>
                            <th>주문상태</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${orderList}">
                            <tr>
                                <td>${order.orderCode}</td>
                                <td>${order.userCode}</td>
                                <td>${order.orderDate}</td>
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
                                <td class="nowrap">${order.statusName}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>
</div>
<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
