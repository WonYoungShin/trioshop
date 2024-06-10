<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
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
<form id="find_Id_Form" name="find_Id_Form" autocomplete="on" action="/findId" method="post">
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

                    <%--                    <a href="#" target="">--%>
                    <%--                        <img src="/images/login/google.png" alt="Google">--%>
                    <%--                    </a>--%>
                    <%--                    <a href="#" target="">--%>
                    <%--                        <img src="/images/login/naver.png" alt="Naver">--%>
                    <%--                    </a>--%>
                    <%--                    <a href="#" target="">--%>
                    <%--                        <img src="/images/login/kakao.png" alt="Kakao">--%>
                    <%--                    </a>--%>


                    <div class="login-input-wrap input-id">
                        <i class="far fa-envelope"></i>
                        <input type="text" id="userName" name="userName" placeholder="이름" title="이름" class="input_text"
                               maxlength="4" value="" required autocomplete="off">
<%--                        <span role="button" class="btn_delete" id="name_clear" style="display: none;">--%>
<%--                           <i class="fa-solid fa-eraser"></i>--%>
<%--                          <span class="sr-only">삭제</span> <!-- 스크린 리더 사용자를 위한 숨겨진 텍스트 -->--%>
<%--                        </span>--%>
                    </div>
                    <div class="login-input-wrap input-password">
                        <i class="fa-solid fa-phone"></i>
                        <input type="tel" id="userTel" name="userTel" placeholder="전화번호" title="전화번호" class="input_text" pattern="\d{3}-\d{3,4}-\d{4}" required autocomplete="off">
<%--                        <span role="button" class="btn_delete" id="Tel_clear" style="display: none;">--%>
<%--                           <i class="fa-solid fa-eraser"></i>--%>
<%--                          <span class="sr-only">삭제</span> <!-- 스크린 리더 사용자를 위한 숨겨진 텍스트 -->--%>
<%--                        </span>--%>
                    </div>
                    <small style="margin-top: 5px;">올바른 전화번호 형식: 010-1234-5678</small>
                </div>

                <br>
                <c:if test="${not empty id}">
                    <div class="alert alert-success" role="alert">
                        당신의 아이디는 <strong>${id}</strong> 입니다.
                    </div>
                </c:if>
                <c:if test="${not empty message}">
                    <div class="alert alert-danger" role="alert">
                            ${message}
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
                    <a href="/login">이미 계정이 있으신가요? <span style="color: #2178ff;">로그인하기</span></a>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
<script>



    // 아이디 입력란
    var userNameInput = document.getElementById('userName');
    var nameClearButton = document.getElementById('name_clear');
    userNameInput.addEventListener('input', function() {
        if (this.value.trim() !== '') {
            nameClearButton.style.display = 'block';
        } else {
            nameClearButton.style.display = 'none';
        }
    });

    // 전화번호 입력란
    var userTelInput = document.getElementById('userTel');
    var TelClearButton = document.getElementById('Tel_clear');
    userTelInput.addEventListener('input', function() {
        if (this.value.trim() !== '') {
            TelClearButton.style.display = 'block';
        } else {
            TelClearButton.style.display = 'none';
        }
    });


    nameClearButton.addEventListener('click', function() {
        userNameInput.value = '';
        this.style.display = 'none';
    });


    TelClearButton.addEventListener('click', function() {
        userTelInput.value = '';
        this.style.display = 'none';
    });

    // 초기에 삭제 버튼 숨기기
    nameClearButton.style.display = 'none';
    TelClearButton.style.display = 'none';


    // 비회원 로그인 버튼 클릭 시 폼 제출
    document.querySelector('.create-account-btn').addEventListener('click', function() {
        // 폼 제출
        document.getElementById('find_Id_Form').submit();
        // /guestLogin으로 이동
        window.location.href = '/guestLogin';
    });

</script>
</html>