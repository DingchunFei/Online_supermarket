<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Create Product</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
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
				<div style="border: 1px solid #e4e4e4;width:930px;margin-bottom:10px;margin:0 auto;padding:10px;margin-bottom:10px;">
					<a href="${pageContext.request.contextPath}/">All&nbsp;&nbsp;&gt;</a>
					
				</div>

				<div style="margin:0 auto;width:950px;">
					<form id="myForm" method="post" action="${pageContext.request.contextPath}/ProductServlet?method=editProduct">
				
						<div>Product name:
							<input name="pname" type="text" value="${requestScope.product_detail.pname}"> 
						</div>
						
						<div>Price:
							<input name="price" type="text" value="${requestScope.product_detail.price}"> 
						</div>
						
						<div>Category:
							<select name="cid">
							<c:forEach items="${requestScope.categories}" var = "category">
								<option selected="selected"  value="${category.cid}" >${category.cname}</option>
							</c:forEach>
							</select>					
						</div>
						
						<div>Description:
							<input name="pdesc" type="text" value="${requestScope.product_detail.pdesc.description}"> 
						</div>
						
			
						<input name="pimage" type="hidden" value="products/1/c_0025.jpg"> 
						<input name="pid" type="hidden" value="${requestScope.product_detail.pid}"> 
						
						<div>
							<a href="javascript:void(0)">
								<input id="btnEdit" style="background: url('${pageContext.request.contextPath}/img/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0);height:36px;width:127px;" value="edit" type="button">
							</a>
							<a href="${pageContext.request.contextPath}/ProductServlet?method=delProduct&pid=${requestScope.product_detail.pid}">
								<input id="btnDel" style="background: url('${pageContext.request.contextPath}/img/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0);height:36px;width:127px;" value="delete" type="button">
							</a>
						</div>
			
					</form>
					<script>
						$(function(){
							$("#btnEdit").click(function(){
								var formObj=document.getElementById("myForm");
								formObj.submit();
							});
						});
					</script>
				</div>
				<div class="clear"></div>
			</div>
		<%@include file="/jsp/footer.jsp" %>

	</div>
	</body>

</html>