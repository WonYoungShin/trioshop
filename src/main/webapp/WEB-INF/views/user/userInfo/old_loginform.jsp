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
    <style>
        .card {
            border: none;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .card-header {
            background-color: #007bff;
            color: white;
            font-weight: bold;
            border-bottom: none;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-control {
            border: 1px solid #ced4da;
            border-radius: 0.25rem;
            padding: 0.5rem 1rem;
        }

        .toggle-password {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            cursor: pointer;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-block {
            display: block;
            width: 100%;
        }

        .btn-outline-primary {
            color: #007bff;
            border-color: #007bff;
        }

        .btn-outline-primary:hover {
            color: #0056b3;
            border-color: #0056b3;
        }
    </style>
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

                    <!-- 기본 로그인 폼 -->
                    <form id="loginForm" method="post" action="">
                        <div class="form-group">
                            <input type="text" class="form-control" name="userId" id="userId" placeholder="ID" autocomplete="Login" required>
                        </div>
                        <div class="form-group position-relative">
                            <input type="password" class="form-control" name="userPasswd" id="userPasswd" placeholder="Password" required>
                            <!-- 눈 모양 아이콘 -->
                            <div class="toggle-password">
                                <i class="far fa-eye"></i>
                            </div>
                        </div>

                        <div class="form-group form-check">
                            <input type="checkbox" class="form-check-input" id="rememberMe">
                            <label class="form-check-label" for="rememberMe">아이디 저장</label>
                        </div>

                        <button type="submit" class="btn btn-primary btn-block">로그인</button>
                        <a href="/guestLogin" class="btn btn-outline-primary btn-block">비회원 로그인</a>
                        <a href="/join" class="btn btn-outline-primary btn-block">회원가입</a>
                        <a href="/findId" class="btn btn-outline-primary btn-block">아이디 찾기</a>
                        <a href="/findPw" class="btn btn-outline-primary btn-block">비밀번호 찾기</a>

                        <!-- 에러 메시지 표시 -->
                        <c:if test="${not empty message}">
                            <script>
                                alert("${message}");
                                document.getElementById('loginForm').reset();
                            </script>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>