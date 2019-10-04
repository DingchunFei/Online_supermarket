<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>

	<%@ include file="/jsp/header.jsp" %>


	<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>My Orders</strong>
					<table class="table table-bordered">

						<c:forEach items="${requestScope.orders}" var = "order">
							<tbody>
								<tr class="success">
									<th colspan="5">Order NO: ${order.oid} <a href="${ pageContext.request.contextPath }/OrderServlet?method=deleteOrderOrderItem&oid=${order.oid}">Delete Order</a></th>
								</tr>
								<tr class="warning">
									<th>Image</th>
									<th>Product</th>
									<th>Price</th>
									<th>Quantity</th>
									<th>Subtotal</th>
								</tr>

								<c:forEach items="${order.list}" var = "orderItem">

									<tr class="active">
										<td width="60" width="40%">
											<input type="hidden" name="id" value="22">
											<img src="${pageContext.request.contextPath}/${orderItem.product.pimage}" width="70" height="60">
										</td>
										<td width="30%">
											<a target="_blank">${orderItem.product.pname}</a>
										</td>
										<td width="20%">
											$${orderItem.product.price}
										</td>
										<td width="10%">
											${orderItem.quantity}
										</td>
										<td width="15%">
											<span class="subtotal">${orderItem.total}</span>
										</td>
									</tr>
								</c:forEach>
									<tr class="active">
										<td width="60" width="40%">
											<em>Total Price</em>
										</td>
										<td colspan="5">
											<span class="total"><em>${order.total}</em></span>
										</td>
									</tr>
									<tr class="active">
										<td width="60" width="40%">
											<em>Address</em>
										</td>
										<td colspan="5">
											<span class="total"><em>${order.address}</em></span>
										</td>
									</tr>
							</tbody>
						</c:forEach>

					</table>
				</div>
			</div>

		<%@include file="/jsp/footer.jsp" %>

	</div>
	</body>

</html>