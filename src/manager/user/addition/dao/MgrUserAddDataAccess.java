package manager.user.addition.dao;

import java.sql.SQLException;

import common.dao.CommonDataAccess;
import entity.User;

public class MgrUserAddDataAccess extends CommonDataAccess {

	public void addUser(User user) throws SQLException {
		executeUpdate("insert into lib_user values(\'" + user.getUsername() + "\',\'" + user.getPassword() + "\',"
				+ user.getIdentity() + ")");
	}

}
