<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>발주 목록</title>
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
            padding-top: 100px;
            margin-left: 100px;
            width: calc(100% - 250px);
        }
    </style>
</head>
<body>
<div class="d-flex">
    <div class="content">
        <div class="container">
            <h1 class="my-4 text-center">게시판</h1>
            <div class="row mb-4">
                <div class="col-md-12 d-flex justify-content-end">
                    <!-- 검색창 및 카테고리 선택 항목 결합 -->
                    <form class="form-inline" method="get" action="">
                        <input class="form-control mr-sm-2" type="search" placeholder="제목" aria-label="Search" name="title" value="${param.title}">
                        <select class="form-control mr-sm-2" name="category">
                            <option value="">전체</option>
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
                            <th>No.</th>
                            <th>카테고리</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일자</th>
                            <th>조회수</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${contentList}">
                            <tr onclick="location.href='/board/${item.boardCode}'">
                                <td>${item.boardCode}</td>
                                <td>${item.categoryName}</td>
                                <td>${item.boardTitle}</td>
                                <td>${item.userName}</td>
                                <td>${item.boardDate}</td>
                                <td>${item.boardViews}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12 d-flex justify-content-end">
                    <a href="${pageContext.request.contextPath}/board/write" class="btn btn-primary">글작성</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>