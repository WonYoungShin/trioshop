<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 수정</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- jQuery 링크 -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style>
        .content {
            padding-top: 50px;
            margin-left: 100px;
            width: calc(100%);
        }
    </style>
</head>
<body>
<div class="d-flex">
    <div class="content">
        <div class="container">
            <h1 class="my-4 text-center">글 수정</h1>
            <form action="" method="post">
                <!-- 글 ID를 숨겨진 필드로 전송 -->
                <input type="hidden" name="boardCode" value="${boardEdit.boardCode}" />
                <div class="form-group">
                    <label for="category">카테고리</label>
                    <select class="form-control" id="category" name="categoryCode">
                        <c:forEach var="category" items="${categoryList}">
                            <c:if test="${category.categoryCode != '10' or category.categoryCode != '10' and loginMember.gradeCode == 4}">
                                <option value="${category.categoryCode}" ${category.categoryCode == boardEdit.categoryCode ? 'selected' : ''}>${category.categoryName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" class="form-control" id="title" name="boardTitle" value="${boardEdit.boardTitle}" required>
                </div>
                <div class="form-group">
                    <label for="content">내용</label>
                    <textarea class="form-control" id="content" name="boardContent" rows="10" required>${boardEdit.boardContent}</textarea>
                </div>
                <div class="form-group text-center">
                    <button type="submit" class="btn btn-primary">수정</button>
                    <a href="/board" class="btn btn-secondary">취소</a>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>