<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>${pageTitle != null ? pageTitle : "Quản trị"}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/_header.jsp"/>
<jsp:include page="/WEB-INF/views/layout/_sidebar-admin.jsp"/>

<main class="container">
    <jsp:include page="${contentPage}"/>
</main>

<jsp:include page="/WEB-INF/views/layout/_footer.jsp"/>
</body>
</html>