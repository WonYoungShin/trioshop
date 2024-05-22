<div class="container">
  <h1 class="my-4">All Items</h1>
  <div class="row mb-4">
    <div class="col-md-12 d-flex justify-content-end">
      <!-- 검색창 및 카테고리 선택 항목 결합 -->
      <form class="form-inline" method="get" action="${pageContext.request.contextPath}/searchItems">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="searchText">
        <select class="form-control mr-sm-2" name="categoryName">
          <option value="">Select Category</option>
          <c:forEach var="categoryName" items="${categoryList}">
            <option value="${categoryName}">${categoryName}</option>
          </c:forEach>
        </select>
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      </form>
    </div>
  </div>
  <div class="row">
    <c:forEach var="item" items="${itemList}">
      <div class="col-lg-4 col-md-6 mb-4">
        <div class="card h-100">
          <a href="${pageContext.request.contextPath}/item/${item.itemCode}">
            <img class="card-img-top" src="${item.itemSrc}" alt="${item.itemName}">
          </a>
          <div class="card-body">
            <h4 class="card-title">
              <a href="${pageContext.request.contextPath}/item/${item.itemCode}">${item.itemName}</a>
            </h4>
            <h5>₩${item.itemPrice}</h5>
            <p class="card-text">
              Category: ${item.categoryName}<br>
              Factory: ${item.factoryName}<br>
              Stock Quantity: ${item.stockQty}
            </p>
          </div>
          <div class="card-footer d-flex justify-content-between">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/item/${item.itemCode}">Details</a>
            <form method="post" action="${pageContext.request.contextPath}/orders">
              <input type="hidden" name="itemCode" value="${item.itemCode}">
              <button type="submit" class="btn btn-success">Order</button>
            </form>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</div>
