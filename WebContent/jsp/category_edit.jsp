<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
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
				
					<c:forEach items="${requestScope.catsMap}" var = "c">
						<form id="myForm" method="post" action="${pageContext.request.contextPath}/CategoryServlet?method=editCategory">
					
							<label>rename the category</label>
							<input name="cname" type="text" value="${c.key.cname}"> 
							<input name="cid" type="hidden" value="${c.key.cid}"> 
							<button type="submit" name="myButton">Rename</button> 

							<c:if test="${c.value!=0}">
								<a href="javascript:void(0)">
									<!-- The Category can not be deleted if there is a product references to this category -->									
									<input id="catsDel" type="button" value="Delete" disabled="disabled">
								</a>
							</c:if>
							
							<c:if test="${c.value==0}">
								<!-- The Category can be deleted Only no product references to this category -->
								<a href="${pageContext.request.contextPath}/CategoryServlet?method=delCategory&cid=${c.key.cid}">
									<input id="catsDel" type="button" value="Delete">
								</a>
							</c:if>
						</form>

					</c:forEach>
					
					<br><br><br>
					<form id="myForm" method="post" action="${pageContext.request.contextPath}/CategoryServlet?method=addCategory">
				
						<div>
							<label>Input a new category</label>
							<input type="text" name="cname" placeholder="input a new category name here...">
							<button type="submit" name="myButton">Add Category</button> 
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