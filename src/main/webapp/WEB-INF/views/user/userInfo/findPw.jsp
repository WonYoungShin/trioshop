<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 찾기</title>
    <!-- 부트스트랩 CSS 링크 추가 -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">비밀번호 찾기</div>
                <div class="card-body">
                    <form action="/findPw" method="post">
                        <div class="form-group">
                            <label for="userName">이름</label>
                            <input type="text" class="form-control" name="userName" id="userName" placeholder="이름을 입력해주세요" required>
                        </div>
                        <div class="form-group">
                            <label for="userId">아이디</label>
                            <input type="text" class="form-control" name="userId" id="userId" placeholder="아이디를 입력해주세요" required>
                        </div>
                        <button type="submit" class="btn btn-primary">비밀번호 찾기</button>
                        <button type="button" onclick="location.href='/login'" class="btn btn-primary">로그인 페이지로 돌아가기</button>
                    </form>
                    <br>
                    <c:if test="${not empty userInfo}">
                        <div class="alert alert-success" role="alert">
                            당신의 비밀번호는 <strong>${userInfo.userPasswd}</strong> 입니다.
                        </div>
                    </c:if>
                    <c:if test="${not empty message}">
                        <div class="alert alert-danger" role="alert">
                                ${message}
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 부트스트랩 JS 및 jQuery 링크 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
