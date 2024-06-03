<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비회원 로그인</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">

    <form id="secondForm" action="/guestLogin" method="post">
        <div class="form-group">
            <label for="userName2">이름:</label>
            <input type="text" class="form-control" id="userName2" name="userName" required>
        </div>
        <div class="form-group">
            <label for="userAddress2">주소:</label>
            <input type="text" class="form-control" id="userAddress2" name="userAddress" required>
        </div>
        <div class="form-group">
            <label for="userTel2">전화번호:</label>
            <input type="tel" class="form-control" id="userTel2" name="userTel" pattern="[0-9]{3}-[0-9]{3,4}-[0-9]{4}" required>
            <small class="form-text text-muted">올바른 전화번호 형식: 010-1234-5678</small>
        </div>
        <button type="submit" class="btn btn-primary btn-block">가입하기</button>
        <a href="/login" class="btn btn-secondary btn-block">뒤로가기</a>
    </form>
</div>
</body>
</html>
