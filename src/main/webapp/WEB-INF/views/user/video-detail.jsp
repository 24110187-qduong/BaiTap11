<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>${video.title} - PolyTube</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="user-layout">

<jsp:include page="/WEB-INF/views/layout/_header.jsp"/>

<main class="main-content">
    <h1>🎬 Chi tiết Video</h1>

    <div class="video-detail">
        <div class="video-detail-left">
            <img src="${pageContext.request.contextPath}/images/${video.poster}"
                 alt="${video.title}" class="detail-poster"
                 onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/default.png'"/>
        </div>
        <div class="video-detail-right">
            <p><strong>Tiêu đề:</strong> ${video.title}</p>
            <p><strong>Mã video:</strong> ${video.videoid}</p>
            <p><strong>Category name:</strong> ${video.categoryName}</p>
            <p><strong>View:</strong> ${video.views}</p>
            <p>
                <strong>Share</strong> <span class="badge">${video.shareCount}</span>
                &nbsp;|&nbsp;
                <strong>Like</strong> <span class="badge">${video.likeCount}</span>
            </p>
        </div>
    </div>

    <div class="video-description">
        <h3>Description</h3>
        <p>${video.description}</p>
    </div>

    <p style="margin-top:20px">
        <a href="${pageContext.request.contextPath}/home" class="btn-back">← Về trang chủ</a>
    </p>
</main>

<jsp:include page="/WEB-INF/views/layout/_footer.jsp"/>

</body>
</html>