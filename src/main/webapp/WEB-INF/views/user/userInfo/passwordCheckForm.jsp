<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>정보 수정</title>
    <link rel="stylesheet" href="/css/passwordCheckForm.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <%--이미지--%>
    <link href="https://fonts.googleapis.com/css2?family=Alata&display=swap" rel="stylesheet">
    <%--폰트--%>
</head>

</head>
<body>
<div class="page-container">
    <div class="login-form-container shadow">
        <div class="login-form-right-side">
            <div class="top-logo-wrap">
            </div>
        </div>
        <div class="login-form-left-side">
            <div class="login-input-container">
                <h1>Trio Shop</h1>
                <h3>cheak Pw</h3>
                <form action="" method="post">
                    <div class="login-input-wrap input-id">
                        <i class="fa-solid fa-signature"></i>
                        <input type="password" class="form-control" id="currentPassword" name="currentPassword" placeholder="현재 비밀번호 입력" required>
                    </div>

                    <div class="login-btn-wrap">
                        <button class="login-btn">비밀번호 확인</button>
                        <a href="${pageContext.request.contextPath}/myPage" class="btn btn-secondary">뒤로가기</a>
                    </div>

                </form>
                <c:if test="${not empty message}">
                    <div class="alert alert-danger mt-3" role="alert">
                            ${message}
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
