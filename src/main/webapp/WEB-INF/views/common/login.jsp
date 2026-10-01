<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập - PolyTube</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="blank-layout">

<main class="blank-content">
    <div class="auth-box">
        <h2>🔐 Đăng nhập</h2>

        <c:if test="${not empty sessionScope.success}">
            <div class="alert alert-success">${sessionScope.success}</div>
            <c:remove var="success" scope="session"/>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/login">
            <div class="form-group">
                <label>Tên đăng nhập</label>
                <input type="text" name="username" required
                       value="${cookie.remember_user.value != null ? cookie.remember_user.value : param.username}"/>
            </div>
            <div class="form-group">
                <label>Mật khẩu</label>
                <input type="password" name="password" required/>
            </div>
            <div class="form-group-inline">
                <label>
                    <input type="checkbox" name="remember"
                    ${cookie.remember_user.value != null ? 'checked' : ''}/>
                    Ghi nhớ đăng nhập
                </label>
            </div>
            <button type="submit" class="btn-primary">Đăng nhập</button>
        </form>
        <p class="auth-link">
            Chưa có tài khoản?
            <a href="${pageContext.request.contextPath}/register">Đăng ký ngay</a>
        </p>
    </div>
</main>

<jsp:include page="/WEB-INF/views/layout/_footer.jsp"/>

</body>
</html>