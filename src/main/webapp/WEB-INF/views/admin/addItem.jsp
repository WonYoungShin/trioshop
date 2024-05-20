<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품등록</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<form action="" method="post">
    <div>
        <label for="itemName">상품이름</label>
        <input type="text" id="itemName" name="itemName"/>
    </div>
    <div>
        <label for="categoryCode">상품분류</label>
        <input type="text" id="categoryCode" name="categoryCode"/>
    </div>
    <div>
        <label for="factoryCode">제조업체</label>
        <input type="text" id="factoryCode" name="factoryCode"/>
    </div>
    <div>
        <label for="itemSize">상품사이즈</label>
        <input type="text" id="itemSize" name="itemSize"/>
    </div>
    <div>
        <label for="itemColor">상품컬러</label>
        <input type="text" id="itemColor" name="itemColor"/>
    </div>
    <div>
        <label for="itemPrice">판매가격</label>
        <input type="text" id="itemPrice" name="itemPrice"/>
    </div>
    <button type="submit">등록</button>
</form>
<div>
    <button type="button" onclick="location.href='/trioAdmin'">취소</button>
</div>

</body>
</html>
