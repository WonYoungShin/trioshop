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
    <link rel="stylesheet" href="/css/join.css">
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

        .img-fluid mx-auto d-block{
            width: 100px;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="form-container" id="secondForm" >
                <h2 class="form-heading text-center">추가 정보 입력하기</h2>
                <form action="" method="post" id="additionalInfoForm">
                    <img src="/images/changeInfo/logo.png" class="img-fluid mx-auto d-block" alt="Logo" style="max-width: 36%; margin-bottom: 29px;">
                    <div class="form-group">
                        <label for="userAddress">주소:</label>
                        <input type="text" class="form-control" id="userAddress" name="userAddress" value="${userPatchModel.userAddress}" required>
                    </div>
                    <div class="form-group">
                        <label for="userTel">전화번호:</label>
                        <input type="tel" class="form-control" id="userTel" name="userTel" value="${userPatchModel.userTel}" pattern="\d{3}-\d{3,4}-\d{4}" required>
                        <small class="form-text text-muted">올바른 전화번호 형식: 010-1234-5678</small>
                    </div>
                    <div class="form-group">
                        <label for="userNickname">닉네임:</label>
                        <input type="text" class="form-control" id="userNickname" name="userNickname" value="${userPatchModel.userNickname}" required>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary mr-2">저장</button>
                        <a href="/myPage" class="btn btn-secondary">뒤로가기</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById('userTel').addEventListener('input', function (e) {
        var input = e.target.value.replace(/\D/g, ''); // 숫자만 남기기
        var formatted = '';

        if (input.length <= 3) {
            formatted = input;
        } else if (input.length <= 7) {
            formatted = input.slice(0, 3) + '-' + input.slice(3);
        } else {
            formatted = input.slice(0, 3) + '-' + input.slice(3, 7) + '-' + input.slice(7, 11);
        }

        e.target.value = formatted;
    });
</script>
</body>
</html>
