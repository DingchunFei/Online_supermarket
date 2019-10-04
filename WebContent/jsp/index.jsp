<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Online Supermarket</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
	</head>

	<body>
		<div class="container-fluid">

			<%@ include file="/jsp/header.jsp" %>

			<!--

            	描述：商品显示
            -->
			<div class="container-fluid">
				<div class="col-md-12">
					<div class="col-md-12">
						<a href="${pageContext.request.contextPath}/IndexServlet?order=aesc">AESC</a>
						<a href="${pageContext.request.contextPath}/IndexServlet?order=desc">DESC</a>
					</div>
					<c:forEach items="${requestScope.allProducts}" var = "c">
						<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
							<a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${c.pid}">
								<img src="${c.pimage}" width="130" height="130" style="display: inline-block;">
							</a>
							<p><a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${c.pid}" style='color:#666'>${c.pname}</a></p>
							<p><font color="#E4393C" style="font-size:16px">$${c.price}</font></p>
						</div>
					</c:forEach>

				</div>
			</div>			
			<!--
            	描述：页脚部分
            -->
			<%@include file="/jsp/footer.jsp" %>
		</div>
	</body>

</html>