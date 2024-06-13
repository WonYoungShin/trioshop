<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 찾기</title>
    <!-- 부트스트랩 CSS 링크 추가 -->
    <link rel="stylesheet" href="/css/changePasswordForm.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <%--이미지--%>
    <link href="https://fonts.googleapis.com/css2?family=Alata&display=swap" rel="stylesheet">
    <script>
        function validatePasswords() {
            var newPassword = document.getElementById("newPassword").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var errorMessage = document.getElementById("errorMessage");

            if (newPassword !== confirmPassword) {
                errorMessage.style.display = 'block';
                errorMessage.innerHTML = '비밀번호가 일치하지 않습니다.';
                return false;
            } else {
                errorMessage.style.display = 'none';
                return true;
            }
        }
    </script>
</head>
<body>
<form id="updatePwForm" action="" method="post" onsubmit="return validatePasswords()">
    <div class="page-container">
        <div class="login-form-container shadow">
            <div class="login-form-right-side">
                <div class="top-logo-wrap">
                </div>
            </div>
            <div class="login-form-left-side">
                <div class="login-input-container">
                    <h1>Trio Shop</h1>
                    <h3>change Pw</h3>

                    <div class="login-input-wrap input-id">
                        <i class="fa-solid fa-signature"></i>
                        <input type="password" class="form-control" name="newPassword" id="newPassword" placeholder="새 비밀번호를 입력해주세요" required>
                    </div>
                    <div class="login-input-wrap input-password">
                        <i class="far fa-envelope"></i>
                        <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="새 비밀번호를 다시 입력해주세요" required>
                    </div>
                    <div id="errorMessage" class="alert alert-danger" style="display:none;"></div>
                    <div class="login-btn-wrap">
                        <button type="submit" class="login-btn">비밀번호 확인</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</div>
<c:if test="${not empty message}">
    <div class="alert alert-danger mt-3" role="alert">
            ${message}
    </div>
</c:if>

</body>
</html>