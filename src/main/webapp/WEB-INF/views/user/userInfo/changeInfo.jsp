<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>정보 수정</title>
    <!-- Bootstrap CDN -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            margin-top: 50px;
            padding: 30px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-group p {
            font-size: 1.1rem;
            font-weight: bold;
        }
        .form-heading {
            font-size: 2rem;
            font-weight: bold;
            margin-bottom: 20px;
            color: #343a40;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <c:choose>
                <c:when test="${empty userPatch}">
                    <div class="alert alert-warning text-center mt-5">
                        세션이 만료되었거나 잘못된 접근입니다.
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="form-container">
                        <h2 class="form-heading text-center">정보 입력하기</h2>
                        <form action="/changeInfo" method="post">
                            <div class="form-group">
                                <label for="newPassword">새 비밀번호:</label>
                                <input type="password" class="form-control" id="newPassword" name="userPasswd" value="${userPasswd}" placeholder="새 비밀번호">
                            </div>
                            <div class="form-group">
                                <label for="userAddress">주소:</label>
                                <input type="text" class="form-control" id="userAddress" name="userAddress" value="${userAddress}">
                            </div>
                            <div class="form-group">
                                <label for="userTel">전화번호:</label>
                                <input type="text" class="form-control" id="userTel" name="userTel" value="${UserTel}">
                            </div>
                            <div class="form-group">
                                <label for="userNickname">닉네임:</label>
                                <input type="text" class="form-control" id="userNickname" name="userNickname" value="${userNickname}">
                            </div>

                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">저장</button>
                                <a href="/myPage" class="btn btn-secondary">뒤로가기</a>
                            </div>
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
