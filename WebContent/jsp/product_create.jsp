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
					<form id="myForm" method="post" action="${pageContext.request.contextPath}/ProductServlet?method=insertProduct">
				
						<div>Product name:
							<input name="pname" type="text"> 
						</div>
						
						<div>Price:
							<input name="price" type="text"> 
						</div>
						
						<div>Category:
							<select name="cid">
							<c:forEach items="${requestScope.categories}" var = "category">
								<option value="${category.cid}">${category.cname}</option>
							</c:forEach>
							</select>					
						</div>
						
						
						<div>Description:
							<input name="pdesc" type="text"> 
						</div>
								
						<input name="pimage" type="hidden" value="products/1/c_0025.jpg"> 
						
						<div>
							<a href="javascript:void(0)">
								<input id="btnId" value="Submit" type="button">
							</a>
						</div>
			
					</form>
					<script>
						$(function(){
							$("#btnId").click(function(){
								var formObj=document.getElementById("myForm");
								//formObj.action="/store_v5/CartServlet";
								//formObj.method="get";
								formObj.submit();
							});
						});
					</script>
				</div>
				<div class="clear"></div>
				<div style="width:950px;margin:0 auto;">

					<div style="background-color:#d3d3d3;width:930px;padding:10px 10px;margin:10px 0 10px 0;">
						<strong>Description</strong>
					</div>
					<div style="margin-top:10px;width:900px;">
						${requestScope.product_detail.pdesc.description}
					</div>

				</div>

			</div>
		<%@include file="/jsp/footer.jsp" %>

	</div>
	</body>

</html>