<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Item Detail Page</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h2>Item Details</h2>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-6">
                    <img src="${item.itemSrc}" alt="${item.itemName}" class="img-fluid">
                </div>
                <div class="col-md-6">
                    <h3>${item.itemName}</h3>
                    <p><strong>Category:</strong> ${item.categoryName}</p>
                    <p><strong>Factory:</strong> ${item.factoryName}</p>
                    <p><strong>Color:</strong> ${item.itemColor}</p>
                    <p><strong>Size:</strong> ${item.itemSize}</p>
                    <p><strong>Stock Quantity:</strong> ${item.stockQty}</p>
                    <p><strong>Order Quantity:</strong> ${item.orderQty}</p>
                    <p><strong>Price:</strong> $${item.itemPrice}</p>

                    <label for="detailQty" class="mr-2">Quantity:</label>
                    <input type="number" id="detailQty" name="detailQty" value="1" class="form-control mr-2" min="1">

                    <form action="/addCart" method="post" class="form-inline mt-2" onsubmit="return updateQty('cartItemQty')">
                        <input type="hidden" name="itemCode" value="${item.itemCode}">
                        <input type="hidden" id="cartItemQty" name="cartItemQty">
                        <button type="submit" class="btn btn-primary mr-2">Add to Cart</button>
                    </form>

                    <form action="/orders" method="post" class="form-inline mt-2" onsubmit="return updateQty('orderQty')">
                        <input type="hidden" name="itemCodes" value="${item.itemCode}">
                        <input type="hidden" id="orderQty" name="quantities">
                        <button type="submit" class="btn btn-success">Order Now</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="card mt-4">
        <div class="card-header">
            <h3>Similar Items</h3>
        </div>
        <div class="card-body">
            <form id="similarItemsForm" method="post">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Select</th>
                            <th>Name</th>
                            <th>Color</th>
                            <th>Size</th>
                            <th>Order Quantity</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="itemDetail" items="${itemLists}">
                            <tr>
                                <td>
                                    <input type="checkbox" name="selectedItems" value="${itemDetail.itemCode}">
                                </td>
                                <td>${itemDetail.itemName}</td>
                                <td>${itemDetail.itemColor}</td>
                                <td>${itemDetail.itemSize}</td>
                                <td>
                                    <input type="number" name="quantities" value="1" class="form-control quantities_${itemDetail.itemCode}" min="1">
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <input type="hidden" name="itemCodes" id="itemCodes">
                <input type="hidden" name="quantitiesList" id="quantitiesList">
                <button type="button" class="btn btn-primary" onclick="addToCart()">Add Selected to Cart</button>
                <button type="button" class="btn btn-success" onclick="orderSelected()">Order Selected</button>
            </form>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
    function getSelectedItems() {
        let selectedItems = [];
        let quantities = [];
        document.querySelectorAll('input[name="selectedItems"]:checked').forEach((checkbox) => {
            const itemCode = checkbox.value;
            const quantity = document.querySelector(`.quantities_${itemCode}`).value;
            selectedItems.push(itemCode);
            quantities.push(quantity);
        });
        return { selectedItems, quantities };
    }

    function addToCart() {
        const { selectedItems, quantities } = getSelectedItems();
        if (selectedItems.length > 0) {
            document.getElementById('itemCodes').value = selectedItems.join(',');
            document.getElementById('quantitiesList').value = quantities.join(',');
            document.getElementById('similarItemsForm').action = "/addCarts";
            document.getElementById('similarItemsForm').submit();
        } else {
            alert("Please select at least one item.");
        }
    }

    function orderSelected() {
        const { selectedItems, quantities } = getSelectedItems();
        if (selectedItems.length > 0) {
            document.getElementById('itemCodes').value = selectedItems.join(',');
            document.getElementById('quantitiesList').value = quantities.join(',');
            document.getElementById('similarItemsForm').action = "/orders";
            document.getElementById('similarItemsForm').submit();
        } else {
            alert("Please select at least one item.");
        }
    }

    function updateQty(hiddenInputId) {
        const qty = document.getElementById('detailQty').value;
        document.getElementById(hiddenInputId).value = qty;
        return true;
    }
</script>
</body>
</html>
