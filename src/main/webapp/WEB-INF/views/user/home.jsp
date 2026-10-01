<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>PolyTube - Trang chủ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="user-layout">

<jsp:include page="/WEB-INF/views/layout/_header.jsp"/>

<main class="main-content">
    <h1>🏠 Trang chủ - Danh sách Video</h1>
    <div class="video-grid">
        <c:forEach var="v" items="${videos}">
            <div class="video-card">
                <a href="${pageContext.request.contextPath}/video/detail?id=${v.videoid}">
                    <img src="${pageContext.request.contextPath}/images/${v.poster}"
                         alt="${v.title}" class="poster"
                         onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/default.png'"/>
                </a>
                <h3>${v.title}</h3>
                <p>Mã video: ${v.videoid}</p>
                <p>View: ${v.views}</p>

                <div class="card-actions">
                    <a href="${pageContext.request.contextPath}/video/detail?id=${v.videoid}"
                       class="btn-detail">Xem</a>

                    <c:if test="${not empty sessionScope.user and sessionScope.user.admin != true}">
                        <form method="post" action="${pageContext.request.contextPath}/cart"
                              style="display:inline">
                            <input type="hidden" name="action" value="add"/>
                            <input type="hidden" name="videoid" value="${v.videoid}"/>
                            <input type="hidden" name="quantity" value="1"/>
                            <button type="submit" class="btn-add-cart">🛒 Thêm</button>
                        </form>
                    </c:if>
                </div>
            </div>
        </c:forEach>
        <c:if test="${empty videos}"><p>Chưa có video nào.</p></c:if>
    </div>
</main>

<jsp:include page="/WEB-INF/views/layout/_footer.jsp"/>

</body>
</html>