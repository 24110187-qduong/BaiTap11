<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<header class="main-header">
    <div class="logo">
        <a href="${pageContext.request.contextPath}/home">🎬 <strong>PolyTube</strong></a>
    </div>
    <nav class="main-nav">
        <a href="${pageContext.request.contextPath}/home">Trang Chủ</a>
        <a href="${pageContext.request.contextPath}/category">Sản phẩm</a>

        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <span class="welcome">Xin chào, <strong>${sessionScope.user.fullname}</strong></span>

                <%-- CHỈ USER THƯỜNG mới thấy Giỏ hàng + Đơn hàng --%>
                <c:if test="${sessionScope.user.admin != true}">
                    <a href="${pageContext.request.contextPath}/cart" class="btn-cart">
                        🛒 Giỏ hàng
                        <c:set var="cartCount" value="0"/>
                        <c:if test="${not empty sessionScope.cart}">
                            <c:forEach var="it" items="${sessionScope.cart}">
                                <c:set var="cartCount" value="${cartCount + it.quantity}"/>
                            </c:forEach>
                        </c:if>
                        <c:if test="${cartCount > 0}">
                            <span class="cart-badge">${cartCount}</span>
                        </c:if>
                    </a>
                    <a href="${pageContext.request.contextPath}/orders" class="btn-cart"
                       style="background:#0ea5e9">📦 Đơn hàng</a>
                </c:if>

                <%-- CHỈ ADMIN mới thấy Trang quản trị --%>
                <c:if test="${sessionScope.user.admin == true}">
                    <a href="${pageContext.request.contextPath}/admin/home" class="btn-admin">⚙ Trang quản trị</a>
                </c:if>

                <a href="${pageContext.request.contextPath}/logout" class="btn-logout">Đăng xuất</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/login" class="btn-login">Đăng nhập</a>
            </c:otherwise>
        </c:choose>
    </nav>
</header>