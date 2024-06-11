<!-- /WEB-INF/views/etc/error.html -->
<!-- 일단은 헤더에 포함 errorMessage변수가 있으면 경고창을 띄우도록함-->
<%-- 알람 중복 생성을 막기위해 errorMessageIncluded 사용 --%>
<% if (pageContext.getAttribute("errorMessageIncluded") == null) { %>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var error = "${not empty errorMessage ? errorMessage : (not empty message ? message : '')}";
        if (error) {
            alert(error);
        }
    });
</script>
<%
    pageContext.setAttribute("errorMessageIncluded", true);
%>
<% } %>