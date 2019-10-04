package com.fei.store.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.fei.store.dao.UserMapper;
import com.fei.store.dao.daoImpl.UserMapperImpl;
import com.fei.store.domain.AdminUser;
import com.fei.store.domain.User;
import com.fei.store.orbd.UnitOfWork;

public interface UserService {
	
	/**
	 * Instantiate the field of UnitOfWork
	 */
	HashMap<String, List<User>> map = new HashMap<>();
	
    UserMapper userMapper = new UserMapperImpl();
    
    UnitOfWork unitOfwork = new UnitOfWork(map, userMapper);


    public void userRegister(User user) throws SQLException;
    
    public void userInfo(User user) throws SQLException;

	void userLogin(User user) throws SQLException;

	public void userEdit(User user) throws SQLException;

	public void adminLogin(AdminUser adminUser);

	public List<User> viewAllUserUI();
	

}
