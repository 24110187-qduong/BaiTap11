<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký - PolyTube</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="blank-layout">

<main class="blank-content">
    <div class="auth-box">
        <h2>📝 Đăng ký tài khoản</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/register">
            <div class="form-group">
                <label>Tên đăng nhập *</label>
                <input type="text" name="username" required value="${param.username}"/>
            </div>
            <div class="form-group">
                <label>Mật khẩu *</label>
                <input type="password" name="password" required/>
            </div>
            <div class="form-group">
                <label>Xác nhận mật khẩu *</label>
                <input type="password" name="confirm" required/>
            </div>
            <div class="form-group">
                <label>Họ và tên</label>
                <input type="text" name="fullname" value="${param.fullname}"/>
            </div>
            <div class="form-group">
                <label>Email *</label>
                <input type="email" name="email" required value="${param.email}"/>
            </div>
            <div class="form-group">
                <label>Số điện thoại</label>
                <input type="text" name="phone" value="${param.phone}"/>
            </div>
            <button type="submit" class="btn-primary">Đăng ký</button>
        </form>
        <p class="auth-link">
            Đã có tài khoản?
            <a href="${pageContext.request.contextPath}/login">Đăng nhập ngay</a>
        </p>
    </div>
</main>

<jsp:include page="/WEB-INF/views/layout/_footer.jsp"/>

</body>
</html>