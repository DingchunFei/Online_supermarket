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
				
				
			<form class="form-horizontal" style="margin-top:5px;" action="${pageContext.request.contextPath}/UserServlet?method=editProfile" method="post">
			 <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">Username</label>
			    <div class="col-sm-6">
			      <input type="text" name="username" class="form-control" id="username" value="${sessionScope.loginUser.username}">
			    </div>
			  </div>
			   <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
			    <div class="col-sm-6">
			      <input type="password" name="password" class="form-control" id="inputPassword3" value="${sessionScope.loginUser.password}">
			    </div>
			  </div>
<!-- 			   <div class="form-group">
			    <label for="confirmpwd" class="col-sm-2 control-label">re-password</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" id="confirmpwd" placeholder="Please confirm your password">
			    </div> -->
			  <div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
			    <div class="col-sm-6">
			      <input type="email" name="email" class="form-control" id="inputEmail3" value="${sessionScope.loginUser.email}">
					<input type="hidden"  name="uid" class="form-control"  value="${sessionScope.loginUser.uid}">
					<input type="hidden" name="type" value="${sessionScope.loginUser.type}"> 
				</div>
			  </div>

			  <div class="form-group opt">  
				  <label for="inlineRadio1" class="col-sm-2 control-label">Gender</label>
				  <div class="col-sm-6">

					<c:if test ="${sessionScope.loginUser.gender=='male'}">
						<label class="radio-inline">
							<input type="radio" name="gender" id="inlineRadio1" value="male" checked="checked"> Male
						</label>
						<label class="radio-inline">
							 <input type="radio" name="gender" id="inlineRadio2" value="female"> Female
						</label>
					</c:if>

					<c:if test = "${sessionScope.loginUser.gender=='female'}">
						<label class="radio-inline">
							<input type="radio" name="gender" id="inlineRadio1" value="male"> Male
						</label>
						<label class="radio-inline">
							 <input type="radio" name="gender" id="inlineRadio2" value="female" checked="checked"> Female
						</label>
					</c:if>
					</div>
			  </div>		
			  
			  <div class="form-group opt">  
				  <label class="col-sm-2 control-label">Duty</label>
				  <div class="col-sm-6">
					<c:if test = "${sessionScope.loginUser.type==1}">
					    <div class="col-sm-6">
					    	<input name="duty" class="form-control" id="duty" value="${sessionScope.loginUser.duty}">
						</div>
					</c:if>
					</div>
			  </div>	
			  
			 
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <input type="submit"  width="100" value="Submit" name="submit" border="0"
				    style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;">
			    </div>
			  </div>
			</form>

				</div>
			</div>			
			<!--
            	描述：页脚部分
            -->
			<%@include file="/jsp/footer.jsp" %>
		</div>
	</body>

</html>