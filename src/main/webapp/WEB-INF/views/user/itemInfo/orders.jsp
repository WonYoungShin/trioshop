<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="my-4">Order Page</h1>

    <form id="orderForm" method="post" action="/placeOrder">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Image</th>
                <th>Item Name</th>
                <th>Category</th>
                <th>Factory</th>
                <th>Price</th>
                <th>Stock Quantity</th>
                <th>Order Quantity</th>
                <th>Subtotal</th>
                <th>Color</th>
                <th>Size</th>
            </tr>
            </thead>
            <tbody>


            <c:forEach var="item" items="${itemList}" varStatus="status">
                <tr>
                    <td><img src="${item.itemSrc}" alt="${item.itemName}" style="width: 100px; height: 100px;"></td>
                    <td>${item.itemName}</td>
                    <td>${item.categoryName}</td>
                    <td>${item.factoryName}</td>
                    <td>₩<span class="item-price">${item.itemPrice}</span></td>
                    <td>${item.stockQty}</td>
                    <td>
                        <input type="hidden" name="orderItemEntityList[${status.index}].orderCode" value="${item.itemSrc}"/>
                        <input type="hidden" name="orderItemEntityList[${status.index}].itemCode" value="${item.itemCode}"/>
                        <input type="number" name="orderItemEntityList[${status.index}].orderQty" value="${item.orderQty}" min="1" max="${item.stockQty}" class="form-control quantity-input" data-price="${item.itemPrice}">
                    </td>
                    <td>₩<span class="item-subtotal">${item.itemPrice * item.orderQty}</span></td>
                    <td>${item.itemColor}</td>
                    <td>${item.itemSize}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="form-group">
            <label for="orderReceiver">Receiver Name</label>
            <input type="text" class="form-control" id="orderReceiver" name="orderReceiver" required>
        </div>
        <div class="form-group">
            <label for="orderDestination">Destination</label>
            <input type="text" class="form-control" id="orderDestination" name="orderDestination" required>
        </div>
        <div class="form-group">
            <label for="orderTel">Contact Number</label>
            <input type="tel" class="form-control" id="orderTel" name="orderTel" required>
        </div>

        <h3>Total Price: ₩<span id="total-price">0</span></h3>
            <c:set var="userCode" value="${sessionScope.loginMember.userCode}" />
        <button type="submit" class="btn btn-primary">Place Order</button>
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
