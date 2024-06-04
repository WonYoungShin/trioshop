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
        .btn-primary, .btn-secondary {
            min-width: 120px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="form-container" id="firstForm">
                <h2 class="form-heading text-center">비밀번호 확인</h2>
                <form action="" method="post">
                    <div class="form-group">
                        <label for="currentPassword">현재 비밀번호:</label>
                        <input type="password" class="form-control" id="currentPassword" name="currentPassword" placeholder="현재 비밀번호 입력">
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary mr-2" id="nextButton">다음</button>
                        <a href="/myPage" class="btn btn-secondary">뒤로가기</a>
                    </div>
                </form>
                <c:if test="${not empty message}">
                    <div class="alert alert-danger mt-3" role="alert">
                            ${message}
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
