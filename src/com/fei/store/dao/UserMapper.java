package com.fei.store.dao;

import java.sql.SQLException;

import com.fei.store.domain.User;

public interface UserMapper {
/*	Integer userRegister(User user) throws SQLException;

	User userLogin(User user) throws SQLException;

	public void userModify(User user) throws SQLException;*/
	
	User userLogin(User user) throws SQLException;

	/**
	 * called by Unit Of Work
	 */
	Integer userInsert(User user) throws SQLException;
	
	/**
	 * called by Unit Of Work
	 */
	public void userModify(User user) throws SQLException;
	
	/**
	 * called by Unit Of Work
	 */
	public void userDelete(User user) throws SQLException;

	public void userLoginCheck(User user) throws SQLException;

	public void userInfo(User user);

}
