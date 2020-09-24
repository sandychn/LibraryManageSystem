package manager.user.update.service;

import java.sql.SQLException;

import entity.User;
import manager.user.update.dao.MgrUserUpdateDataAccess;

public class MgrUserUpdateService {

	private MgrUserUpdateDataAccess dao;// 修改用户身份操作
	private User user;
	private boolean isUserExists;

	public MgrUserUpdateService() {
		dao = new MgrUserUpdateDataAccess();
		isUserExists = false;
	}

	public void updateUserIdentity(int newIdentity) throws MgrUserUpdateException {
		if (user == null) {
			throw new MgrUserUpdateException("数据异常");
		}
		try {// 向数据库中修改用户身份
			User newUser = new User(user.getUsername(), user.getPassword(), newIdentity);
			dao.userIdentityUpdate(user = newUser);
		} catch (SQLException sqle) {
			throw new MgrUserUpdateException("数据库出错。错误信息:\n" + sqle.getMessage());
		}
	}

	public void setUserByuName(String username) throws MgrUserUpdateException {
		try {
			user = dao.getUserByUName(username);
		} catch (SQLException sqle) {
			throw new MgrUserUpdateException("数据库出错。错误信息:\n" + sqle.getMessage());
		}
	}

	public User getUser() {
		return user;
	}

	public boolean getUserExists() {
		return isUserExists;
	}

	public void setUserExists(boolean isUserExists) {
		this.isUserExists = isUserExists;
	}

	public boolean judgeUserExists(String username) {
		try {
			return dao.getUserByUName(username) != null;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}

}
