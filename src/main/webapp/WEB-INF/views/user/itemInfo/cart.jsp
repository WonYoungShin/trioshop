<%--
  Created by IntelliJ IDEA.
  User: dst06
  Date: 2024-05-17
  Time: 오후 5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<div class="container">
    <h1 class="my-4">Shopping Cart</h1>
    <c:if test="${not empty cartItems}">
        <form id="orderForm" method="post" action="/orders">
            <input type="hidden" name="userCode" value="${loginMember.userCode}">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Select</th>
                    <th>Item Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Subtotal</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="cartItem" items="${cartItems}">
                    <tr>
                        <td>
                            <input type="checkbox" class="item-checkbox" value="${cartItem.cartCode}" data-item-code="${cartItem.itemCode}">
                        </td>
                        <td>${cartItem.itemName}</td>
                        <td>₩<span class="item-price">${cartItem.itemPrice}</span></td>
                        <td>
                            <input type="number" value="${cartItem.cartItemQty}" min="1" class="form-control quantity-input" style="width: 70px;" data-price="${cartItem.itemPrice}" data-item-code="${cartItem.itemCode}">
                        </td>
                        <td>₩<span class="item-subtotal">${cartItem.itemPrice * cartItem.cartItemQty}</span></td>
                        <td>
                            <form method="post" action="/cart/remove">
                                <button type="submit" class="btn btn-sm btn-danger">Remove</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="3" class="text-right"><strong>Total:</strong></td>
                    <td colspan="2">₩<span id="total-price">0</span></td>
                </tr>
                </tbody>
            </table>
            <button type="submit" class="btn btn-primary">Checkout</button>
        </form>
    </c:if>
    <c:if test="${empty cartItems}">
        <p>Your cart is empty. <a href="/items">Continue shopping</a></p>
    </c:if>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        // 총 가격 업데이트 함수
        function updateTotalPrice() {
            var totalPrice = 0;
            $('.quantity-input').each(function() {
                var quantity = $(this).val();
                var price = $(this).data('price');
                var subtotal = quantity * price;
                $(this).closest('tr').find('.item-subtotal').text(subtotal.toLocaleString());
                totalPrice += subtotal;
            });
            $('#total-price').text(totalPrice.toLocaleString());
        }

        // 페이지 로드 시 총 가격 초기화
        updateTotalPrice();

        // 수량 변경 시 총 가격 업데이트
        $('.quantity-input').on('change', function() {
            updateTotalPrice();
        });

        // 폼 제출 시 선택된 항목만 전송
        $('#orderForm').on('submit', function(event) {
            event.preventDefault();
            var form = $(this);
            form.find('input[name="itemCodes"]').remove();
            form.find('input[name="quantities"]').remove();
            $('.item-checkbox:checked').each(function() {
                var itemCode = $(this).data('item-code');
                var quantity = $(this).closest('tr').find('.quantity-input').val();
                $('<input>').attr({
                    type: 'hidden',
                    name: 'itemCodes',
                    value: itemCode
                }).appendTo(form);
                $('<input>').attr({
                    type: 'hidden',
                    name: 'quantities',
                    value: quantity
                }).appendTo(form);
            });
            form.off('submit').submit();
        });
    });
</script>
</body>
</html>
