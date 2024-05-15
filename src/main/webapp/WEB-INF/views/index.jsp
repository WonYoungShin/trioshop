<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>회원등록 페이지</title>
</head>
<body>
<form action="test" method="POST">
    ID: <input type="text" name="id" /><br />
    NAME: <input type="text" name="name" /><br />
    EMAIL: <input type="text" name="email" /><br />
    <input type="submit" value="회원등록" />
    <span></span>
</form>
</body>
</html>