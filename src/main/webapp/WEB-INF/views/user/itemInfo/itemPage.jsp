<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Item Details</title>
    <!-- 부트스트랩 CSS 링크 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1 class="my-4">${item.itemName}</h1>
            <img class="img-fluid" src="${item.itemSrc}" alt="${item.itemName}">
            <h3>₩${item.itemPrice}</h3>
            <p><strong>Category:</strong> ${item.categoryName}</p>
            <p><strong>Factory:</strong> ${item.factoryName}</p>
            <p><strong>Stock Quantity:</strong> ${item.stockQty}</p>

            <div class="form-group">
                <label for="colorSelect">Color:</label>
                <select class="form-control" id="colorSelect">
                    <c:forEach var="color" items="${itemColors}">
                        <option value="${color}">${color}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="sizeSelect">Size:</label>
                <select class="form-control" id="sizeSelect">
                    <c:forEach var="size" items="${itemSizes}">
                        <option value="${size}">${size}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="button" class="btn btn-primary" onclick="addItem()">Add Item</button>
        </div>

        <div class="col-md-6">
            <h2 class="my-4">Order Summary</h2>
            <div id="orderSummary"></div>
            <form id="orderForm" action="/orders" method="post">
                <input type="hidden" name="userCode" value="${loginMember.userCode}">
                <input type="hidden" name="itemCodes" id="itemCodes" value="">
                <input type="hidden" name="quantities" id="quantities" value="">
                <button type="submit" class="btn btn-success mt-2">Order</button>
                <button type="button" class="btn btn-secondary mt-2" onclick="addCart()">Add to Cart</button>
            </form>
        </div>
    </div>
</div>

<!-- 부트스트랩 JavaScript 링크 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    let orderItems = [];

    function addItem() {
        const itemCode = "${item.itemCode}";
        const itemName = "${item.itemName}";
        const color = document.getElementById('colorSelect').value;
        const size = document.getElementById('sizeSelect').value;
        const quantity = 1;

        const newItem = { itemCode, itemName, color, size, quantity };
        orderItems.push(newItem);
        console.log("Item added:", newItem);
        updateOrderSummary();
        updateOrderForm();
    }

    function updateOrderSummary() {
        const orderSummary = document.getElementById('orderSummary');
        orderSummary.innerHTML = '';

        orderItems.forEach((subItem, index) => {
            console.log("Updating summary for item:", subItem);
            const itemElement = document.createElement('div');
            itemElement.className = 'order-item';

            itemElement.innerHTML =
                '<p><strong>' + subItem.itemName + '</strong> - ' + subItem.color + ', ' + subItem.size + '</p>' +
                '<div class="input-group mb-3" style="max-width: 200px;">' +
                '<div class="input-group-prepend">' +
                '<button class="btn btn-outline-secondary" type="button" onclick="changeItemQuantity(' + index + ', -1)">-</button>' +
                '</div>' +
                '<input type="text" class="form-control text-center" value="' + subItem.quantity + '" id="itemQty' + index + '" readonly>' +
                '<div class="input-group-append">' +
                '<button class="btn btn-outline-secondary" type="button" onclick="changeItemQuantity(' + index + ', 1)">+</button>' +
                '</div>' +
                '</div>' +
                '<button class="btn btn-danger" type="button" onclick="removeItem(' + index + ')">Remove</button>';

            orderSummary.appendChild(itemElement);
        });
    }

    function changeItemQuantity(index, amount) {
        let currentQty = parseInt(orderItems[index].quantity);
        let newQty = currentQty + amount;
        if (newQty < 1) newQty = 1;
        orderItems[index].quantity = newQty;
        document.getElementById('itemQty' + index).value = newQty;
        console.log('Quantity changed for item ' + index + ': ' + newQty);
        updateOrderForm();
    }

    function removeItem(index) {
        orderItems.splice(index, 1);
        console.log('Item removed at index ' + index);
        updateOrderSummary();
        updateOrderForm();
    }

    function updateOrderForm() {
        const itemCodesInput = document.getElementById('itemCodes');
        const quantitiesInput = document.getElementById('quantities');

        itemCodesInput.value = orderItems.map(subItem => subItem.itemCode).join(',');
        quantitiesInput.value = orderItems.map(subItem => subItem.quantity).join(',');
        console.log('Form updated:', itemCodesInput.value, quantitiesInput.value);
    }

    function addCart() {
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '/addCart';
        form.style.display = 'none';

        orderItems.forEach(item => {
            const itemCodeInput = document.createElement('input');
            itemCodeInput.type = 'hidden';
            itemCodeInput.name = 'itemCode';
            itemCodeInput.value = item.itemCode;
            form.appendChild(itemCodeInput);

            const itemQtyInput = document.createElement('input');
            itemQtyInput.type = 'hidden';
            itemQtyInput.name = 'cartItemQty';
            itemQtyInput.value = item.quantity;
            form.appendChild(itemQtyInput);
        });

        document.body.appendChild(form);
        form.submit();
    }
</script>
</body>
</html>
