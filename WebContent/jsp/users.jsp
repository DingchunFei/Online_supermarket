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
			
			table, th, td {
			  border: 3px solid black;
			  border-collapse: collapse;
			}
			th, td{
				padding:0px 17px;
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
				
					<table>
						 <tr>
						    <th>ID</th>
						    <th>Username</th> 
						    <th>Gender</th>
						    <th>Email</th>
						    <th>Type</th>
						  </tr>
						  <c:forEach items="${requestScope.users}" var = "user">
						  	<tr>
							    <td>${user.uid}</td>
							    <td>${user.username}</td> 
							    <td>${user.gender}</td>
							    <td>${user.email}</td>
							    <c:if test="${user.type==0}" >
							    	<td>Ordinary User</td>
							    </c:if>
							    <c:if test="${user.type==1}" >
							    	<td>Administrator</td>
							    </c:if>
						    </tr>
						  </c:forEach>
					</table>
				
				</div>
				<div class="clear"></div>
			</div>
		<%@include file="/jsp/footer.jsp" %>

	</div>
	</body>

</html>