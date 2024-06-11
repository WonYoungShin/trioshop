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
                       <a href="#">Top</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="#">Basic T-Shirt</a></li>
                            <li><a href="#">Wool Sweater</a></li>
                            <li><a href="#">Long Sweater</a></li>
                            <li><a href="#">Long Shirt</a></li>
                        </ul>

                    </span>
                    <span class="tmenu">
                        <a href="#">Pants</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="#">Classic Jeans</a></li>
                            <li><a href="#">Short Pants</a></li>
                        </ul>
                    </span>

                    <span class="tmenu">
                  <a href="#">Outer</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="#">Leather Jacket</a></li>
                        </ul>
                    </span>
                    <span class="tmenu">
                  <a href="#">Shoes</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="#">Formal Shoes</a></li>
                        </ul>
                    </span>
                    <span class="tmenu">
                           <a href="#">Acc</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="#">Leather Belt</a></li>
                            <li><a href="#">Baseball Cap</a></li>
                            <li><a href="#">Backpack</a></li>
                        </ul>
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
</form>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var tmenuItems = document.querySelectorAll('.tmenu');

        tmenuItems.forEach(function (tmenuItem) {
            tmenuItem.addEventListener('mouseover', function () {
                this.querySelector('.depth2').style.display = 'block';
            });

            tmenuItem.addEventListener('mouseout', function () {
                this.querySelector('.depth2').style.display = 'none';
            });
        });
    });
</script>
</body>
</html>
