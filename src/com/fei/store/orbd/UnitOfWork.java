package com.fei.store.orbd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fei.store.dao.UserMapper;
import com.fei.store.domain.User;

public class UnitOfWork implements IUnitOfWork<User> {

	  private Map<String, List<User>> map;
	  private UserMapper userMapper;

	  /**
	   * @param context         set of operations to be perform during commit.
	   * @param studentDatabase Database for student records.
	   */
	  public UnitOfWork (Map<String, List<User>> map, UserMapper userMapper) {
	    this.map = map;
	    this.userMapper = userMapper;
	  }

	  @Override
	  public void registerNew(User user) {
	    //LOGGER.info("Registering {} for insert in context.", student.getName());
	    register(user, IUnitOfWork.INSERT);
	  }

	  @Override
	  public void registerModified(User user) {
	    //LOGGER.info("Registering {} for modify in context.", student.getName());
	    register(user, IUnitOfWork.MODIFY);

	  }

	  @Override
	  public void registerDeleted(User user) {
	    //LOGGER.info("Registering {} for delete in context.", student.getName());
	    register(user, IUnitOfWork.DELETE);
	  }

	  private void register(User user, String operation) {
	    List<User> usersToOperate = map.get(operation);
	    if (usersToOperate == null) {
	    	usersToOperate = new ArrayList<>();
	    }
	    usersToOperate.add(user);
	    map.put(operation, usersToOperate);
	  }

	  /**
	   * All UnitOfWork operations are batched and executed together on commit only.
	 * @throws SQLException 
	   */
	  @Override
	  public void commit() throws SQLException {
	    if (map == null || map.size() == 0) {
	      return;
	    }
	    //LOGGER.info("Commit started");
	    if (map.containsKey(IUnitOfWork.INSERT)) {
	      commitInsert();
	    }
	    if (map.containsKey(IUnitOfWork.MODIFY)) {
	      commitModify();
	    }
	    if (map.containsKey(IUnitOfWork.DELETE)) {
	      commitDelete();
	    }
	    //LOGGER.info("Commit finished.");
	  }

	  private void commitInsert() throws SQLException {
	    List<User> usersToBeInserted = map.get(IUnitOfWork.INSERT);
	    for (User user : usersToBeInserted) {
	      //LOGGER.info("Saving {} to database.", student.getName());
	    	userMapper.userInsert(user);
	    }
	  }

	  private void commitModify() throws SQLException {
	    List<User> modifiedUsers = map.get(IUnitOfWork.MODIFY);
	    for (User user : modifiedUsers) {
	      //LOGGER.info("Modifying {} to database.", student.getName());
	    	userMapper.userModify(user);
	    }
	  }

	  private void commitDelete() throws SQLException {
	    List<User> deletedUsers = map.get(IUnitOfWork.DELETE);
	    for (User user : deletedUsers) {
	      //LOGGER.info("Deleting {} to database.", student.getName());
	    	userMapper.userDelete(user);
	    }
	  }
	}
