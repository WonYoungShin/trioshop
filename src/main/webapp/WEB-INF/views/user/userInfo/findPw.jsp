<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 찾기</title>
    <!-- 부트스트랩 CSS 링크 추가 -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header bg-primary text-white">비밀번호 찾기</div>
                <div class="card-body">
                    <form id="findPwForm" action="/findPw" method="post">
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
                    <div id="firstForm" style="display: ${showForm ? 'block' : 'none'};">
                        <hr>
                        <form id="updatePwForm" action="/findPw" method="post">
                            <div class="form-group">
                                <label for="newPassword">새 비밀번호</label>
                                <input type="password" class="form-control" name="newPassword" id="newPassword" placeholder="새 비밀번호를 입력해주세요">
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword">비밀번호 확인</label>
                                <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="새 비밀번호를 다시 입력해주세요">
                            </div>
                            <input type="hidden" name="userName" value="${userName}">
                            <input type="hidden" name="userId" value="${userId}">
                            <button type="submit" class="btn btn-primary btn-block">비밀번호 변경</button>
                        </form>
                    </div>
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
