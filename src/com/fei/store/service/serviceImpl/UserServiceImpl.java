package com.fei.store.service.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.fei.store.dao.UserMapper;
import com.fei.store.dao.daoImpl.UserMapperImpl;
import com.fei.store.domain.AdminUser;
import com.fei.store.domain.User;
import com.fei.store.service.UserService;

public class UserServiceImpl implements UserService {
	
	@Override
	public void userRegister(User user) throws SQLException {
		unitOfwork.registerNew(user);
		unitOfwork.commit();
	}
	
	@Override
	public void userEdit(User user) throws SQLException {
		unitOfwork.registerModified(user);
		unitOfwork.commit();
	}

	@Override
	public void userLogin(User user) throws SQLException {
		UserMapper userMapper = new UserMapperImpl();
		userMapper.userLoginCheck(user);
		if(user.getUid()==null){
			throw new RuntimeException("Wrong Username or password!");
		}
	}

	@Override
	public void userInfo(User user) throws SQLException {
		System.out.println(user);
		if(user.getEmail()==null || user.getGender()==null){	// if one of it is NULL, then we need to fetch detail from DB
			UserMapper userMapper = new UserMapperImpl();		
			userMapper.userInfo(user);
		}
	}

	@Override
	public void adminLogin(AdminUser adminUser) {
		UserMapper userMapper = new UserMapperImpl();
		userMapper.adminLogin(adminUser);
	}

	@Override
	public List<User> viewAllUserUI() {
		UserMapper userMapper = new UserMapperImpl();
		return userMapper.viewAllUserUI();
	}
}
