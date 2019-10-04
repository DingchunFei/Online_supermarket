<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Online Store</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
</head>

	<body>
	<div class="container-fluid">

			<!--
            	描述：菜单栏
            -->
			<div class="container-fluid">
<%--				<div class="col-md-4">--%>
<%--					<img src="${pageContext.request.contextPath}/img/logo2.png" />--%>
<%--				</div>--%>
<%--				<div class="col-md-5">--%>
<%--					<img src="${pageContext.request.contextPath}/img/header.png" />--%>
<%--				</div>--%>
				<div class="col-md-6" style="padding-top:20px">
					<ol class="list-inline">
						<c:if test="${empty loginUser}">
							<li><a href="${pageContext.request.contextPath}/UserServlet?method=loginUI">Sign in</a></li>
							<li><a href="${pageContext.request.contextPath}/UserServlet?method=registerUI">Sign up</a></li>
						</c:if>

						<c:if test="${not empty loginUser}">
							<li>Welcome ${loginUser.username}</li>
							<li><a href="${pageContext.request.contextPath}/UserServlet?method=userLogout">Logout</a></li>
							<li><a href="${pageContext.request.contextPath}/UserServlet?method=editProfileUI">Edit Profile</a></li>
							<li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">Cart</a></li>
							<li><a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrders">Orders</a></li>
						</c:if>
					</ol>
				</div>
			</div>
		<!--
描述：导航条
-->
		<div class="container-fluid">
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="${pageContext.request.contextPath}/IndexServlet">All</a>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav" id="myUL">

						</ul>
						<form class="navbar-form navbar-right" role="search" action="${pageContext.request.contextPath}/IndexServlet">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Search" name="pname">
							</div>
							<button type="submit" class="btn btn-default">Submit</button>
						</form>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>
	</div>
	</body>
</html>

<script>
	$(function(){
		//向服务端CategoryServlet__>gteAllCats发起ajax请求,服务端经过处理,
		//将所有分类信息以JSON格式的数据返回,获取到返回的所有分类绑定在页面的显示分类区域
		var url="${pageContext.request.contextPath}/CategoryServlet";
		var obj={"method":"findAllCats"};
		$.post(url,obj,function(data){
			//alert(data);

			//获取到服务端响应会的数据,经过观察data中存放的是一个JSON格式数组,遍历数组,动态的显示分类区域代码
			$.each(data,function(i,obj){
				var li="<li><a href='${pageContext.request.contextPath}/ProductServlet?method=findProductsByCid&cid="+obj.cid+"'>"+obj.cname+"</a></li>";
				$("#myUL").append(li);
			});

		},"json");


	});
</script>