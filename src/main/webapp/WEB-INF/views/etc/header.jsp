<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TRIOShop</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">TRIOShop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <c:choose>
                <c:when test="${not empty loginMember}">
                    <!-- 로그인된 사용자의 닉네임 표시 -->
                    <li class="nav-item">
                        <span class="navbar-text mr-3">안녕하세요, ${loginMember.userNickname}님</span>
                    </li>
                    <c:choose>
                        <c:when test="${loginMember.gradeCode == 4}">
                            <!-- 관리자 메뉴 -->
                            <li class="nav-item">
                                <a class="nav-link" href="/trioAdmin/stock/list">재고</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/trioAdmin/stores">입고</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/trioAdmin/stores/list">입고내역</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/trioAdmin/purchase">발주</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/trioAdmin/purchase/list">발주내역</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/trioAdmin/stock">상품등록</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/logout">로그아웃</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <!-- 일반 사용자 메뉴 -->
                            <li class="nav-item">
                                <a class="nav-link" href="/orderList">주문내역</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/itemList">상품조회</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/cart">장바구니</a>
                            </li>
                            <c:if test="${loginMember.gradeCode != 0}">
                                <li class="nav-item">
                                    <a class="nav-link" href="/myPage">마이페이지</a>
                                </li>
                            </c:if>
                            <li class="nav-item">
                                <a class="nav-link" href="/logout">로그아웃</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <!-- 비회원 메뉴 -->
                    <li class="nav-item">
                        <a class="nav-link" href="/login">로그인</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/join">회원가입</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
