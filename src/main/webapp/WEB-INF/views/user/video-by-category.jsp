<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Video theo Category - PolyTube</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="user-layout">

<jsp:include page="/WEB-INF/views/layout/_header.jsp"/>

<main class="main-content">
    <h1>📂 Video theo Category</h1>

    <div class="category-filter">
        <c:forEach var="cat" items="${categories}">
            <a href="${pageContext.request.contextPath}/category?id=${cat.categoryId}"
               class="cat-btn ${currentCategory != null && currentCategory.categoryId == cat.categoryId ? 'active' : ''}">
                    ${cat.categoryname} (${cat.videoCount})
            </a>
        </c:forEach>
    </div>

    <c:if test="${not empty currentCategory}">
        <h2>Category: ${currentCategory.categoryname} <small>(${totalVideos} video)</small></h2>

        <div class="video-grid">
            <c:forEach var="v" items="${videos}">
                <div class="video-card">
                    <a href="${pageContext.request.contextPath}/video/detail?id=${v.videoid}">
                        <img src="${pageContext.request.contextPath}/images/${v.poster}"
                             alt="${v.title}" class="poster"
                             onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/default.png'"/>
                    </a>
                    <h3>${v.title}</h3>
                    <p><strong>Mã video:</strong> ${v.videoid}</p>
                    <p><strong>Category:</strong> ${v.categoryName}</p>
                    <p><strong>View:</strong> ${v.views}</p>
                    <p>
                        <strong>Share</strong> (${v.shareCount})
                        &nbsp;|&nbsp;
                        <strong>Like</strong> (${v.likeCount})
                    </p>

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

        <c:if test="${totalPages > 1}">
            <div class="pagination">
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <a href="${pageContext.request.contextPath}/category?id=${currentCategory.categoryId}&page=${i}"
                       class="${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>
            </div>
        </c:if>
    </c:if>

    <c:if test="${empty currentCategory}">
        <p style="margin-top:20px;color:#64748b">👆 Chọn một category để xem video.</p>
    </c:if>
</main>

<jsp:include page="/WEB-INF/views/layout/_footer.jsp"/>

</body>
</html>