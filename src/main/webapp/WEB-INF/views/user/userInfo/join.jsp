<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="/css/join.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <%--이미지--%>
    <link href="https://fonts.googleapis.com/css2?family=Alata&display=swap" rel="stylesheet">
    <%--폰트--%>
</head>
<body>
<form id="joinForm" name="joinForm" autocomplete="off" action="/join" method="post">
    <div class="page-container">
        <div class="login-form-container shadow">
            <div class="login-form-right-side">
                <div class="top-logo-wrap">
                </div>
            </div>
            <div class="login-form-left-side">

                <div class="login-input-container">
                    <h1>Trio Shop</h1>

                    <div class="login-input-wrap input-id">
                        <i class="far fa-envelope"></i>
                        <input type="text" id="userId" name="userId" placeholder="아이디" title="아이디" class="input_text"
                               maxlength="41" value="" required autocomplete="off">
                        <span role="button" class="btn_delete" id="id_clear" style="display: none;">
<%--                                <i class="fa-solid fa-eraser"></i>--%>
<%--                        <span class="sr-only">삭제</span> <!-- 스크린 리더 사용자를 위한 숨겨진 텍스트 -->--%>
<%--                        </span>--%>
                    </div>
<%--                    <button type="button" onclick="checkDuplicate()">중복 확인</button>--%>


                    <div class="login-input-wrap input-password">
                        <i class="fas fa-key"></i>
                        <input type="password" id="userPassWd" name="userPassWd" placeholder="비밀번호" title="비밀번호" class="input_text"
                               maxlength="41" value="" required autocomplete="off">
                        <span role="button" class="btn_delete" id="pw_clear" style="display: none;"> <!-- 삭제 버튼 -->
<%--                             <i class="fa-solid fa-eraser"></i>--%>
<%--                        <span class="sr-only">삭제</span> <!-- 스크린 리더 사용자를 위한 숨겨진 텍스트 -->--%>
<%--                        </span>--%>
                    </div>
                    <div class="login-input-wrap input-Name">
                        <i class="fa-solid fa-signature"></i>
                        <input type="text" id="userName" name="userName" placeholder="이름" title="이름" class="input_text"
                               maxlength="41" value="" required autocomplete="off">
<%--                        <span role="button" class="btn_delete" id="Name_clear" style="display: none;">--%>
<%--                            <i class="fa-solid fa-eraser"></i>--%>
<%--                        <span class="sr-only">삭제</span> <!-- 스크린 리더 사용자를 위한 숨겨진 텍스트 -->--%>
<%--                        </span>--%>
                    </div>
                    <div class="login-input-wrap input-Address">
                        <i class="fa-solid fa-map-location-dot"></i>
                        <input type="text" id="userAddress" name="userAddress" placeholder="주소" title="주소" class="input_text"
                               maxlength="41" value="" required autocomplete="off">
<%--                        <span role="button" class="btn_delete" id="Address_clear" style="display: none;">--%>
<%--                             <i class="fa-solid fa-address-book"></i>--%>
<%--                        <span class="sr-only">삭제</span> <!-- 스크린 리더 사용자를 위한 숨겨진 텍스트 -->--%>
<%--                        </span>--%>
                    </div>
                    <div class="login-input-wrap input-Nickname">
                        <i class="fa-solid fa-user"></i>
                        <input type="text" id="userNickname" name="userNickname" placeholder="닉네임" title="닉네임" class="input_text"
                               maxlength="41" value="" required autocomplete="off">
<%--                        <span role="button" class="btn_delete" id="Nickname_clear" style="display: none;">--%>
<%--                              <i class="fa-solid fa-eraser"></i>--%>
<%--                        <span class="sr-only">삭제</span> <!-- 스크린 리더 사용자를 위한 숨겨진 텍스트 -->--%>
<%--                        </span>--%>
                    </div>
                    <div class="login-input-wrap input-Tel">
                        <i class="fa-solid fa-phone"></i>
                        <input type="tel" id="userTel" name="userTel" placeholder="전화번호" title="전화번호" class="input_text" pattern="\d{3}-\d{3,4}-\d{4}" required autocomplete="off">
<%--                        <span role="button" class="btn_delete" id="Tel_clear" style="display: none;">--%>
<%--                          <i class="fa-solid fa-eraser"></i>--%>
<%--                        <span class="sr-only">삭제</span> <!-- 스크린 리더 사용자를 위한 숨겨진 텍스트 -->--%>
<%--                        </span>--%>
                    </div>
                    <small class="form-text text-muted">올바른 전화번호 형식: 010-1234-5678</small>

<%--                <!-- 에러 메시지 표시 -->--%>
<%--                    <c:if test="${not empty message}">--%>
<%--                        <script>--%>
<%--                            // 에러 메시지 표시--%>
<%--                            var errorMessage = "${message}";--%>
<%--                            if (errorMessage === 'duplicate') {--%>
<%--                                alert('이미 사용 중인 아이디입니다.');--%>
<%--                            } else if (errorMessage === 'success') {--%>
<%--                                alert('회원가입에 성공했습니다.');--%>
<%--                                window.location.href = '/login'; // 회원가입 성공 시 로그인 페이지로 이동--%>
<%--                            } else {--%>
<%--                                alert(errorMessage);--%>
<%--                            }--%>
<%--                            document.getElementById('joinForm').reset();--%>
<%--                        </script>--%>
<%--                    </c:if>--%>

                <div class="login-btn-wrap">
                    <button class="login-btn join-btn">가입하기</button>
                    <a href="/login">로그인 페이지로 돌아가기</a>
                </div>


            </div>
        </div>
    </div>

    </div>
</form>
</body>
<script>


    //guestLogin 이동
    document.addEventListener('DOMContentLoaded', function () {
        const createAccountBtn = document.querySelector('.create-account-btn');
        if (createAccountBtn) {
            createAccountBtn.addEventListener('click', function () {
                window.location.href = '/guestLogin';
            });
        }
    });


    // 아이디 입력란
    var userIdInput = document.getElementById('userId');
    var idClearButton = document.getElementById('id_clear');
    userIdInput.addEventListener('input', function() {
        if (this.value.trim() !== '') {
            idClearButton.style.display = 'block';
        } else {
            idClearButton.style.display = 'none';
        }
    });

    // 비밀번호 입력란
    var passwdInput = document.getElementById('userPasswd');
    var pwClearButton = document.getElementById('pw_clear');
    passwdInput.addEventListener('input', function() {
        if (this.value.trim() !== '') {
            pwClearButton.style.display = 'block';
        } else {
            pwClearButton.style.display = 'none';
        }
    });

    // 아이디 입력란 삭제 버튼 클릭 시
    idClearButton.addEventListener('click', function() {
        userIdInput.value = '';
        this.style.display = 'none';
    });

    // 비밀번호 입력란 삭제 버튼 클릭 시
    pwClearButton.addEventListener('click', function() {
        passwdInput.value = '';
        this.style.display = 'none';
    });

    // 초기에 삭제 버튼 숨기기
    idClearButton.style.display = 'none';
    pwClearButton.style.display = 'none';


    // 이름 입력란
    var nameInput = document.getElementById('userName');
    var nameClearButton = document.getElementById('Name_clear');
    nameInput.addEventListener('input', function() {
        if (this.value.trim() !== '') {
            nameClearButton.style.display = 'block';
        } else {
            nameClearButton.style.display = 'none';
        }
    });

    // 주소 입력란
    var addressInput = document.getElementById('userAddress');
    var addressClearButton = document.getElementById('Address_clear');
    addressInput.addEventListener('input', function() {
        if (this.value.trim() !== '') {
            addressClearButton.style.display = 'block';
        } else {
            addressClearButton.style.display = 'none';
        }
    });

    // 닉네임 입력란
    var nicknameInput = document.getElementById('userNickname');
    var nicknameClearButton = document.getElementById('Nickname_clear');
    nicknameInput.addEventListener('input', function() {
        if (this.value.trim() !== '') {
            nicknameClearButton.style.display = 'block';
        } else {
            nicknameClearButton.style.display = 'none';
        }
    });

    // 전화번호 입력란
    var telInput = document.getElementById('userTel');
    var telClearButton = document.getElementById('Tel_clear');
    telInput.addEventListener('input', function() {
        if (this.value.trim() !== '') {
            telClearButton.style.display = 'block';
        } else {
            telClearButton.style.display = 'none';
        }
    });

    // 이름 입력란 삭제 버튼 클릭 시
    nameClearButton.addEventListener('click', function() {
        nameInput.value = '';
        this.style.display = 'none';
    });

    // 주소 입력란 삭제 버튼 클릭 시
    addressClearButton.addEventListener('click', function() {
        addressInput.value = '';
        this.style.display = 'none';
    });

    // 닉네임 입력란 삭제 버튼 클릭 시
    nicknameClearButton.addEventListener('click', function() {
        nicknameInput.value = '';
        this.style.display = 'none';
    });

    // 전화번호 입력란 삭제 버튼 클릭 시
    telClearButton.addEventListener('click', function() {
        telInput.value = '';
        this.style.display = 'none';
    });

    // 초기에 삭제 버튼 숨기기
    nameClearButton.style.display = 'none';
    addressClearButton.style.display = 'none';
    nicknameClearButton.style.display = 'none';
    telClearButton.style.display = 'none';


    // //중복확인
    // function checkDuplicate() {
    //     var userId = document.getElementById('userId').value;
    //
    //     // AJAX 요청
    //     var xhr = new XMLHttpRequest();
    //     xhr.open('GET', '/checkDuplicate?userId=' + userId, true);
    //
    //     xhr.onload = function () {
    //         if (xhr.status >= 200 && xhr.status < 400) {
    //             var response = xhr.responseText;
    //             if (response === 'duplicate') {
    //                 alert('이미 사용 중인 아이디입니다.');
    //             } else {
    //                 alert('사용 가능한 아이디입니다.');
    //             }
    //         } else {
    //             alert('서버와의 통신에 문제가 발생했습니다.');
    //         }
    //     };
    //
    //     xhr.send();
    // }


</script>
</html>
