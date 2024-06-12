<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/errorMessage.jsp" %>
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
        <div class="clear">
            <div class="hdCate">
                <div class="cateWrap">
                    <span class="tmenu">
                        <a href="<c:url value='/searchItems'><c:param name='category' value='top'/></c:url>">Top</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="<c:url value='/searchItems'><c:param name='itemName' value='Basic T-Shirt'/></c:url>">Basic T-Shirt</a></li>
                            <li><a href="<c:url value='/searchItems'><c:param name='itemName' value='Wool Sweater'/></c:url>">Wool Sweater</a></li>
                            <li><a href="<c:url value='/searchItems'><c:param name='itemName' value='Long Shirt'/></c:url>">Long Shirt</a></li>
                        </ul>
                    </span>
                    <span class="tmenu">
                        <a href="<c:url value='/searchItems'><c:param name='category' value='pants'/></c:url>">Pants</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="<c:url value='/searchItems'><c:param name='itemName' value='Classic Jeans'/></c:url>">Classic Jeans</a></li>
                            <li><a href="<c:url value='/searchItems'><c:param name='itemName' value='Short Pants'/></c:url>">Short Pants</a></li>
                        </ul>
                    </span>
                    <span class="tmenu">
                        <a href="<c:url value='/searchItems'><c:param name='category' value='outer'/></c:url>">Outer</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="<c:url value='/searchItems'><c:param name='itemName' value='Leather Jacket'/></c:url>">Leather Jacket</a></li>
                        </ul>
                    </span>
                    <span class="tmenu">
                        <a href="<c:url value='/searchItems'><c:param name='category' value='shoes'/></c:url>">Shoes</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="<c:url value='/searchItems'><c:param name='itemName' value='Formal Shoes'/></c:url>">Formal Shoes</a></li>
                        </ul>
                    </span>
                    <span class="tmenu">
                        <a href="<c:url value='/searchItems'><c:param name='category' value='acc'/></c:url>">Acc</a>
                        <ul class="depth2" style="display: none;">
                            <li><a href="<c:url value='/searchItems'><c:param name='itemName' value='Leather Belt'/></c:url>">Leather Belt</a></li>
                            <li><a href="<c:url value='/searchItems'><c:param name='itemName' value='Baseball Cap'/></c:url>">Baseball Cap</a></li>
                            <li><a href="<c:url value='/searchItems'><c:param name='itemName' value='Backpack'/></c:url>">Backpack</a></li>
                        </ul>
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
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
