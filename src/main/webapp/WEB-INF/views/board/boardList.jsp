<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Font Awesome CSS 링크 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <!-- jQuery 링크 -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style>
        .table {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            overflow: hidden;
            width: 85%; /* 테이블의 최대 폭 설정 */
            margin: auto; /* 중앙 정렬 */
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
            top: 45px;
        }

        .pagination .page-item .page-link {
            color: #535353;
        }

        .pagination .page-item .page-link:hover {
            background-color: #e9ecef;
            border-color: #dee2e6;
        }

        .btn-circle {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 20px;
            color: white;
            background-color: #007bff;
            border: none;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: box-shadow 0.3s ease;
        }

        .btn-circle:hover {
            box-shadow: 0 8px 12px rgba(0, 0, 0, 0.2);
        }

        .category-name {
            font-size: 0.75em;
            color: #a69b9b;
            text-align: left;
            display: inline-block;
        }

        .board-title {
            text-align: center;
            display: inline-block;
            width: calc(100% - 5em); /* 카테고리 이름의 폭을 고려한 설정 */
            margin-left: 5px;
        }

        .title-cell {
            text-align: left;
        }

        .board-title {
            text-align: initial;
        !important;
        }

        .justify-content-end {
            padding-right: 100px;
        }

        .text-center {
            padding-bottom: 50px;
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
                        <input class="form-control mr-sm-2" type="search" placeholder="제목" aria-label="Search"
                               name="title" value="${param.title}">
                        <select class="form-control mr-sm-2" name="category">
                            <option value="">전체</option>
                            <c:forEach var="category" items="${categoryList}">
                                <option value="${category.categoryCode}"
                                        <c:if test="${param.category == category.categoryCode}">selected</c:if>>${category.categoryName}</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
                    </form>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12 d-flex justify-content-end" style="margin-bottom: 10px">
                    <a href="${pageContext.request.contextPath}/board/write" class="btn btn-circle">
                        <i class="fas fa-plus"></i>
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <table class="table table-bordered table-hover">
                        <thead class="thead-light">
                        <tr>
                            <th>No.</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일자</th>
                            <th>조회수</th>
                            <th>댓글수</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${contentList}">
                            <tr onclick="location.href='/board/${item.boardCode}'">
                                <td>${item.boardCode}</td>
                                <td class="title-cell">
                                    <span class="category-name"
                                          style="font-size: 0.75em; color: #a69b9b;">[${item.categoryName}]</span>
                                    <span class="board-title">${item.boardTitle}</span>
                                </td>
                                <td>${item.userNickname}</td>
                                <td>${item.boardDate}</td>
                                <td>${item.boardViews}</td>
                                <td>${item.commentCount}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
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
                                               href="?page=${param.page - 1}&title=${param.title}&category=${param.category}">&lt 이전</a>
                                        </li>
                                    </c:if>
                                    <c:forEach var="i" begin="1" end="${totalPages}">
                                        <li class="page-item ${i == param.page ? 'active' : ''}">
                                            <a class="page-link"
                                               href="?page=${i}&title=${param.title}&category=${param.category}">${i}</a>
                                        </li>
                                    </c:forEach>
                                    <c:if test="${param.page < totalPages || param.page == null}">
                                        <li class="page-item">
                                            <a class="page-link"
                                               href="?page=${param.page==null ? 2 : param.page + 1}&title=${param.title}&category=${param.category}">다음 &gt</a>
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
    </div>
</div>
<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.amazonaws.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
