<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Xác thực OTP - PolyTube</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="blank-layout">

<main class="blank-content">
    <div class="auth-box">
        <h2>📧 Xác thực OTP</h2>
        <p class="hint">Mã OTP đã gửi đến: <strong>${sessionScope.otp_email}</strong></p>
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/verify-otp">
            <div class="form-group">
                <label>Mã OTP (6 chữ số)</label>
                <input type="text" name="otp" maxlength="6" required pattern="[0-9]{6}"
                       style="font-size:24px;letter-spacing:8px;text-align:center"
                       placeholder="______"/>
            </div>
            <button type="submit" class="btn-primary">Xác thực</button>
        </form>
        <p class="auth-link">
            Không nhận được mã?
            <a href="${pageContext.request.contextPath}/register">Đăng ký lại</a>
        </p>
    </div>
</main>

<jsp:include page="/WEB-INF/views/layout/_footer.jsp"/>

</body>
</html>