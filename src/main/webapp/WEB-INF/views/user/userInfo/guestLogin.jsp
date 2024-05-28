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
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 400px;
            margin-top: 100px;
        }
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        form label {
            font-weight: bold;
        }
        form button {
            margin-top: 10px;
        }
        /* 두 번째 폼 숨기기 */
        #secondForm {
            display: none;
        }
        /* 회원가입 버튼 표시 */
        #signupButton1 {
            display: inline-block; /* 처음에는 회원가입 버튼이 보이도록 변경 */
        }
        /* 로그인 버튼 표시 */
        #loginButton {
            display: inline-block;
        }
        /* 회원가입, 취소 버튼 표시 */
        #signupButton2,
        #cancelButton {
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="container">
    <!-- 첫 번째 폼 -->
    <form id="firstForm" action="/guestlogin" method="post">
        <div class="form-group">
            <label for="userName1">이름:</label>
            <input type="text" class="form-control" id="userName1" name="userName" required>
        </div>
        <div class="form-group">
            <label for="userAddress1">주소:</label>
            <input type="text" class="form-control" id="userAddress1" name="userAddress" required>
        </div>
        <div class="form-group">
            <label for="userTel1">전화번호:</label>
            <input type="tel" class="form-control" id="userTel1" name="userTel" pattern="[0-9]{3}-[0-9]{3,4}-[0-9]{4}" required>
            <small class="form-text text-muted">올바른 전화번호 형식: 010-1234-5678</small>
        </div>

        <button id="loginButton" type="submit" class="btn btn-primary btn-block">로그인</button>
        <button id="signupButton1" type="button" class="btn btn-primary btn-block" onclick="showSecondForm()">회원가입</button>

    </form>

    <!-- 두 번째 폼 -->
    <form id="secondForm" action="/guestlogin" method="post">
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
        <!-- "취소" 버튼 클릭 시 JavaScript를 통해 기존 폼 표시 -->
        <button id="cancelButton" type="button" class="btn btn-secondary btn-block" onclick="showFirstForm()">취소</button>
    </form>
</div>

<script>
    // 두 번째 폼 표시하는 함수
    function showSecondForm() {
        document.getElementById("firstForm").style.display = "none";
        document.getElementById("secondForm").style.display = "block";
        // 회원가입 버튼 숨김
        document.getElementById("signupButton1").style.display = "none";
        // 로그인 버튼 표시
        document.getElementById("loginButton").style.display = "none";
    }

    // 첫 번째 폼 표시하는 함수
    function showFirstForm() {
        document.getElementById("secondForm").style.display = "none";
        document.getElementById("firstForm").style.display = "block";
        // 로그인 버튼 숨김
        document.getElementById("loginButton").style.display = "inline-block";
        // 회원가입 버튼 표시
        document.getElementById("signupButton1").style.display = "inline-block";
    }

    // 비회원 로그인 성공/실패 알림
    function showLoginAlert(isSuccess) {
        if (isSuccess) {
            alert("비회원 로그인 성공!");
        } else {
            alert("비회원 로그인 실패!");
        }
    }

    // 비회원 회원가입 성공/실패 알림
    function showSignupAlert(isSuccess) {
        if (isSuccess) {
            alert("비회원 회원가입 성공!");
        } else {
            alert("비회원 회원가입 실패!");
        }
    }
</script>

</body>
</html>
