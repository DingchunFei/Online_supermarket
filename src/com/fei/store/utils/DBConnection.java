package com.fei.store.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBConnection {

	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/Online_Store";
	
	//private static final String DB_CONNECTION = "jdbc:postgresql://115.159.64.55:8010/Online_Store";
	
	private static final String DB_USER ="postgres";
	private static final String DB_PASSWORD="123456";
	
	static Connection dbConnection =null;
	
	public static PreparedStatement prepare(String stm){
		PreparedStatement preparedStatement = null ;
		try{
			Connection dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(stm);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return preparedStatement;
	}

	public static PreparedStatement prepare(Connection connection, String stm){
		PreparedStatement preparedStatement = null ;
		try{
			preparedStatement = connection.prepareStatement(stm);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return preparedStatement;
	}
	
	public static Connection getDBConnection(){
		try{
			DriverManager.registerDriver(new org.postgresql.Driver());
			dbConnection = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
			
			dbConnection.setAutoCommit(false);
			return dbConnection;
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		System.out.println("Connection problem");
		return null;
	}


	public static void commit() {
		try {
			dbConnection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
