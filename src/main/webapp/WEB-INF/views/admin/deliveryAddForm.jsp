<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>운송장</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title">운송장 정보 입력</h4>
                </div>
                <div class="card-body">
                    <form id="statusForm" action="" method="post">
                        <div class="form-group">
                            <label for="delivery">운송회사 :</label>
                            <select id="delivery" class="form-control" name="deliveryCode">
                                <option value="">==운송회사를 선택해주세요==</option>
                                <c:forEach var="delivery" items="${deliveryList}">
                                    <option value="${delivery.deliveryCode}" <c:if test="${delivery.deliveryCode == oldDeliveryCode}">selected</c:if>>${delivery.deliveryName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="waybillNum">운송장번호 :</label>
                            <input id="waybillNum" type="text" class="form-control" name="waybillNum" placeholder="-를 제외하고 입력해주세요." value="<c:out value='${oldWaybillNum}'/>" />
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">확인</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#statusForm').on('submit', function(event) {
            event.preventDefault(); // 기본 폼 제출 동작 방지

            var waybillNum = $('#waybillNum').val();
            if (!/^\d+$/.test(waybillNum)) {
                alert('숫자만 입력해주세요.');
                return false; // 폼 제출 중지
            }

            var $form = $(this);
            $.post($form.attr('action'), $form.serialize())
                .done(function(response) {
                    if (response === 'success') {
                        alert('입력 완료');
                        window.opener.location.reload(); // 부모 창 새로고침
                        window.close(); // 현재 창 닫기
                    } else {
                        alert('입력 오류가 발생했습니다. 다시 시도해주세요.');
                    }
                })
                .fail(function() {
                    alert('서버 오류가 발생했습니다. 나중에 다시 시도해주세요.');
                });
        });
    });
</script>

</body>
</html>
