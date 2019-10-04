<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!doctype html>
<html>
<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Login</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #666;
    font-size: 22px;
    font-weight: normal;
    padding-right:17px;
}
 </style>
</head>
<body>


<%@ include file="/jsp/header.jsp" %>







<div class="container" >
<div class="row"> 
	<div class="col-md-7">
		<!--<img src="image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
	</div>
	
	<div class="col-md-5">
        <div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
            <font>USER LOGIN</font>
            <div>${msg}&nbsp;</div>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/UserServlet?method=userLogin" method="post">

            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">Username</label>
                <div class="col-sm-6">
                  <input type="text" name="username" class="form-control" id="username" placeholder="Username">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-6">
                    <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="Password">
                </div>
            </div>
              <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                <input type="submit"  width="100" value="Login" name="submit" border="0"
                style="background: url('${pageContext.request.contextPath}/img/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                height:35px;width:100px;color:white;">
                </div>
              </div>
            </form>
        </div>
	</div>
</div>
</div>

<%@include file="/jsp/footer.jsp" %>

</body></html>