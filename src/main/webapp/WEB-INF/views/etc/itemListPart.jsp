<div class="container">
  <h1 class="my-4">All Items</h1>
  <div class="row mb-4">
    <div class="col-md-12 d-flex justify-content-end">
      <!-- 검색창 및 카테고리 선택 항목 결합 -->
      <form class="form-inline" method="get" action="${pageContext.request.contextPath}/searchItems">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="itemName">
        <select class="form-control mr-sm-2" name="category">
          <option value="">Select Category</option>
          <c:forEach var="category" items="${categoryList}">
            <option value="${category.categoryName}">${category.categoryName}</option>
          </c:forEach>
        </select>
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      </form>
    </div>
  </div>
  <div class="row">
    <c:forEach var="item" items="${itemList}">
      <div class="col-lg-4 col-md-6 mb-4">
        <div class="card h-100" onclick="location.href='${pageContext.request.contextPath}/item/${item.itemCode}'" style="cursor: pointer;">
          <img class="card-img-top" src="${item.itemSrc}" alt="${item.itemName}">
          <div class="card-body">
            <h4 class="card-title">${item.itemName}</h4>
            <h5>₩${item.itemPrice}</h5>
            <p class="card-text">
              Category: ${item.categoryName}<br>
              Factory: ${item.factoryName}<br>
              Stock Quantity: ${item.stockQty}
            </p>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</div>
