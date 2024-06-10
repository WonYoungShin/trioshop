<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/errorMessage.html" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TRIOShop</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="css/hdarea.css">
</head>
<body>

<div id="hdWrap" class="headroom headroom--not-bottom headroom--pinned headroom--top">
    <div class="hdArea">
        <div class=" clear">
            <div class="hdCate">
                <div class="cateWrap">
                    <span class="tmenu">
                        <a href="/shop/shopbrand.html?xcode=065&amp;type=">MADE</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="/shop/shopbrand.html?xcode=065&amp;type=N&amp;mcode=002">티</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=065&amp;type=N&amp;mcode=004">셔츠</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=065&amp;type=N&amp;mcode=005">바지</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=065&amp;type=N&amp;mcode=003">아우터</a></li>
                        </ul>

                    </span>
                    <span class="tmenu">
                        <a href="/shop/shopbrand.html?xcode=052&amp;type=Y">BIG SIZE</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="/shop/shopbrand.html?xcode=052&amp;type=N&amp;mcode=001">티</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=052&amp;type=N&amp;mcode=002">셔츠</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=052&amp;type=N&amp;mcode=003">아우터</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=052&amp;type=N&amp;mcode=004">바지</a></li>
                        </ul>
                    </span>
                    <!--<span class="tmenu">
                        <a href="">BRAND</a>
                    </span>-->
                    <span class="tmenu">
                        <a href="/shop/shopbrand.html?xcode=078&amp;type=Y">TOP</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="/shop/shopbrand.html?xcode=078&amp;type=N&amp;mcode=009">맨투맨&amp;후드티</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=078&amp;type=N&amp;mcode=011">니트</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=078&amp;type=N&amp;mcode=004">긴팔티</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=078&amp;type=N&amp;mcode=010">반팔티</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=078&amp;type=N&amp;mcode=003">나시</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=078&amp;type=N&amp;mcode=002">프린팅티</a></li>
                        </ul>
                    </span>
                    <span class="tmenu">
                        <a href="/shop/shopbrand.html?xcode=055&amp;type=Y">SHIRTS</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="/shop/shopbrand.html?xcode=055&amp;type=N&amp;mcode=004">베이직</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=055&amp;type=N&amp;mcode=006">청남방</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=055&amp;type=N&amp;mcode=005">체크&amp;패턴</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=055&amp;type=N&amp;mcode=003">스트라이프</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=055&amp;type=N&amp;mcode=008">헨리넥&amp;차이나</a></li>
                        </ul>
                    </span>
                    <span class="tmenu">
                        <a href="/shop/shopbrand.html?xcode=081&amp;type=Y">PANTS</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="/shop/shopbrand.html?xcode=081&amp;type=N&amp;mcode=006">슬랙스</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=081&amp;type=N&amp;mcode=003">면바지</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=081&amp;type=N&amp;mcode=002">청바지</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=081&amp;type=N&amp;mcode=007">밴딩팬츠</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=081&amp;type=N&amp;mcode=004">반바지</a></li>
                        </ul>
                    </span>
                    <span class="tmenu">
                        <a href="/shop/shopbrand.html?xcode=079&amp;type=Y">OUTER</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="/shop/shopbrand.html?xcode=079&amp;type=N&amp;mcode=004">패딩</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=079&amp;type=N&amp;mcode=002">코트</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=079&amp;type=N&amp;mcode=006">수트&amp;블레이져</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=079&amp;type=N&amp;mcode=010">자켓</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=079&amp;type=N&amp;mcode=007">블루종/MA-1</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=079&amp;type=N&amp;mcode=005">가디건&amp;조끼</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=079&amp;type=N&amp;mcode=008">후드&amp;집업</a></li>
                        </ul>
                    </span>

                    <span class="tmenu">
                        <a href="/shop/shopbrand.html?xcode=083">SHOES&amp;BAG</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="/shop/shopbrand.html?xcode=083&amp;type=N&amp;mcode=001">신발</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=083&amp;type=N&amp;mcode=002">가방</a></li>
                        </ul>
                    </span>
                    <span class="tmenu">
                        <a href="/shop/shopbrand.html?xcode=080">ACC</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="/shop/shopbrand.html?xcode=080&amp;type=N&amp;mcode=005">양말&amp;타이</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=080&amp;type=N&amp;mcode=003">모자</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=080&amp;type=N&amp;mcode=008">벨트&amp;시계</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=080&amp;type=N&amp;mcode=006">머플러&amp;장갑</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=080&amp;type=N&amp;mcode=002">안경</a></li>
                            <li><a href="/shop/shopbrand.html?xcode=080&amp;type=N&amp;mcode=004">쥬얼리</a></li>
                           <li><a href="/shop/shopbrand.html?xcode=080&amp;type=N&amp;mcode=007">etc</a></li>
                        </ul>

                    </span>
                    <span class="tmenu">
                        <a href="/shop/shopbrand.html?xcode=068">당일배송</a>
                    </span>
                    <span class="tmenu">
                        <a href="/shop/shopbrand.html?xcode=076">SALE</a>
                    </span>
                    <span class="tmenu">
                        <a href="/shop/shopbrand.html?xcode=018">1+1</a>
                    </span>
                    <!--<span class="tmenu">
                        <a href="/shop/shopbrand.html?xcode=046">2022</a>
                    </span>-->
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
