<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Admin - PolyTube</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="admin-layout">

<jsp:include page="/WEB-INF/views/layout/_header.jsp"/>

<div class="admin-container">
    <jsp:include page="/WEB-INF/views/layout/_sidebar-admin.jsp"/>
    <main class="admin-content">
        <h1>⚙ Trang quản trị</h1>
        <p>Chào mừng <strong>${sessionScope.user.fullname}</strong>!</p>

        <div class="admin-stats">
            <div class="stat-card">
                <h3>👥 Quản lý Users</h3>
                <a href="${pageContext.request.contextPath}/admin/users">Truy cập →</a>
            </div>
            <div class="stat-card">
                <h3>📂 Quản lý Categories</h3>
                <a href="${pageContext.request.contextPath}/category">Truy cập →</a>
            </div>
        </div>
    </main>
</div>

<jsp:include page="/WEB-INF/views/layout/_footer.jsp"/>

</body>
</html>