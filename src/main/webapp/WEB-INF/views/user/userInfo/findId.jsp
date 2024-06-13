<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디찾기</title>
    <link rel="stylesheet" href="/css/findId.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <%--이미지--%>
    <link href="https://fonts.googleapis.com/css2?family=Alata&display=swap" rel="stylesheet">
    <%--폰트--%>
</head>
<body>
<form id="find_Id_Form" name="find_Id_Form" autocomplete="off" action="/findId" method="post">
    <div class="page-container">
        <div class="login-form-container shadow">
            <div class="login-form-right-side">
                <div class="top-logo-wrap">
                </div>
            </div>
            <div class="login-form-left-side">

                <div class="login-input-container">
                    <h1>Trio Shop</h1>
                    <h3>Forget Id</h3>

                    <div class="login-input-wrap input-id">
                        <i class="far fa-envelope"></i>
                        <input type="text" id="userName" name="userName" placeholder="이름" title="이름" class="input_text"
                               maxlength="11" value="" required autocomplete="off">
                    </div>
                    <div class="login-input-wrap input-Tel">
                        <i class="fa-solid fa-phone"></i>
                        <input type="tel" id="userTel" name="userTel" placeholder="전화번호" title="전화번호" class="input_text"
                               pattern="\d{3}-\d{3,4}-\d{4}" maxlength="13" required autocomplete="off">
                    </div>
                    <small class="form-text text-muted">올바른 전화번호 형식: 010-1234-5678</small>
                </div>

                <br>
                <c:if test="${not empty id}">
                    <div class="alert alert-success" role="alert">
                        당신의 아이디는 <strong>${id}</strong> 입니다.
                    </div>
                </c:if>


                <!-- 에러 메시지 표시 -->
                <c:if test="${not empty message}">
                    <script>
                        alert("${message}");
                        document.getElementById('loginForm').reset();
                    </script>
                </c:if>

                <div class="login-btn-wrap">
                    <button class="login-btn">아이디 찾기</button>
                    <a href="${pageContext.request.contextPath}/login">이미 계정이 있으신가요? <span style="color: #2178ff;">로그인하기</span></a>
                </div>
            </div>
        </div>
    </div>
</form>
<script>
    // 전화번호 입력란 자동 포맷팅
    document.getElementById('userTel').addEventListener('input', function (e) {
        var input = e.target.value.replace(/\D/g, ''); // 숫자만 남기기
        var formatted = '';

        if (input.length <= 3) {
            formatted = input;
        } else if (input.length <= 7) {
            formatted = input.slice(0, 3) + '-' + input.slice(3);
        } else {
            formatted = input.slice(0, 3) + '-' + input.slice(3, 7) + '-' + input.slice(7);
        }

        e.target.value = formatted.slice(0, 13); // 최대 11자까지 제한
    });

    // 아이디 입력란 초기화 버튼
    var userNameInput = document.getElementById('userName');
    var nameClearButton = document.getElementById('name_clear');
    userNameInput.addEventListener('input', function() {
        if (this.value.trim() !== '') {
            nameClearButton.style.display = 'block';
        } else {
            nameClearButton.style.display = 'none';
        }
        this.value = this.value.slice(0, 11); // 최대 11자까지 제한
    });

    nameClearButton.addEventListener('click', function() {
        userNameInput.value = '';
        this.style.display = 'none';
    });

    // 전화번호 입력란 초기화 버튼
    var userTelInput = document.getElementById('userTel');
    var TelClearButton = document.getElementById('Tel_clear');
    userTelInput.addEventListener('input', function() {
        if (this.value.trim() !== '') {
            TelClearButton.style.display = 'block';
        } else {
            TelClearButton.style.display = 'none';
        }
    });


    TelClearButton.addEventListener('click', function() {
        userTelInput.value = '';
        this.style.display = 'none';
    });

    // 비회원 로그인 버튼 클릭 시 폼 제출
    document.querySelector('.create-account-btn').addEventListener('click', function() {
        document.getElementById('find_Id_Form').submit();
        window.location.href = '/guestLogin';
    });

    // 초기화 버튼 숨기기
    nameClearButton.style.display = 'none';
    TelClearButton.style.display = 'none';
</script>
</body>
</html>
