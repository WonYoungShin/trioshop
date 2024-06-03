<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 페이지</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="my-4 text-center">주문 페이지</h1>

    <form id="orderForm" method="post" action="/placeOrder">
        <input type="hidden" name="userCode" value="${loginMember.userCode}">
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th>이미지</th>
                    <th>상품명</th>
                    <th>분류</th>
                    <th>제조사</th>
                    <th>가격</th>
                    <th>재고수량</th>
                    <th>주문수량</th>
                    <th>가격</th>
                    <th>색</th>
                    <th>크기</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${itemList}" varStatus="delivery">
                    <tr>
                        <td><img src="${item.itemSrc}" alt="${item.itemName}" class="img-thumbnail" style="width: 100px; height: 100px;"></td>
                        <td>${item.itemName}</td>
                        <td>${item.categoryName}</td>
                        <td>${item.factoryName}</td>
                        <td>₩<span class="item-price">${item.itemPrice}</span></td>
                        <td>${item.stockQty}</td>
                        <td>
                            <input type="hidden" name="orderItemEntityList[${delivery.index}].orderCode" value="${item.itemSrc}"/>
                            <input type="hidden" name="orderItemEntityList[${delivery.index}].itemCode" value="${item.itemCode}"/>
                            <input type="number" name="orderItemEntityList[${delivery.index}].orderQty" value="${item.orderQty}" min="1" max="${item.stockQty}" class="form-control quantity-input" data-price="${item.itemPrice}">
                        </td>
                        <td>₩<span class="item-subtotal">${item.itemPrice * item.orderQty}</span></td>
                        <td>${item.itemColor}</td>
                        <td>${item.itemSize}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="form-group">
            <label for="orderDestination">배송지</label>
            <input type="text" class="form-control" id="orderDestination" name="orderDestination" value="${userAddressInfo.userName}" required>
        </div>
        <div class="form-group">
            <label for="orderReceiver">수취인</label>
            <input type="text" class="form-control" id="orderReceiver" name="orderReceiver" value="${userAddressInfo.userAddress}" required>
        </div>
        <div class="form-group">
            <label for="orderTel">연락처</label>
            <input type="tel" class="form-control" id="orderTel" name="orderTel" value="${userAddressInfo.userTel}" required>
        </div>

        <h3 class="mt-4">Total Price: ₩<span id="total-price">0</span></h3>
        <button type="submit" class="btn btn-primary btn-block mt-3">결제하기</button>
    </form>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        function updateTotalPrice() {
            let totalPrice = 0;
            $('.quantity-input').each(function() {
                let quantity = $(this).val();
                let price = $(this).data('price');
                let subtotal = quantity * price;
                $(this).closest('tr').find('.item-subtotal').text(subtotal.toLocaleString());
                totalPrice += subtotal;
            });
            $('#total-price').text(totalPrice.toLocaleString());
        }

        // 페이지 로드 시 총 가격 초기화
        updateTotalPrice();

        // 수량 변경 시 총 가격 업데이트
        $('.quantity-input').on('input', function() {
            updateTotalPrice();
        });
    });
</script>
</body>
</html>
