<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>운송장 출력</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title">운송장 정보</h4>
                </div>
                <div class="card-body">
                    <div class="form-group">
                        <label>운송회사 :</label>
                        <p class="form-control-plaintext">${waybillModel.deliveryName}</p>
                    </div>
                    <div class="form-group">
                        <label>운송장번호 :</label>
                        <p class="form-control-plaintext">${waybillModel.waybillNum}</p>
                    </div>
                    <!-- <button onclick="window.history.back()" class="btn btn-secondary btn-block">뒤로</button> -->
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
