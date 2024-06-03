<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>마이페이지</title>
    <!-- Bootstrap CDN -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .main-container {
            margin-top: 50px;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }
        h2 {
            margin-bottom: 30px;
            font-size: 2rem;
            font-weight: bold;
            color: #343a40;
        }
        .btn-custom {
            height: 50px;
            margin-bottom: 20px;
            font-size: 1.2rem;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8 main-container">
            <h2 class="text-center">마이페이지</h2>
            <div class="row">
                <!-- 정보 수정 링크 -->
                <div class="col-12">
                    <a href="/changeInfo" class="btn btn-primary btn-block btn-custom">정보 수정</a>
                </div>
            </div>
            <div class="row">
                <!-- 주문 내역 링크 -->
                <div class="col-12">
                    <a href="/orderList" class="btn btn-secondary btn-block btn-custom">주문 내역</a>
                </div>
            </div>
            <div class="row">
                <!-- 닉네임 수정 링크 -->
                <div class="col-12">
                    <a href="logout" class="btn btn-success btn-block btn-custom">로그아웃</a>
                </div>
            </div>
            <div class="row">
                <!-- 메인 메뉴로 돌아가기 버튼 -->
                <div class="col-12">
                    <a href="/itemList" class="btn btn-info btn-block btn-custom">메인 메뉴로 돌아가기</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
