package com.fei.store.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fei.store.domain.AdminUser;
import com.fei.store.domain.User;
import com.fei.store.service.UserService;
import com.fei.store.service.serviceImpl.UserServiceImpl;
import com.fei.store.utils.BeanFactory;
import com.fei.store.utils.CookieUtils;
import com.fei.store.utils.MyBeanUtils;
import com.fei.store.utils.UUIDUtils;
import com.fei.store.web.base.BaseServlet;

public class UserServlet extends BaseServlet {
	
	//UserService UserService =(UserService)BeanFactory.createObject("UserService");
	
	//registUI
	public String registerUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
	}

	//registUI
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}



	public String editProfileUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		User user = (User) request.getSession().getAttribute("loginUser");
		UserService userService = new UserServiceImpl();

		//fetch the field of email & gender from DB, if email or gender equals to NULL
		userService.userInfo(user);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>"+user);
		return "/jsp/edit_profile.jsp";
	}

	
	/**
	 * Lazy Initialization. Only when the field of email or gender is required, the query will happen
	 */
	public String editProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		UserService userService = new UserServiceImpl();
		User user;
	
		String uid = request.getParameter("uid");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		if(Integer.parseInt(request.getParameter("type"))==0){
			user = new User(uid,username,password,email,gender);
			user.setType(0);
		}else{
			AdminUser adminUser = new AdminUser();
			adminUser.setUid(uid);
			adminUser.setUsername(username);
			adminUser.setPassword(password);
			adminUser.setGender(gender);
			adminUser.setEmail(email);
			adminUser.setDuty(request.getParameter("duty"));
			adminUser.setType(1);
			user = adminUser;
		}
	
		try{
			userService.userEdit(user);
			request.getSession().setAttribute("loginUser",user);
			response.sendRedirect(request.getContextPath()+"/IndexServlet");
			return null;
		}catch (Exception e) {
			//login fail
			String msg = e.getMessage();
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
	}

	//userRegist
	public String userRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		User user =new User();
		MyBeanUtils.populate(user, request.getParameterMap());
		user.setUid(UUIDUtils.getId());
		UserService userService = new UserServiceImpl();
		
		userService.userRegister(user);
		
		request.getSession().setAttribute("loginUser",user);
		response.sendRedirect(request.getContextPath()+"/IndexServlet");
		return null;
	}

	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

		User user = new User();
		MyBeanUtils.populate(user,request.getParameterMap());
		UserService userService = new UserServiceImpl();

		try{
			userService.userLogin(user);
			
			if(user.getType()==1){			//if Type==1, it means this is an admin user, so we need to fetch extra field
				AdminUser adminUser = new AdminUser();
				adminUser.setType(1);
				adminUser.setUid(user.getUid());
				adminUser.setUsername(user.getUsername());
				userService.adminLogin(adminUser);
				request.getSession().setAttribute("loginUser",adminUser);
			}else{
				request.getSession().setAttribute("loginUser",user);
			}
			response.sendRedirect(request.getContextPath()+"/IndexServlet");
			return null;
		}catch (Exception e) {
			//login fail
			String msg = e.getMessage();
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
	}

	public String userLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
		return null;
	}
	
	public String viewAllUserUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

		UserService userService = new UserServiceImpl();
		List<User> users = userService.viewAllUserUI();
		
		request.setAttribute("users", users);
		return "/jsp/users.jsp";
	}
	
	
	/*//userExists
	public String userExists(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接受用户名
		String um=request.getParameter("username");
		//调用业务层功能,根据用户名查询用户
		
		User user=UserService.findUserByUsreName(um);
		//完成响应
		if(null!=user){
			response.getWriter().print("11");
		}else{
			response.getWriter().print("00");
		}
		return null;
	}

	//active
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//服务端获取到激活码,和数据库中已经存在的激活码对比,如果存在,激活成功,更改用户激活状态1,转发到登录页面,提示:激活成功,请登录.
		String code=request.getParameter("code");
		//调用业务层功能:根据激活码查询用户 select * from user where code=?
		User user01=UserService.userActive(code);
		//如果用户不为空,激活码正确的,可以激活
		if(null!=user01){
			user01.setState(1);
			user01.setCode("");
			UserService.updateUser(user01);
		}
		//转发到登录页面,提示:激活成功,请登录
		request.setAttribute("msg", "用户激活成功,请登录");
		return "/jsp/login.jsp";
		
	}
	//loginUI
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie ck=CookUtils.getCookieByName("remUser", request.getCookies());
		if(null!=ck){
			request.setAttribute("remUser", ck.getValue());
		}
		
		return "/jsp/login.jsp";
	}

	//userLogin
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接受用户名和密码
		User user01=MyBeanUtils.populate(User.class, request.getParameterMap());
		//调用业务层登录功能
		User user02=UserService.userLogin(user01);
		
		if(null!=user02){
			//登录成功,向session中放入用户信息,重定向到首页
			request.getSession().setAttribute("loginUser",user02);
			
			//在登录成功的基础上,判断用户是否选中自动登录复选框
			String autoLogin=request.getParameter("autoLogin");
			if("yes".equals(autoLogin)){
				//用户选中自动登录复选框
				Cookie ck=new Cookie("autoLogin",user02.getUsername()+"#"+user02.getPassword());
				ck.setPath("/store_v4");
				ck.setMaxAge(23423424);
				response.addCookie(ck);
			}
			//remUser
			String remUser=request.getParameter("remUser");
			if("yes".equals(remUser)){
				//用户选中自动登录复选框
				Cookie ck=new Cookie("remUser",user02.getUsername());
				ck.setPath("/store_v4");
				ck.setMaxAge(23423424);
				response.addCookie(ck);
			}
			
			
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else{
			//登录失败,向request放入提示信息,转发到登录页面,显示提示userLogin	
			request.setAttribute("msg", "用户名和密码不匹配!");
			return "/jsp/login.jsp";
		}
		return null;
	}
	//logOut
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//用户退出,清空用户session
		request.getSession().invalidate();
		Cookie ck=CookUtils.getCookieByName("autoLogin", request.getCookies());
		if(null!=ck){
			ck.setMaxAge(0);
			ck.setPath("/store_v4");
			response.addCookie(ck);
		}
		
		response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
		return null;
	}
	*/
}