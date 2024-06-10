<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>마이페이지</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="css/myPage.css">
</head>
<body>

<div id="wrapper">
    <div class="card" data-usercode="1">
        <header>정보 수정</header>
        <img src="/images/logo.png" alt="Image 1">
    </div>

    <div class="card" data-usercode="2">
        <header>비밀번호 변경</header>
        <img src="/images/logo.png" alt="Image 2">
    </div>

    <div class="card" data-usercode="3">
        <header>주문 내역</header>
        <img src="/images/logo.png" alt="Image 3">
    </div>

    <div class="card" data-usercode="4">
        <header>로그아웃</header>
        <img src="/images/logo.png" alt="Image 4">
    </div>

    <div class="card" data-usercode="5">
        <header>메인 메뉴로 돌아가기</header>
        <img src="/images/logo.png" alt="Image 5">
    </div>

</div>
<script>
    document.addEventListener('DOMContentLoaded', (event) => {
        const cards = document.querySelectorAll('.card');

        cards.forEach(card => {
            card.addEventListener('click', () => {
                const userCode = card.getAttribute('data-usercode');
                let url;
                switch(userCode) {
                    case '1':
                        url = `/passwordCheck/${userCode}?status=info`;
                        break;
                    case '2':
                        url = '/passwordCheck/${userCode}?status=pw';
                        break;
                    case '3':
                        url = `/orderList`;
                        break;
                    case '4':
                        url = `/logout`;
                        break;
                    case '5':
                        url = `/itemList`;
                        break;
                    default:
                        url = `#`;
                        break;
                }
                window.location.href = url;
            });
        });
    });
</script>
</body>
</html>
