<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품 상세 정보</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="card">
        <div class="card-header bg-primary text-white">
            <h2>상품 상세</h2>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-6">
                    <img src="${item.itemSrc}" alt="${item.itemName}" class="img-fluid rounded">
                </div>
                <div class="col-md-6">
                    <h3>${item.itemName}</h3>
                    <p><strong>분류:</strong> ${item.categoryName}</p>
                    <p><strong>제조사:</strong> ${item.factoryName}</p>
                    <p><strong>색상:</strong> ${item.itemColor}</p>
                    <p><strong>크기:</strong> ${item.itemSize}</p>
                    <p><strong>재고수량:</strong> ${item.stockQty}</p>
                    <p><strong>가격:</strong> $${item.itemPrice}</p>

                    <label for="detailQty" class="mr-2">선택 수량:</label>
                    <input type="number" id="detailQty" name="detailQty" value="1" class="form-control d-inline w-auto mr-2" min="1">

                    <form action="/addCart" method="post" class="form-inline mt-2" onsubmit="return updateQty('cartItemQty')">
                        <input type="hidden" name="itemCode" value="${item.itemCode}">
                        <input type="hidden" id="cartItemQty" name="cartItemQty">
                        <button type="submit" class="btn btn-primary mr-2">장바구니 추가</button>
                    </form>

                    <form action="/orders" method="post" class="form-inline mt-2" onsubmit="return updateQty('orderQty')">
                        <input type="hidden" name="itemCodes" value="${item.itemCode}">
                        <input type="hidden" id="orderQty" name="quantities">
                        <button type="submit" class="btn btn-success">주문하기</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="card mt-4">
        <div class="card-header bg-secondary text-white">
            <h3>유사 상품</h3>
        </div>
        <div class="card-body">
            <form id="similarItemsForm" method="post">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>선택</th>
                            <th>상품명</th>
                            <th>색상</th>
                            <th>크기</th>
                            <th>주문수량</th>
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
                <button type="button" class="btn btn-primary" onclick="addToCart()">선택 장바구니 추가</button>
                <button type="button" class="btn btn-success" onclick="orderSelected()">선택 주문하기</button>
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
