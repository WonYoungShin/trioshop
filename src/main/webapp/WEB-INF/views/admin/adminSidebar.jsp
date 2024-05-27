<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .sidebar {
        width: 250px;
        background: #f8f9fa;
        flex-shrink: 0;
        border-right: 1px solid #dee2e6;
        height: 100vh;
        position: fixed;
    }
    .sidebar .nav-link {
        color: #343a40;
    }
    .sidebar .nav-link:hover {
        background-color: #e9ecef;
    }

</style>

<div class="sidebar bg-light">
    <div class="list-group list-group-flush">
        <a href="/trioAdmin" class="list-group-item list-group-item-action bg-light text-dark">홈</a>
        <a href="/trioAdmin/stock/list" class="list-group-item list-group-item-action bg-light text-dark">재고 관리</a>
        <a href="/trioAdmin/stock" class="list-group-item list-group-item-action bg-light text-dark">상품 등록</a>
        <a href="/trioAdmin/stores" class="list-group-item list-group-item-action bg-light text-dark">입고 등록</a>
        <a href="/trioAdmin/stores/list" class="list-group-item list-group-item-action bg-light text-dark">입고 내역</a>
        <a href="/trioAdmin/purchase" class="list-group-item list-group-item-action bg-light text-dark">발주 등록</a>
        <a href="/trioAdmin/purchase/list" class="list-group-item list-group-item-action bg-light text-dark">발주 내역</a>
        <a href="/trioAdmin/sales" class="list-group-item list-group-item-action bg-light text-dark">매출</a>
        <a href="/trioAdmin/orderStatus" class="list-group-item list-group-item-action bg-light text-dark">주문 상태 관리</a>
    </div>
</div>
