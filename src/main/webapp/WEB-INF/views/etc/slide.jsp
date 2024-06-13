<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TRIOShop</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<style>
    #carouselExampleIndicators {
        margin-top: 130px;
    }

    .carousel-item img {
        width: 100%;
        height: 100%;
        object-fit: contain; /* 화면 확대/축소 시 고정*/
    }

    .carousel-control-prev-icon{
        margin-right: -40px; /* 아이콘 간격을 좁게 조정 */
    }

    .carousel-control-next-icon{
        margin-right: 22px; /* 아이콘 간격을 좁게 조정 */
    }


</style>
<body>
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" data-interval="3000">
    <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="/images/slide/slide1.png" class="d-block w-100" style="height: 750px;" alt="...">
        </div>
        <div class="carousel-item">
            <img src="/images/slide/slide2.png" class="d-block w-100" style="height: 750px;" alt="...">
        </div>
        <div class="carousel-item">
            <img src="/images/slide/slide3.png" class="d-block w-100" style="height: 750px;" alt="...">
        </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
</div>
<h1 style="margin-bottom: -180px"></h1>
<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
