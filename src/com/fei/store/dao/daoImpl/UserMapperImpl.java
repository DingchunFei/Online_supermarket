package com.fei.store.dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fei.store.dao.UserMapper;
import com.fei.store.domain.User;
import com.fei.store.utils.DBConnection;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;


public class UserMapperImpl implements UserMapper {


	@Override
	public void userModify(User user) throws SQLException {
		try {
			//PreparedStatement stmt = DBConnection.prepare("UPDATE t_user SET username=? ,password = ?,email=?,gender=? WHERE uid=?");

			//PreparedStatement stmt = DBConnection.prepare("INSERT INTO t_user values(?,?,?,?,?)");
			PreparedStatement stmt = DBConnection.prepare("UPDATE t_user SET username=? ,password = ?,email=?,gender=? WHERE uid=?");

			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getGender());

			stmt.setString(5, user.getUid());

			stmt.executeUpdate();

			DBConnection.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Integer userInsert(User user) throws SQLException {
		try {
			PreparedStatement stmt = DBConnection.prepare("INSERT into t_user (uid,username,password,email,gender) values(?,?,?,?,?)");
			stmt.setString(1, user.getUid());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getGender());

			int resultSet=stmt.executeUpdate();
			DBConnection.commit();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;	//Insert fail
	}

	@Override
	public void userDelete(User user) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public User userLogin(User user) throws SQLException {
		User user2 = null;
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * from t_user where username=? and password=?");

			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				user2 = new User();
				user2.setUid(rs.getString(1));
				user2.setUsername(rs.getString(2));
				user2.setEmail(rs.getString(4));
				user2.setGender(rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user2;
	}

	@Override
	public void userLoginCheck(User user) throws SQLException {
		
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT uid from t_user where username=? and password=?");

			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());

			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				user.setUid(rs.getString(1));			//If UID!=null, login success; else fail;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Lazy Initialization. Only when the field of email or gender is required, the query will happen
	 */
	@Override
	public void userInfo(User user) {
		
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT email,gender from t_user where uid=?");

			stmt.setString(1, user.getUid());

			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				user.setEmail(rs.getString(1));			//Lazy Initialization, 
				System.out.println(rs.getString(1));	
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");	

				user.setGender(rs.getString(2));
				System.out.println(rs.getString(2));	

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
