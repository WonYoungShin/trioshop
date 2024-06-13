<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="/css/join.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <%--이미지--%>
    <link href="https://fonts.googleapis.com/css2?family=Alata&display=swap" rel="stylesheet">
    <%--폰트--%>
</head>
<body>
<form id="joinForm" name="joinForm" autocomplete="off" action="/join" method="post">
    <div class="page-container">
        <div class="login-form-container shadow">
            <div class="login-form-right-side">
                <div class="top-logo-wrap">
                </div>
            </div>
            <div class="login-form-left-side">

                <div class="login-input-container">
                    <h1>Trio Shop</h1>

                    <div class="login-input-wrap input-id">
                        <i class="far fa-envelope"></i>
                        <input type="text" id="userId" name="userId" placeholder="아이디" title="아이디" class="input_text"
                               maxlength="41" value="" required autocomplete="off">
                    </div>
                    <div class="login-input-wrap input-password">
                        <i class="fas fa-key"></i>
                        <input type="password" id="userPasswd" name="userPasswd" placeholder="비밀번호" title="비밀번호"
                               class="input_text"
                               maxlength="41" value="" required autocomplete="off">
                    </div>
                    <div class="login-input-wrap input-Name">
                        <i class="fa-solid fa-signature"></i>
                        <input type="text" id="userName" name="userName" placeholder="이름" title="이름" class="input_text"
                               maxlength="41" value="" required autocomplete="off">

                    </div>
                    <div class="login-input-wrap input-Address">
                        <i class="fa-solid fa-map-location-dot"></i>
                        <input type="text" id="userAddress" name="userAddress" placeholder="주소" title="주소"
                               class="input_text"
                               maxlength="41" value="" required autocomplete="off">

                    </div>
                    <div class="login-input-wrap input-Nickname">
                        <i class="fa-solid fa-user"></i>
                        <input type="text" id="userNickname" name="userNickname" placeholder="닉네임" title="닉네임"
                               class="input_text"
                               maxlength="41" value="" required autocomplete="off">

                    </div>
                    <div class="login-input-wrap input-Tel">
                        <i class="fa-solid fa-phone"></i>
                        <input type="tel" id="userTel" name="userTel" placeholder="전화번호" title="전화번호" class="input_text"
                               pattern="\d{3}-\d{3,4}-\d{4}" required autocomplete="off">
                    </div>
                    <small class="form-text text-muted">올바른 전화번호 형식: 010-1234-5678</small>


                    <div class="login-btn-wrap">
                        <button class="login-btn join-btn">가입하기</button>
                        <a href="${pageContext.request.contextPath}/login">로그인 페이지로 돌아가기</a>
                    </div>


                </div>
            </div>
        </div>
    </div>
</form>

<script>
    //guestLogin 이동
    document.addEventListener('DOMContentLoaded', function () {
        const createAccountBtn = document.querySelector('.create-account-btn');
        if (createAccountBtn) {
            createAccountBtn.addEventListener('click', function () {
                window.location.href = '/guestLogin';
            });
        }
    });

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
