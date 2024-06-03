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
            <label for="userName">이름:</label>
            <input type="text" class="form-control" id="userName" name="userName" required>
        </div>
        <div class="form-group">
            <label for="userTel">전화번호:</label>
            <input type="tel" class="form-control" id="userTel" name="userTel" pattern="\d{3}-\d{3,4}-\d{4}" required>
            <small class="form-text text-muted">올바른 전화번호 형식: 010-1234-5678</small>
        </div>
        <button type="submit" class="btn btn-primary btn-block">가입하기</button>
        <a href="/login" class="btn btn-secondary btn-block">뒤로가기</a>
    </form>
</div>

<!-- 부트스트랩 JS 및 jQuery 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- 전화번호 포맷팅을 위한 JavaScript -->
<script>
    document.getElementById('userTel').addEventListener('input', function (e) {
        var input = e.target.value.replace(/\D/g, ''); // 숫자만 남기기
        var formatted = '';

        if (input.length <= 3) {
            formatted = input;
        } else if (input.length <= 7) {
            formatted = input.slice(0, 3) + '-' + input.slice(3);
        } else {
            formatted = input.slice(0, 3) + '-' + input.slice(3, 7) + '-' + input.slice(7, 11);
        }

        e.target.value = formatted;
    });
</script>
</body>
</html>
