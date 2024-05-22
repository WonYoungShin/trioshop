<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <style>
        /* 에러 메시지 스타일 */
        .error-message {
            color: red;
        }

        /* 아이디 중복 에러 스타일 */
        #userId {
            border: 2px solid red;
        }

        /* 아이디 중복 에러 문구 스타일 */
        #userIdErrorMessage {
            display: none;
            color: red;
            font-size: 12px;
            margin-left: 10px;
            cursor: pointer;
        }

        /* 느낌표 이미지 스타일 */
        #userIdErrorMessage img {
            width: 12px;
            height: 12px;
            margin-bottom: -2px;
        }

        /* 설명창 스타일 */
        #userIdDescription {
            display: none;
            color: red;
            font-size: 12px;
            margin-left: 10px;
        }

        /* 마우스 포인터 변경 */
        #userIdDescription:hover {
            cursor: pointer;
        }
    </style>
    <script>
        // 페이지 로드 시 에러 메시지가 있으면 알림창으로 표시
        window.onload = function() {
            var successMessage = "${success}";
            var errorMessage = "${error}";

            if(successMessage !== "") {
                alert(successMessage);
            }

            if(errorMessage !== "") {
                alert(errorMessage);
            }
        };
    </script>
</head>
<body>
<form id="joinForm" action="/join" method="post" onsubmit="return hideUserIdErrorMessageOnSubmit()">
    <!-- 에러 메시지 표시 -->
    <c:if test="${not empty error}">
        <p class="error-message">${error}</p>
    </c:if>

    <!-- 성공 메시지 표시 -->
    <c:if test="${not empty success}">
        <p style="color: green">${success}</p>
    </c:if>

    <label for="userId">아이디:</label>
    <input type="text" id="userId" name="userId" required>
    <span id="userIdErrorMessage" onclick="toggleUserIdDescription()">
        <img src="exclamation_mark.png" alt="!"> 이미 존재하는 계정입니다.
        <span id="userIdDescription" onclick="toggleUserIdDescription()">다른 아이디를 사용해주세요.</span>
    </span>
    <br>

    <label for="userPasswd">비밀번호:</label>
    <input type="password" id="userPasswd" name="userPasswd" required><br>

    <label for="userName">이름:</label>
    <input type="text" id="userName" name="userName" required><br>

    <label for="userAddress">주소:</label>
    <input type="text" id="userAddress" name="userAddress" required><br>

    <label for="userTel">전화번호:</label>
    <input type="text" id="userTel" name="userTel" required><br>

    <label for="userNickname">닉네임:</label>
    <input type="text" id="userNickname" name="userNickname" required><br>

    <input type="submit" value="회원가입">
    <input type="button" value="로그인창으로 돌아가기" onclick="location.href='/login'">
</form>
</body>
</html>
