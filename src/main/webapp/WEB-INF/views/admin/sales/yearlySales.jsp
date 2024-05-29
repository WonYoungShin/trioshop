
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>연도별 매출</title>
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
    <h1 class="my-4 text-center">연도별 매출</h1>
    <div class="row mb-4">
        <div class="col-md-12">
            <!-- 연도 검색 폼 -->
            <form class="form-inline search-form justify-content-center" method="get" action="">
                <div class="form-group mr-2">
                    <label class="mr-2" for="searchYear">연도 검색:</label>
                    <input type="text" class="form-control" id="searchYear" name="year" placeholder="연도를 입력하세요" value="${salesDateModel.year}">
                </div>
                <button class="btn btn-success" type="submit">검색</button>
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
                    <th>연도</th>
                    <th>매출액</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="sale" items="${yearlySales}">
                    <tr>
                        <td><a href="/trioAdmin/monthSales?year=${sale.orderYear}">${sale.orderYear}</a></td>
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
