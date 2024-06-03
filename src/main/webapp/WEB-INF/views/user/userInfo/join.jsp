<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .error-message {
            color: red;
        }

        #userIdErrorMessage {
            display: none;
            color: red;
            font-size: 12px;
            margin-left: 10px;
            cursor: pointer;
        }

        #userIdErrorMessage img {
            width: 12px;
            height: 12px;
            margin-bottom: -2px;
        }

        #userIdDescription {
            display: none;
            color: red;
            font-size: 12px;
            margin-left: 10px;
        }

        #userIdDescription:hover {
            cursor: pointer;
        }
    </style>
    <script>
        window.onload = function() {
            var successMessage = "<c:out value='${sessionScope.success}'/>";
            var errorMessage = "<c:out value='${sessionScope.error}'/>";

            if(successMessage !== "") {
                alert(successMessage);
            }

            if(errorMessage !== "") {
                alert(errorMessage);
            }
        };

        function toggleUserIdDescription() {
            var description = document.getElementById('userIdDescription');
            if (description.style.display === 'none') {
                description.style.display = 'inline';
            } else {
                description.style.display = 'none';
            }
        }

        function hideUserIdErrorMessageOnSubmit() {
            var errorMessageElement = document.getElementById('userIdErrorMessage');
            var userIdInput = document.getElementById('userId');
            if (errorMessageElement.style.display !== 'none') {
                userIdInput.focus();
                return false;
            }
            return true;
        }

        function checkUserIdAvailability() {
            var userId = document.getElementById('userId').value;
            // 이 부분에 실제로 서버와의 통신을 통해 아이디 중복 여부를 확인하는 로직을 추가합니다.
            // 예를 들어, Ajax를 사용하여 서버에 요청을 보낼 수 있습니다.
            // 여기서는 간단히 예시로 이미 존재하는 아이디를 "guest"로 가정합니다.
            if (userId === "guest") {
                document.getElementById('userIdErrorMessage').style.display = 'inline';
            } else {
                document.getElementById('userIdErrorMessage').style.display = 'none';
            }
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center">
                    <h4>회원가입</h4>
                </div>
                <div class="card-body">
                    <form id="joinForm" action="/join" method="post" onsubmit="return hideUserIdErrorMessageOnSubmit()">
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">
                                <c:out value="${sessionScope.error}"/>
                            </div>
                        </c:if>

                        <c:if test="${not empty success}">
                            <div class="alert alert-success">
                                <c:out value="${sessionScope.success}"/>
                            </div>
                        </c:if>

                        <div class="form-group">
                            <label for="userId">아이디:</label>
                            <input type="text" class="form-control" id="userId" name="userId" required onblur="checkUserIdAvailability()">
                            <div id="userIdErrorMessage" onclick="toggleUserIdDescription()">
                                <img src="exclamation_mark.png" alt="!"> 이미 존재하는 계정입니다.
                                <span id="userIdDescription">다른 아이디를 사용해주세요.</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="userPasswd">비밀번호:</label>
                            <input type="password" class="form-control" id="userPasswd" name="userPasswd" required>
                        </div>

                        <div class="form-group">
                            <label for="userName">이름:</label>
                            <input type="text" class="form-control" id="userName" name="userName" required>
                        </div>

                        <div class="form-group">
                            <label for="userAddress">주소:</label>
                            <input type="text" class="form-control" id="userAddress" name="userAddress" required>
                        </div>

                        <div class="form-group">
                            <label for="userTel">전화번호:</label>
                            <input type="text" class="form-control" id="userTel" name="userTel" required>
                        </div>

                        <div class="form-group">
                            <label for="userNickname">닉네임:</label>
                            <input type="text" class="form-control" id="userNickname" name="userNickname" required>
                        </div>

                        <button type="submit" class="btn btn-primary btn-block">회원가입</button>
                        <button type="button" class="btn btn-secondary btn-block" onclick="location.href='/login'">로그인창으로 돌아가기</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
