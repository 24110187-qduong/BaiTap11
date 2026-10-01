<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý Users - Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="admin-layout">

<jsp:include page="/WEB-INF/views/layout/_header.jsp"/>

<div class="admin-container">
    <jsp:include page="/WEB-INF/views/layout/_sidebar-admin.jsp"/>
    <main class="admin-content">
        <h1>👥 Quản lý Users (${totalUsers} users)</h1>

        <p style="margin:15px 0">
            <a href="${pageContext.request.contextPath}/admin/users?action=new" class="btn-primary"
               style="display:inline-block;width:auto;padding:10px 20px;text-decoration:none">
                ➕ Thêm User
            </a>
        </p>

        <table class="admin-table">
            <thead>
            <tr>
                <th>Username</th><th>Fullname</th><th>Email</th><th>Phone</th>
                <th>Admin</th><th>Active</th><th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="u" items="${users}">
                <tr>
                    <td>${u.username}</td>
                    <td>${u.fullname}</td>
                    <td>${u.email}</td>
                    <td>${u.phone}</td>
                    <td>${u.admin ? '✅' : '❌'}</td>
                    <td>${u.active ? '✅' : '❌'}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/users?action=edit&username=${u.username}"
                           class="btn-edit">✏ Sửa</a>
                        <a href="${pageContext.request.contextPath}/admin/users?action=delete&username=${u.username}"
                           class="btn-delete"
                           onclick="return confirm('Xóa user ${u.username}?')">🗑 Xóa</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <c:if test="${totalPages > 1}">
            <div class="pagination">
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <a href="${pageContext.request.contextPath}/admin/users?page=${i}"
                       class="${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>
            </div>
        </c:if>
    </main>
</div>

<jsp:include page="/WEB-INF/views/layout/_footer.jsp"/>

</body>
</html>