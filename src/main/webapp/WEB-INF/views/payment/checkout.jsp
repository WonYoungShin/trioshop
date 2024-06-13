<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
    <script src="https://js.tosspayments.com/v1/payment-widget"></script>
</head>
<body>
<div class="wrapper">
    <div class="box_section" style="padding: 40px 30px 50px 30px; margin-top: 30px; margin-bottom: 50px;">
        <!-- 결제 UI -->
        <div id="payment-method"></div>
        <!-- 이용약관 UI -->
        <div id="agreement"></div>
        <!-- 결제하기 버튼 -->
        <div class="result wrapper">
            <button class="button" id="payment-button" style="margin-top: 30px">결제하기</button>
        </div>
    </div>
</div>
<script>
    const coupon = document.getElementById("coupon-box");
    const button = document.getElementById("payment-button");
    const amount = "${paymentData.amount}";

    // 구매자의 고유 아이디를 불러와서 customerKey로 설정하세요.
    // 이메일・전화번호와 같이 유추가 가능한 값은 안전하지 않습니다.
    // const widgetClientKey = "test_gck_docs_Ovk5rk1EwkEbP0W43n07xlzm";
    const widgetClientKey = "test_gck_docs_Ovk5rk1EwkEbP0W43n07xlzm";
    const customerKey = "${paymentData.userCode}";
    const paymentWidget = PaymentWidget(widgetClientKey, customerKey); // 회원 결제
    // const paymentWidget = PaymentWidget(widgetClientKey, PaymentWidget.ANONYMOUS) // 비회원 결제

    const paymentMethodWidget = paymentWidget.renderPaymentMethods(
        "#payment-method",
        {value: amount},
        {variantKey: "DEFAULT"}
    );

    paymentWidget.renderAgreement(
        "#agreement",
        {variantKey: "AGREEMENT"}
    );
    var successUrl = "${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/toss/success";
    var failUrl = "${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/toss/fail";
    button.addEventListener("click", function () {
        // 결제를 요청하기 전에 orderId, amount를 서버에 저장하세요.
        // 결제 과정에서 악의적으로 결제 금액이 바뀌는 것을 확인하는 용도입니다.
        paymentWidget.requestPayment({
            orderId: "${paymentData.orderId}",
            orderName: "${paymentData.orderName}",
            successUrl: successUrl,
            failUrl: failUrl,
            customerName: "${paymentData.userName}",
            customerMobilePhone: "${paymentData.userTel}",
        });
    });
</script>
</body>
</html>