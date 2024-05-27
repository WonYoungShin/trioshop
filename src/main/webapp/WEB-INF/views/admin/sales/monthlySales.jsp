<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>월별 매출</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .content {
            margin-left: 250px;
            padding: 20px;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/admin/adminSidebar.jsp" %>

<div class="content">
    <h1 class="my-4 text-center">월별 매출</h1>
    <div class="row mb-4">
        <div class="col-md-12 d-flex justify-content-center">
            <!-- 연도 및 월 선택 드롭다운 메뉴 -->
            <form class="form-inline" method="get" action="">
                <div class="form-group mr-2">
                    <label class="mr-2" for="selectedYear">연도 선택:</label>
                    <select class="form-control" id="selectedYear" name="year">
                        <c:forEach var="year" items="${yearList}">
                            <option value="${year}" <c:if test="${year == selectedYear}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group mr-2">
                    <label class="mr-2" for="selectedMonth">월 선택:</label>
                    <select class="form-control" id="selectedMonth" name="month">
                        <option value="" <c:if test="${empty selectedMonth}">selected</c:if>>전체</option>
                        <c:forEach var="month" items="${monthList}">
                            <option value="${month}" <c:if test="${month == selectedMonth}">selected</c:if>>${month}</option>
                        </c:forEach>
                    </select>
                </div>
                <button class="btn btn-success" type="submit">조회</button>
            </form>
        </div>
    </div>

    <!-- 매출 테이블 -->
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center mb-4">매출 정보</h2>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>월</th>
                    <th>매출액</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="sale" items="${salesList}">
                    <tr>
                        <td>${sale.orderMonth}</td>
                        <td><fmt:formatNumber value="${sale.totalSales}" type="currency" currencySymbol="₩"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- 총매출 표시 -->
    <div class="row mt-4">
        <div class="col-md-12 text-right">
            <div class="total-sales d-flex justify-content-end">
                <h3>총매출: <fmt:formatNumber value="${totalSales}" type="currency" currencySymbol="₩"/></h3>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
