<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="updatePwForm" action="${pageContext.request.contextPath}/updatePw" method="post">
    <div class="page-container">
        <div class="login-form-container shadow">
            <div class="login-form-right-side">
                <div class="top-logo-wrap">
                </div>
            </div>
            <div class="login-form-left-side">
                <div class="login-input-container">
                    <h1>Trio Shop</h1>
                    <h3>New PassWd</h3>

                    <div class="login-input-wrap input-id">
                        <i class="fa-solid fa-signature"></i>
                        <input type="password" class="input_text" name="newPassword" id="newPassword"
                               placeholder="새 비밀번호를 입력해주세요" required>
                    </div>
                    <div class="login-input-wrap input-password">
                        <i class="far fa-envelope"></i>
                        <input type="password" class="input_text" name="confirmPassword" id="confirmPassword"
                               placeholder="새 비밀번호를 다시 입력해주세요" required>
                    </div>
                </div>
                <div class="login-btn-wrap">
                    <button class="login-btn">비밀번호 변경하기</button>
                    <a href="/login">이미 계정이 있으신가요? <span style="color: #2178ff;">로그인하기</span></a>
                </div>
            </div>
        </div>
    </div>
</form>
