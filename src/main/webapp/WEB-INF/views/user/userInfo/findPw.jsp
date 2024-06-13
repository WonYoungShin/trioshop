<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호</title>
    <link rel="stylesheet" href="/css/findPw.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <%--이미지--%>
    <link href="https://fonts.googleapis.com/css2?family=Alata&display=swap" rel="stylesheet">
    <%--폰트--%>
</head>

<body>
<c:choose>
    <c:when test="${not findPwSuccess}">
        <form id="find_Pw_Form" name="find_Pw_Form" autocomplete="off" action="/findPw" method="post">
            <div class="page-container">
                <div class="login-form-container shadow">
                    <div class="login-form-right-side">
                        <div class="top-logo-wrap">
                        </div>
                    </div>
                    <div class="login-form-left-side">
                        <div class="login-input-container">
                            <h1>Trio Shop</h1>
                            <h3>Forget Pw</h3>

                            <div class="login-input-wrap input-id">
                                <i class="fa-solid fa-signature"></i>
                                <input type="text" id="userName" name="userName" placeholder="이름" title="이름" class="input_text"
                                       maxlength="4" value="" required autocomplete="off">
                            </div>
                            <div class="login-input-wrap input-password">
                                <i class="far fa-envelope"></i>
                                <input type="text" id="userId" name="userId" placeholder="아이디" title="아이디" class="input_text"
                                       required autocomplete="off">
                            </div>
                        </div>
                        <div class="login-btn-wrap">
                            <button class="login-btn">비밀번호 찾기</button>
                            <a href="${pageContext.request.contextPath}/login">이미 계정이 있으신가요? <span style="color: #2178ff;">로그인하기</span></a>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </c:when>
    <c:otherwise>
        <%@ include file="/WEB-INF/views/user/userInfo/updatePw.jsp" %>
    </c:otherwise>
</c:choose>

</body>
<script>

    // 아이디 입력란
    var userNameInput = document.getElementById('userName');
    var nameClearButton = document.getElementById('name_clear');
    userNameInput.addEventListener('input', function () {
        if (this.value.trim() !== '') {
            nameClearButton.style.display = 'block';
        } else {
            nameClearButton.style.display = 'none';
        }
    });

    // 전화번호 입력란
    var userIdInput = document.getElementById('userId');
    var IdClearButton = document.getElementById('Id_clear');
    userTelInput.addEventListener('input', function () {
        if (this.value.trim() !== '') {
            IdClearButton.style.display = 'block';
        } else {
            IdClearButton.style.display = 'none';
        }
    });


    nameClearButton.addEventListener('click', function () {
        userNameInput.value = '';
        this.style.display = 'none';
    });


    IdClearButton.addEventListener('click', function () {
        userIdInput.value = '';
        this.style.display = 'none';
    });

    // 초기에 삭제 버튼 숨기기
    nameClearButton.style.display = 'none';
    IdClearButton.style.display = 'none';


    // 비회원 로그인 버튼 클릭 시 폼 제출
    document.querySelector('.create-account-btn').addEventListener('click', function () {
        // 폼 제출
        document.getElementById('find_Pw_Form').submit();
        // /guestLogin으로 이동
        window.location.href = '/guestLogin';
    });

    // 비밀번호 확인
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
</html>