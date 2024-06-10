<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <%--이미지--%>
    <link href="https://fonts.googleapis.com/css2?family=Alata&display=swap" rel="stylesheet">
    <%--폰트--%>
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
                    <span>회원가입 없이 이용하시고 싶으신가요?</span>
                    <button class="create-account-btn shadow-light">비회원 로그인하기</button>
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
                        <input type="text" id="userId" name="userId" placeholder="아이디" title="아이디" class="input_text"
                               maxlength="20" value="" required>
<%--                        <span role="button" class="btn_delete" id="id_clear" style="display: none;">--%>
<%--                             <i class="fa-solid fa-eraser"></i>--%>
<%--                        <span class="sr-only">삭제</span> <!-- 스크린 리더 사용자를 위한 숨겨진 텍스트 -->--%>
<%--                        </span>--%>
                    </div>
                    <div class="login-input-wrap input-password">
                        <i class="fas fa-key"></i>
                        <input type="password" id="userPasswd" name="userPasswd" placeholder="비밀번호" title="비밀번호"
                               class="input_text" maxlength="20" required>
<%--                        <span role="button" class="btn_delete" id="pw_clear" style="display: none;">--%>
<%--                             <i class="fa-solid fa-eraser"></i>--%>
<%--                        <span class="sr-only">삭제</span> <!-- 스크린 리더 사용자를 위한 숨겨진 텍스트 -->--%>
<%--                        </span>--%>
                    </div>
                </div>

                <!-- 에러 메시지 표시 -->
                <c:if test="${not empty message}">
                    <script>
                        alert("${message}");
                        document.getElementById('loginForm').reset();
                    </script>
                </c:if>

                <div class="login-btn-wrap">
                    <button class="login-btn">로그인</button>
                    <button class="login-btn join-btn">회원가입</button>
                </div>

                <div class="login-btn-wrap-find">
                <a href="/findId">Forgot Id?</a>
                <a href="/findPw">Forgot password?</a>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
<script>


        //join 으로 이동
    document.addEventListener('DOMContentLoaded', function () {
        const joinButton = document.querySelector('.join-btn');
        if (joinButton) {
            joinButton.addEventListener('click', function () {
                window.location.href = '/join';
            });
        }
    });


    //guestLogin 이동
    document.addEventListener('DOMContentLoaded', function () {
        const createAccountBtn = document.querySelector('.create-account-btn');
        if (createAccountBtn) {
            createAccountBtn.addEventListener('click', function () {
                window.location.href = '/guestLogin';
            });
        }
    });


    // 아이디 입력란
    var userNameInput = document.getElementById('userId');
    var idClearButton = document.getElementById('id_clear');
    userNameInput.addEventListener('input', function() {
        if (this.value.trim() !== '') {
            idClearButton.style.display = 'block';
        } else {
            idClearButton.style.display = 'none';
        }
    });

    // 전화번호 입력란
    var userTelInput = document.getElementById('userPasswd');
    var pwClearButton = document.getElementById('pw_clear');
    userTelInput.addEventListener('input', function() {
        if (this.value.trim() !== '') {
            pwClearButton.style.display = 'block';
        } else {
            pwClearButton.style.display = 'none';
        }
    });

    // 아이디 입력란 삭제 버튼 클릭 시
    idClearButton.addEventListener('click', function() {
        userNameInput.value = '';
        this.style.display = 'none';
    });

    // 전화번호 입력란 삭제 버튼 클릭 시
    pwClearButton.addEventListener('click', function() {
        userTelInput.value = '';
        this.style.display = 'none';
    });

    // 초기에 삭제 버튼 숨기기
    idClearButton.style.display = 'none';
    pwClearButton.style.display = 'none';



</script>
</html>