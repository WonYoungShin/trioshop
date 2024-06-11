<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/errorMessage.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>TRIOShop</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- 추가된 슬라이더 CSS -->
    <link rel="stylesheet" href="css/quickmenu.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        /* 슬라이더 스타일 */
        .carousel-item img {
            width: 100%;
            height: 500px;
        }
    </style>
</head>
<body>

<div class="quickMenuWrap">
    <div class="quickMenu" style="display: block;">

        <ul>
            <li class="recent"><a href="#" class="#"><span>#</span></a></li>
            <li class="wish"><a href="#"><span>#</span></a></li>
            <li class="attendance"><a href="#" onclick="javascript:window.open('#', '_self');" class="attend"><span>#</span></a></li>
            <li class="kakao"><a href="#none" onclick="javascript:window.open('#', '_self');" class="kakao"><span>#</span></a></li>
            <li class="naver"><a href="#none;" onclick="javascript:window.open('#', '_self');" class="talk"><span>#</span></a></li>
            <li class="top"><a onclick="" class="top"><span>top</span></a></li>
            <li class="bottom"><a onclick="" class="bottom"><span>bottom</span></a></li>
        </ul>

    <%--    <div class="quickScroll">
            <a onclick="" class="top pageTop"><i class="xi-angle-up"><i></i></i></a>
            <a onclick="" class="bottom pageBottom"><i class="xi-angle-down"><i></i></i></a>
        </div>--%>

    </div>
    <div id="MS_shopping_tab_inner">
        <h2>Shopping History<i class="xi-close"></i></h2>
        <div class="product_wrap">
            <div id="MS_today_product" class="MS_product"></div>
        </div>
    </div>
</div>


<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
</script>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        const topLink = document.querySelector('.top');
        const bottomLink = document.querySelector('.bottom');

        topLink.addEventListener('click', () => {
            window.scrollTo({
                top: 0,
            });
        });

        bottomLink.addEventListener('click', () => {
            window.scrollTo({
                top: document.body.scrollHeight,
            });
        });
    });
</script>
</body>
</html>
