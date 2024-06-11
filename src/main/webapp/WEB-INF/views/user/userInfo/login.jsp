<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Alata&display=swap" rel="stylesheet">
</head>
<body>
<form id="loginForm" name="loginForm" autocomplete="off" action="" method="post">
    <div class="page-container">
        <div class="login-form-container shadow">
            <div class="login-form-right-side">
                <div class="top-logo-wrap"></div>
            </div>
            <div class="login-form-left-side">
                <div class="login-top-wrap">
                    <span>회원가입 없이 이용하시고 싶으신가요?</span>
                    <a href="/guestLogin" class="create-account-btn shadow-light">비회원 로그인하기</a>
                </div>
                <div class="login-input-container">
                    <h1>Trio Shop</h1>
                    <h3>Sign In to Trio</h3>
                    <div class="API_img">
                        <a href="#"><img src="/images/login/kakao.png" alt="Kakao"></a>
                        <a href="#"><img src="/images/login/google.png" alt="Google"></a>
                        <a href="#"><img src="/images/login/naver.png" alt="Naver"></a>
                    </div>
                    <h3 class="or-text">- OR -</h3>
                    <div class="login-input-wrap input-id">
                        <i class="far fa-envelope"></i>
                        <input type="text" id="userId" name="userId" placeholder="아이디" title="아이디" class="input_text" maxlength="20" required>
                    </div>
                    <div class="login-input-wrap input-password">
                        <i class="fas fa-key"></i>
                        <input type="password" id="userPasswd" name="userPasswd" placeholder="비밀번호" title="비밀번호" class="input_text" maxlength="20" required>
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
                    <button type="button" class="login-btn" onclick="href='${pageContext.request.contextPath}/login'">로그인</button>
                    <button type="button" class="login-btn join-btn" onclick="href='${pageContext.request.contextPath}/join'">회원가입</button>
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
</html>