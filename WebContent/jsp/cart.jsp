<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>购物车</title>
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
			
			.container .row div {
				/* position:relative;
	 float:left; */
			}
			
			font {
				color: #3164af;
				font-size: 18px;
				font-weight: normal;
				padding: 0 10px;
			}
		</style>
	</head>

	<body>


	<%@ include file="/jsp/header.jsp" %>

		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong style="font-size:16px;margin:5px 0;">Cart Detail</strong>
					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th>Image</th>
								<th>Product</th>
								<th>Price</th>
								<th>Quantity</th>
								<th>Subtotal</th>
								<th>Operation</th>
							</tr>
							<c:forEach items="${cart.cartItems}" var = "item">
							<tr class="active">
									<td width="60" width="40%">
										<input type="hidden" name="id" value="22">
										<a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${item.product.pid}">
											<img src="${pageContext.request.contextPath}/${item.product.pimage}" width="70" height="60">
										</a>
									</td>
									<td width="30%">
										<a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${item.product.pid}">${item.product.pname}</a>
									</td>
									<td width="20%">
										$${item.product.price}
									</td>
									<td width="10%">
										<input type="text" name="quantity" value="${item.num}" maxlength="4" size="10">
									</td>
									<td width="15%">
										<span class="subtotal">$${item.subTotal}</span>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/CartServlet?method=removeCartItem&pid=${item.product.pid}" class="delete">Delete</a>
									</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div style="margin-right:130px;">
				<div style="text-align:right;">
					 Total: <strong style="color:#ff6600;">$${cart.total}</strong>
				</div>
				<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
					<a href="${pageContext.request.contextPath}/CartServlet?method=clearCart" id="clear" class="clear">Clear Cart</a>
					<form action="${pageContext.request.contextPath}/OrderServlet?method=saveOrder" method="post">
						Address: <input type="text" name="address" />
						<br>
						<%--提交表单 --%>
							<input type="submit" width="100" value="Submit Order" name="submit" border="0" style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						height:35px;width:100px;color:white;">
					</form>
				</div>
			</div>

		</div>

	<%@include file="/jsp/footer.jsp" %>
	</body>

</html>