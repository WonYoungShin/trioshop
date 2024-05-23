<%@ page import="com.trioshop.model.dto.item.OrdersEntity" %>
<%@ page import="com.trioshop.model.dto.item.OrderItemWrapper" %>
<%@ page import="com.trioshop.model.dto.item.OrderItemEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Form</title>
    <!-- 부트스트랩 CSS 링크 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <h2>Order Form</h2>
    <form method="post" action="/placeOrder" id="orderForm">
        <c:if test="${not empty itemList}">
            <c:forEach var="item" items="${itemList}" varStatus="status">
                <div class="card mb-4">
                    <div class="card-header">
                        <h4>Item Information</h4>
                    </div>
                    <div class="card-body">
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <img class="img-fluid" src="${item.itemSrc}" alt="${item.itemName}">
                            </div>
                            <div class="col-md-8">
                                <h5 class="card-title">${item.itemName}</h5>
                                <p class="card-text">Category: ${item.categoryName}</p>
                                <p class="card-text">Price: ₩${item.itemPrice}</p>
                                <div class="form-group">
                                    <label for="quantity-${item.itemCode}">Quantity</label>
                                    <input type="number" class="form-control quantity" id="quantity-${item.itemCode}" name="orderItemWrapper.orderItemEntityList[${status.index}].order_qty" value="1" min="1" max="${item.stockQty}" data-price="${item.itemPrice}" required>
                                </div>
                                <p class="card-text">Subtotal: ₩<span class="subtotal" id="subtotal-${item.itemCode}">${item.itemPrice}</span></p>
                                <input type="hidden" name="orderItemWrapper.orderItemEntityList[${status.index}].itemCode" value="${item.itemCode}">
                                <input type="hidden" name="orderItemWrapper.orderItemEntityList[${status.index}].orderCode" value="${ordersEntity.orderCode}">
                                <input type="hidden" name="userCode" value=${item.}>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div class="card mb-4">
                <div class="card-header">
                    <h4>Order Total</h4>
                </div>
                <div class="card-body">
                    <p class="card-text">Total Price: ₩<span id="totalPrice">0</span></p>
                </div>
            </div>
        </c:if>
        <div class="card mb-4">
            <div class="card-header">
                <h4>Order Details</h4>
            </div>
            <div class="card-body">
                <div class="form-group">
                    <label for="receiverName">Receiver Name</label>
                    <input type="text" class="form-control" id="receiverName" name="orderReceiver" required>
                </div>
                <div class="form-group">
                    <label for="address">Delivery Address</label>
                    <input type="text" class="form-control" id="address" name="orderDestination" required>
                </div>
                <div class="form-group">
                    <label for="phone">Contact Number</label>
                    <input type="text" class="form-control" id="phone" name="orderTel" required>
                </div>

            </div>
        </div>
        <button type="submit" class="btn btn-success">Place Order</button>
    </form>
</div>

<script>
    $(document).ready(function() {
        function updateTotal() {
            var total = 0;
            $(".quantity").each(function() {
                var price = $(this).data("price");
                var quantity = $(this).val();
                var subtotal = price * quantity;
                total += subtotal;
                var itemCode = $(this).attr('id').split('-')[1];
                $("#subtotal-" + itemCode).text(subtotal);
            });
            $("#totalPrice").text(total);
        }

        $(".quantity").on("input", function() {
            updateTotal();
        });
        // 초기 총합 계산
        updateTotal();
    });
</script>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
