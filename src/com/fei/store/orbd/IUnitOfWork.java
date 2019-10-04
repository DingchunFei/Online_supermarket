package com.fei.store.orbd;

import java.sql.SQLException;

public interface IUnitOfWork<T> {
	
	String INSERT = "INSERT";
	String DELETE = "DELETE";
	String MODIFY = "MODIFY";
	
	void registerNew(T Object);
	
	void registerModified(T Object);
	
	void registerDeleted(T Object);

	void commit() throws SQLException;

}
