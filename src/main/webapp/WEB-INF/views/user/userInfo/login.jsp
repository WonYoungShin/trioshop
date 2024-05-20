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
                    <div class="alert alert-info" role="alert">이 광고는 간단한 예시입니다.
                        여기에 광고 내용을 추가할 수 있습니다.</div>

                    <!-- 기본 로그인 폼 -->
                    <form id="loginForm" method="post" action="/login" autocomplete="on">
                        <div class="form-group">
                            <input type="text" class="form-control" name="userId"
                                   placeholder="Id" autocomplete="Login" required>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" name="userPasswd"
                                   placeholder="Password" autocomplete="current-password" required>
                        </div>

                        <button type="submit" class="btn btn-primary">로그인</button>
                        <button id="signup-btn" class="btn btn-primary" type="button">회원가입</button>
                        <button id="findId" class="btn btn-primary" type="button">아이디찾기</button>
                        <button id="findPw" class="btn btn-primary" type="button">비밀번호찾기</button>
                    </form>
                    <!-- 카카오톡 로그인 버튼 -->
                    <button id="kakao-login-btn" class="btn btn-warning btn-block"
                            style="background-color: transparent; border: none;">
                        <div class="text-SNS">
                            <br><b>SNS 계정 간편 로그인</b>
                        </div>
                        <br>
                        <img src="images/kakaoLog.png" alt="Kakao 로그인" style="width: 100%;">
                    </button>

                    <c:if test="${not empty error}">
                        <script>
                            alert("${error}");
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
            redirectUri : 'http://localhost:8080/join' // 설정한 Redirect URI
        });
    });
</script>

<!-- 부트스트랩 JS 및 jQuery 링크 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
