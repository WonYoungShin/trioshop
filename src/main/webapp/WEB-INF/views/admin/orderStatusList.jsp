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
</head>
<body>
<%@ include file="adminSidebar.jsp" %>
<div class="content">
    <div class="content">
        <div class="container">
            <h1 class="my-4 text-center">주문 목록</h1>
            <div class="row mb-4">
                <div class="col-md-12 d-flex justify-content-end">
                    <!-- 검색창 및 카테고리 선택 항목 결합 -->
                    <form class="form-inline" method="get" action="">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="itemName" value="${param.itemName}">
                        <select class="form-control mr-sm-2" name="category">
                            <option value="">Select Category</option>
                            <c:forEach var="category" items="${categoryList}">
                                <option value="${category.categoryCode}" <c:if test="${param.category == category.categoryCode}">selected</c:if>>${category.categoryName}</option>
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
                            <th>주문상태</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${purchaseList}">
                                <td>${item.orderCode}</td>
                                <td>${item.userCode}</td>
                                <td>${item.orderDate}</td>
                                <td>${item.itemName}</td>
                                <td>${item.orderQty}</td>
                                <td>${item.statusName}</td>
                                <td>
                                    <button class="btn btn-danger" onclick="">운송장번호</button>
                                </td>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
