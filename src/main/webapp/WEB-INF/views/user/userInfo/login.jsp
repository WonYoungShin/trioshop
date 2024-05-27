<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <!-- 부트스트랩 CSS 링크 추가 -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome 아이콘 CDN 추가 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">로그인</div>
                <div class="card-body">
                    <!-- 이미지 추가 -->
                    <img src="/images/logo.png" class="img-fluid mb-4" alt="부트스트랩 이미지">

                    <!-- 광고 추가 -->
                    <div class="alert alert-info" role="alert">
                        이 광고는 간단한 예시입니다. 여기에 광고 내용을 추가할 수 있습니다.
                    </div>

                    <!-- 기본 로그인 폼 -->
                    <form id="loginForm" method="post" action="/login" autocomplete="on">
                        <div class="form-group">
                            <input type="text" class="form-control" name="userId" id="userId" placeholder="Id" autocomplete="Login" required>
                        </div>
                        <div class="form-group position-relative">
                            <input type="password" class="form-control" name="userPasswd" id="userPasswd" placeholder="Password" autocomplete="current-password" required>
                            <!-- 눈 모양 아이콘 -->
                            <div class="toggle-password" style="position: absolute; top: 50%; right: 10px; transform: translateY(-50%); cursor: pointer; display: none;">
                                <i class="far fa-eye"></i>
                            </div>
                        </div>

                        <div class="form-group form-check">
                            <input type="checkbox" class="form-check-input" id="rememberMe">
                            <label class="form-check-label" for="rememberMe">아이디 저장</label>
                        </div>

                        <button type="submit" class="btn btn-primary">로그인</button>
                        <a href="/join" class="btn btn-primary" role="button">회원가입</a>
                        <a href="/findId" class="btn btn-primary" role="button">아이디 찾기</a>
                        <a href="/findPw" class="btn btn-primary" role="button">비밀번호 찾기</a>
                        <a href="/guestLogin" class="btn btn-primary" role="button">비회원 로그인</a>
                    </form>

                    <!-- 카카오톡 로그인 버튼 -->
                    <button id="kakao-login-btn" class="btn btn-warning btn-block" style="background-color: transparent; border: none;">
                        <div class="text-SNS">
                            <br><b>SNS 계정 간편 로그인</b>
                        </div>
                        <br>
                        <img src="images/kakaoLog.png" alt="Kakao 로그인" style="width: 100%;">
                    </button>

                    <!-- 에러 메시지 표시 -->
                    <c:if test="${not empty error}">
                        <script>
                            alert("${error}");
                            document.getElementById('loginForm').reset();
                        </script>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 카카오 JavaScript SDK 추가 -->
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
    // 카카오 SDK 초기화
    Kakao.init('ff87e6e69cbcbe5b6b326ee2c92452e1'); // 카카오 디벨로퍼스에서 발급받은 JavaScript 키로 초기화

    // 로그인 버튼 클릭 이벤트
    document.getElementById('kakao-login-btn').addEventListener('click', function() {
        Kakao.Auth.authorize({
            redirectUri : 'http://localhost:8080/' // 설정
        });
    });

    // 비밀번호 입력란 마우스 이벤트
    const passwordInput = document.querySelector('input[name="userPasswd"]');
    const togglePassword = document.querySelector('.toggle-password');

    passwordInput.addEventListener('mouseenter', function() {
        togglePassword.style.display = 'block';
    });

    passwordInput.addEventListener('mouseleave', function() {
        togglePassword.style.display = 'none';
    });

    togglePassword.addEventListener('mouseenter', function() {
        passwordInput.type = 'text';
    });+

    togglePassword.addEventListener('mouseleave', function() {
        passwordInput.type = 'password';
    });

    // 아이디 저장 체크 박스 이벤트
    const rememberMeCheckbox = document.getElementById('rememberMe');
    const userIdInput = document.getElementById('userId');

    rememberMeCheckbox.addEventListener('change', function() {
        if (this.checked) {
            localStorage.setItem('savedUserId', userIdInput.value);
        } else {
            localStorage.removeItem('savedUserId');
        }
    });
</script>
</body>
</html>
