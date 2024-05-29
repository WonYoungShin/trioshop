<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>4xx Bad Request</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .error-container {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
            background-color: #f8f9fa;
        }
        .error-content {
            max-width: 600px;
        }
        .error-title {
            font-size: 6rem;
            font-weight: 700;
        }
        .error-message {
            font-size: 1.5rem;
            margin: 20px 0;
        }
        .btn-home {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container error-container">
    <div class="error-content">
        <div class="error-title text-danger">4xx</div>
        <div class="error-message text-muted">Bad Request</div>
        <p class="text-muted">요청하신 페이지를 찾을 수 없습니다.</p>
        <a href="/" class="btn btn-primary btn-home">홈으로</a>
    </div>
</div>
</body>
</html>
