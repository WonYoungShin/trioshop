<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>


<!DOCTYPE html>
<html>
<head>
    <title>TRIO SHOP</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<style>

    .container {
        margin-top: 105px;
    }
</style>
<body>
<%--hdarea 보여주는 파트jsp--%>

<%@ include file="/WEB-INF/views/etc/hdarea.jsp" %>

<%--slider를 보여주는 파트jsp--%>
<%@ include file="/WEB-INF/views/etc/slide.jsp" %>

<%--quickmenu를 보여주는 파트jsp--%>
<%--<%@ include file="/WEB-INF/views/etc/quickmenu.jsp" %>--%>

<%--itemList를 보여주는 파트jsp--%>
<div class="container">
<%@ include file="/WEB-INF/views/etc/itemListPart.jsp" %>
</div>
<!-- 부트스트랩 JavaScript 링크 -->
</body>
</html>