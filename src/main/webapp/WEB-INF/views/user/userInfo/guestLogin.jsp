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
                    <button class="create-account-btn shadow-light">회원 로그인하기</button>
                </div>
                <div class="login-input-container">
                    <h1>Trio Shop</h1>
                    <h3>Sign In to Trio</h3>


                <%--                    <a href="#" target="">--%>
                    <%--                        <img src="/images/login/google.png" alt="Google">--%>
                    <%--                    </a>--%>
                    <%--                    <a href="#" target="">--%>
                    <%--                        <img src="/images/login/naver.png" alt="Naver">--%>
                    <%--                    </a>--%>
                    <%--                    <a href="#" target="">--%>
                    <%--                        <img src="/images/login/kakao.png" alt="Kakao">--%>
                    <%--                    </a>--%>

                    <div class="API_img">
                        <a href="#">
                            <img src="/images/login/kakao.png" alt="Kakao">
                        </a>
                        <a href="#">
                            <img src="/images/login/google.png" alt="Google">
                        </a>
                        <a href="#">
                            <img src="/images/login/naver.png" alt="Naver">
                        </a>
                    </div>




                    <h3 class="or-text">- OR -</h3>
                    <div class="login-input-wrap input-id">
                        <i class="far fa-envelope"></i>
                        <input type="text" id="userName" name="userName" placeholder="이름" title="이름" class="input_text" maxlength="4" value="" required autocomplete="off">
<%--                        <span role="button" class="btn_delete" id="name_clear" style="display: none;">--%>
<%--                             <i class="fa-solid fa-eraser"></i>--%>
<%--                        <span class="sr-only">삭제</span> <!-- 스크린 리더 사용자를 위한 숨겨진 텍스트 -->--%>
<%--                        </span>--%>
                    </div>
                    <div class="login-input-wrap input-password">
                        <i class="fas fa-key"></i>
                        <input type="tel" id="userTel" name="userTel" placeholder="전화번호" title="전화번호" class="input_text" pattern="\d{3}-\d{3,4}-\d{4}" required autocomplete="off">
<%--                        <span role="button" class="btn_delete" id="tel_clear" style="display: none;">--%>
<%--                           <i class="fa-solid fa-eraser"></i>--%>
<%--                          <span class="sr-only">삭제</span> <!-- 스크린 리더 사용자를 위한 숨겨진 텍스트 -->--%>
<%--                        </span>--%>
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
    //로그인페이지로 돌아가기
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
    userNameInput.addEventListener('input', function() {
        if (this.value.trim() !== '') {
            nameClearButton.style.display = 'block';
        } else {
            nameClearButton.style.display = 'none';
        }
    });

    // 전화번호 입력란
    var TelInput = document.getElementById('userTel');
    var telClearButton = document.getElementById('tel_clear');
    TelInput.addEventListener('input', function() {
        if (this.value.trim() !== '') {
            telClearButton.style.display = 'block';
        } else {
            telClearButton.style.display = 'none';
        }
    });

    // 이름 입력란 삭제 버튼 클릭 시
    nameClearButton.addEventListener('click', function() {
        userNameInput.value = '';
        this.style.display = 'none';
    });

    // 전화번호 입력란 삭제 버튼 클릭 시
    telClearButton.addEventListener('click', function() {
        TelInput.value = '';
        this.style.display = 'none';
    });

    // 초기에 삭제 버튼 숨기기
    nameClearButton.style.display = 'none';
    telClearButton.style.display = 'none';
</script>
</html>
