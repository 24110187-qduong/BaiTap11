<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Đơn hàng của tôi - PolyTube</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="user-layout">

<jsp:include page="/WEB-INF/views/layout/_header.jsp"/>

<main class="main-content">
  <h1>📦 Đơn hàng của tôi</h1>

  <c:if test="${not empty sessionScope.orderSuccess}">
    <div class="alert alert-success">${sessionScope.orderSuccess}</div>
    <c:remove var="orderSuccess" scope="session"/>
  </c:if>

  <c:if test="${empty orders}">
    <p style="margin:20px 0">Bạn chưa có đơn hàng nào.</p>
    <a href="${pageContext.request.contextPath}/home" class="btn-checkout"
       style="display:inline-block;text-decoration:none">← Mua sắm ngay</a>
  </c:if>

  <c:forEach var="o" items="${orders}">
    <div class="order-card">
      <div class="order-header">
        <div>
          <strong>Đơn #${o.orderId}</strong>
          <span class="order-date">${o.orderDate}</span>
        </div>
        <div>
          <span class="order-status">${o.status}</span>
          <span class="order-payment">${o.paymentMethod}</span>
        </div>
      </div>
      <div class="order-body">
        <table class="order-items">
          <c:forEach var="item" items="${o.items}">
            <tr>
              <td>
                <img src="${pageContext.request.contextPath}/images/${item.poster}"
                     class="order-thumb"
                     onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/images/default.png'"/>
              </td>
              <td>${item.title}</td>
              <td>x${item.quantity}</td>
              <td><fmt:formatNumber value="${item.price}" type="number"/> đ</td>
            </tr>
          </c:forEach>
        </table>
      </div>
      <div class="order-footer">
        <p><strong>Địa chỉ:</strong> ${o.address}</p>
        <p><strong>SĐT:</strong> ${o.phone}</p>
        <p class="order-total">
          Tổng tiền:
          <strong><fmt:formatNumber value="${o.totalAmount}" type="number"/> đ</strong>
        </p>
      </div>
    </div>
  </c:forEach>
</main>

<jsp:include page="/WEB-INF/views/layout/_footer.jsp"/>

</body>
</html>