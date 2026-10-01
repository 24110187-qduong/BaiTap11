<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Giỏ hàng - PolyTube</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="user-layout">

<jsp:include page="/WEB-INF/views/layout/_header.jsp"/>

<main class="main-content">
  <h1>🛒 Giỏ hàng của bạn</h1>

  <c:if test="${empty cart}">
    <div class="empty-cart">
      <p>Giỏ hàng đang trống.</p>
      <a href="${pageContext.request.contextPath}/home" class="btn-checkout"
         style="display:inline-block;text-decoration:none">← Tiếp tục mua sắm</a>
    </div>
  </c:if>

  <c:if test="${not empty cart}">
    <table class="cart-table">
      <thead>
      <tr>
        <th>Poster</th>
        <th>Tiêu đề</th>
        <th>Giá</th>
        <th>Số lượng (tối đa ${maxQuantity})</th>
        <th>Thành tiền</th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="item" items="${cart}">
        <tr>
          <td>
            <img src="${pageContext.request.contextPath}/images/${item.poster}"
                 class="cart-poster"
                 onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/default.png'"/>
          </td>
          <td>
            <strong>${item.title}</strong><br/>
            <small>Mã: ${item.videoid}</small>
          </td>
          <td><fmt:formatNumber value="${item.price}" type="number"/> đ</td>
          <td>
            <form method="post" action="${pageContext.request.contextPath}/cart"
                  class="qty-form">
              <input type="hidden" name="action" value="update"/>
              <input type="hidden" name="videoid" value="${item.videoid}"/>

              <button type="submit" name="quantity" value="${item.quantity - 1}"
                      class="qty-btn"
                ${item.quantity <= 1 ? 'disabled' : ''}>−</button>

              <input type="number" name="quantity" value="${item.quantity}"
                     min="1" max="${maxQuantity}" class="qty-input"
                     onchange="this.form.submit()"/>

              <button type="submit" name="quantity" value="${item.quantity + 1}"
                      class="qty-btn"
                ${item.quantity >= maxQuantity ? 'disabled' : ''}>+</button>
            </form>
          </td>
          <td><strong><fmt:formatNumber value="${item.subtotal}" type="number"/> đ</strong></td>
          <td>
            <a href="${pageContext.request.contextPath}/cart?action=remove&id=${item.videoid}"
               class="btn-delete"
               onclick="return confirm('Xóa sản phẩm này khỏi giỏ?')">🗑 Xóa</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
      <tfoot>
      <tr>
        <td colspan="4" style="text-align:right;font-size:18px">
          <strong>TỔNG CỘNG:</strong>
        </td>
        <td colspan="2" style="font-size:20px;color:#ef4444">
          <strong><fmt:formatNumber value="${total}" type="number"/> đ</strong>
        </td>
      </tr>
      </tfoot>
    </table>

    <div class="cart-actions">
      <a href="${pageContext.request.contextPath}/cart?action=clear"
         class="btn-clear" onclick="return confirm('Xóa toàn bộ giỏ hàng?')">
        🗑 Xóa toàn bộ
      </a>
      <a href="${pageContext.request.contextPath}/checkout" class="btn-checkout">
        💳 Thanh toán COD →
      </a>
    </div>
  </c:if>
</main>

<jsp:include page="/WEB-INF/views/layout/_footer.jsp"/>

</body>
</html>