<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 찾기</title>
    <!-- 부트스트랩 CSS 링크 추가 -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header bg-primary text-white">비밀번호 찾기</div>
                <div class="card-body">
                    <form id="findPwForm" action="" method="post">
                        <div class="form-group">
                            <label for="userName">이름</label>
                            <input type="text" class="form-control" name="userName" id="userName" placeholder="이름을 입력해주세요" required>
                        </div>
                        <div class="form-group">
                            <label for="userId">아이디</label>
                            <input type="text" class="form-control" name="userId" id="userId" placeholder="아이디를 입력해주세요" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">비밀번호 찾기</button>
                        <button type="button" onclick="location.href='/login'" class="btn btn-secondary btn-block">로그인 페이지로 돌아가기</button>
                    </form>
                    <br>
                    <c:if test="${showForm}">
                        <div id="updatePwFormContainer">
                            <hr>
                            <form id="updatePwForm" action="${pageContext.request.contextPath}/updatePw" method="post" onsubmit="return validatePasswords()">
                                <div class="form-group">
                                    <label for="newPassword">새 비밀번호</label>
                                    <input type="password" class="form-control" name="newPassword" id="newPassword" placeholder="새 비밀번호를 입력해주세요" required>
                                </div>
                                <div class="form-group">
                                    <label for="confirmPassword">비밀번호 확인</label>
                                    <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="새 비밀번호를 다시 입력해주세요" required>
                                </div>
                                <div id="errorMessage" class="alert alert-danger" style="display:none;"></div>
                                <button type="submit" class="btn btn-primary btn-block">비밀번호 변경</button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${not empty message}">
                        <div class="alert alert-danger mt-3" role="alert">
                                ${message}
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>