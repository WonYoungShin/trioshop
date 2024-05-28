<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상태 변경</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <form id="statusForm" class="form-inline">
        <div class="form-group">
            <label for="status" class="mr-2">상태:</label>
            <select id="status" class="form-control mr-sm-2" name="statusCode">
                <c:forEach var="delivery" items="${statusList}">
                    <option value="${delivery.statusCode}" <c:if test="${delivery.statusCode == param.statusCode}">selected</c:if>>${delivery.statusName}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">확인</button>
    </form>
</div>

<!-- Bootstrap JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        var orderCode = '${orderCode}';
        $('#statusForm').on('submit', function(event) {
            event.preventDefault();
            var statusCode = $('#status').val();
            $.ajax({
                url: '/trioAdmin/orderStatus/edit/' + orderCode, // 컨트롤러 URL
                type: 'POST', // POST 메서드 사용
                data: { statusCode: statusCode },
                success: function(response) {
                    if (response.success) {
                        window.opener.location.reload(); // 부모 창 새로고침
                        window.close(); // 현재 창 닫기
                    } else {
                        alert('상태 변경에 실패했습니다.');
                    }
                },
                error: function() {
                    alert('에러가 발생했습니다.');
                }
            });
        });
    });
</script>
</body>
</html>
