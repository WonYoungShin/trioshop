<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비회원로그인</title>
    <link rel="stylesheet" href="/css/guestLogin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <%--이미지--%>
    <link href="https://fonts.googleapis.com/css2?family=Alata&display=swap" rel="stylesheet"> <%--폰트--%>
</head>
<body>
<form id="loginForm" name="loginForm" autocomplete="off" action="" method="post">
    <div class="page-container">
        <div class="login-form-container shadow">
            <div class="login-form-right-side">
                <div class="top-logo-wrap">
                </div>
            </div>
            <div class="login-form-left-side">
                <div class="login-top-wrap">
                    <span>로그인 후 이용하고 싶으신가요?</span>
                    <button type="button" class="create-account-btn shadow-light">회원 로그인하기</button>
                </div>
                <div class="login-input-container">
                    <h1>Trio Shop</h1>
                    <h3>Sign In to Trio</h3>

                    <div class="API_img">
                        <a href="${kakaoURL}"><img src="/images/login/kakao.png" alt="Kakao"></a>
                    </div>
                    <h3 class="or-text">- OR -</h3>
                    <div class="login-input-wrap input-id">
                        <i class="far fa-envelope"></i>
                        <input type="text" id="userName" name="userName" placeholder="이름" title="이름" class="input_text" maxlength="10" value="" required autocomplete="off">
                    </div>
                    <div class="login-input-wrap input-Tel">
                        <i class="fa-solid fa-phone"></i>
                        <input type="tel" id="userTel" name="userTel" placeholder="전화번호" title="전화번호" class="input_text"
                               pattern="\d{3}-\d{3,4}-\d{4}" required autocomplete="off">
                    </div>
                    <small class="form-text text-muted">올바른 전화번호 형식: 010-1234-5678</small>
                </div>
                <div class="login-btn-wrap">
                    <button class="login-btn">로그인</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
<script>
    // 로그인페이지로 돌아가기
    document.addEventListener('DOMContentLoaded', function() {
        const createAccountBtn = document.querySelector('.create-account-btn');
        if (createAccountBtn) {
            createAccountBtn.addEventListener('click', function() {
                window.location.href = '/login';
            });
        }
    });

    // 이름 입력란
    var userNameInput = document.getElementById('userName');
    var nameClearButton = document.getElementById('name_clear');
    if (userNameInput && nameClearButton) {
        userNameInput.addEventListener('input', function() {
            if (this.value.trim() !== '') {
                nameClearButton.style.display = 'block';
            } else {
                nameClearButton.style.display = 'none';
            }
        });

        // 이름 입력란 삭제 버튼 클릭 시
        nameClearButton.addEventListener('click', function() {
            userNameInput.value = '';
            this.style.display = 'none';
        });

        // 초기에 삭제 버튼 숨기기
        nameClearButton.style.display = 'none';
    }

    // 전화번호 입력란
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
</html>
