<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Thanh toán - PolyTube</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="user-layout">

<jsp:include page="/WEB-INF/views/layout/_header.jsp"/>

<main class="main-content">
  <h1>💳 Thanh toán đơn hàng (COD)</h1>

  <c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
  </c:if>

  <div class="checkout-container">

    <div class="checkout-left">
      <h2>📍 Thông tin giao hàng</h2>

      <form method="post" action="${pageContext.request.contextPath}/checkout"
            class="checkout-form">
        <div class="form-group">
          <label>Người nhận</label>
          <input type="text" value="${user.fullname}" readonly/>
        </div>

        <div class="form-group">
          <label>Số điện thoại *</label>
          <input type="text" name="phone" required
                 value="${user.phone}" placeholder="0901234567"/>
        </div>

        <div class="form-group">
          <label>Địa chỉ giao hàng *</label>
          <textarea name="address" rows="3" required
                    placeholder="Số nhà, đường, phường/xã, quận/huyện, tỉnh/thành"></textarea>
        </div>

        <div class="form-group">
          <label>Ghi chú</label>
          <textarea name="note" rows="2"
                    placeholder="Ghi chú cho đơn hàng (tùy chọn)"></textarea>
        </div>

        <div class="form-group">
          <label>Phương thức thanh toán</label>
          <div class="payment-option">
            <input type="radio" id="cod" name="payment" value="COD" checked disabled/>
            <label for="cod">💰 Thanh toán khi nhận hàng (COD)</label>
          </div>
        </div>

        <button type="submit" class="btn-primary">✅ Đặt hàng</button>
      </form>
    </div>

    <div class="checkout-right">
      <h2>🧾 Đơn hàng của bạn</h2>

      <table class="order-summary">
        <thead>
        <tr><th>Sản phẩm</th><th>SL</th><th>Thành tiền</th></tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${cart}">
          <tr>
            <td>${item.title}</td>
            <td>${item.quantity}</td>
            <td><fmt:formatNumber value="${item.subtotal}" type="number"/> đ</td>
          </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
          <td colspan="2" style="text-align:right"><strong>Tổng:</strong></td>
          <td style="color:#ef4444;font-size:18px">
            <strong><fmt:formatNumber value="${total}" type="number"/> đ</strong>
          </td>
        </tr>
        </tfoot>
      </table>
    </div>

  </div>
</main>

<jsp:include page="/WEB-INF/views/layout/_footer.jsp"/>

</body>
</html>