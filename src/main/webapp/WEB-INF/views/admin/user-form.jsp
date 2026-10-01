<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>${mode == 'edit' ? 'Sửa User' : 'Thêm User'} - Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="admin-layout">

<jsp:include page="/WEB-INF/views/layout/_header.jsp"/>

<div class="admin-container">
    <jsp:include page="/WEB-INF/views/layout/_sidebar-admin.jsp"/>
    <main class="admin-content">
        <h1>${mode == 'edit' ? '✏ Sửa User' : '➕ Thêm User'}</h1>

        <form method="post" action="${pageContext.request.contextPath}/admin/users" class="admin-form">
            <input type="hidden" name="action" value="${mode == 'edit' ? 'edit' : 'create'}"/>

            <div class="form-group">
                <label>Username *</label>
                <input type="text" name="username" required value="${user.username}"
                ${mode == 'edit' ? 'readonly' : ''}/>
            </div>
            <div class="form-group">
                <label>Password ${mode == 'edit' ? '(để trống nếu không đổi)' : '*'}</label>
                <input type="password" name="password" ${mode == 'create' ? 'required' : ''}/>
            </div>
            <div class="form-group">
                <label>Fullname</label>
                <input type="text" name="fullname" value="${user.fullname}"/>
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" value="${user.email}"/>
            </div>
            <div class="form-group">
                <label>Phone</label>
                <input type="text" name="phone" value="${user.phone}"/>
            </div>
            <div class="form-group-inline">
                <label><input type="checkbox" name="admin" ${user.admin ? 'checked' : ''}/> Admin</label>
            </div>
            <div class="form-group-inline">
                <label><input type="checkbox" name="active" ${user.active ? 'checked' : ''}/> Active</label>
            </div>

            <button type="submit" class="btn-primary">💾 Lưu</button>
            <a href="${pageContext.request.contextPath}/admin/users"
               style="margin-left:10px;color:#64748b;text-decoration:none">Hủy</a>
        </form>
    </main>
</div>

<jsp:include page="/WEB-INF/views/layout/_footer.jsp"/>

</body>
</html>