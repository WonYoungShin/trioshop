<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>월별 매출</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Chart.js 라이브러리 링크 -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
    </style>
</head>
<body>
<%@ include file="adminSidebar.jsp" %>
<div class="container">
    <h1 class="my-4 text-center">월별 매출</h1>
    <div class="row mb-4">
        <div class="col-md-12 d-flex justify-content-end">
            <!-- 연도 및 월 선택 드롭다운 메뉴 -->
            <form class="form-inline" method="get" action="">
                <label class="mr-sm-2" for="selectedYear">연도 선택:</label>
                <select class="form-control mr-sm-2" id="selectedYear" name="year">
                    <option value="">전체</option>
                    <c:forEach var="year" items="${yearList}">
                        <option value="${year}" <c:if test="${param.year == year}">selected</c:if>>${year}</option>
                    </c:forEach>
                </select>
                <label class="mr-sm-2" for="selectedMonth">월 선택:</label>
                <select class="form-control mr-sm-2" id="selectedMonth" name="month">
                    <option value="">전체</option>
                    <c:forEach var="month" items="${monthList}">
                        <option value="${month}" <c:if test="${param.month == month}">selected</c:if>>${month}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">조회</button>
            </form>
        </div>
    </div>
    <div class="row mb-4">
        <div class="col-12">
            <canvas id="salesChart" width="400" height="200"></canvas>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <table class="table table-bordered table-hover">
                <thead class="thead-light">
                <tr>
                    <th>월</th>
                    <th>매출액</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="sale" items="${monthlySales}">
                    <tr>
                        <td>${sale.month}</td>
                        <td>${sale.amount}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        var ctx = document.getElementById('salesChart').getContext('2d');
        var salesChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: [
                    <c:forEach var="sale" items="${monthlySales}">
                    '${sale.month}',
                    </c:forEach>
                ],
                datasets: [{
                    label: '월별 매출액',
                    data: [
                        <c:forEach var="sale" items="${monthlySales}">
                        ${sale.amount},
                        </c:forEach>
                    ],
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    });
</script>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
