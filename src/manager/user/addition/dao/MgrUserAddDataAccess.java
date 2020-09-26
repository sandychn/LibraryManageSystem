package manager.user.addition.dao;

import common.dao.CommonDataAccess;
import entity.User;

import java.sql.SQLException;

public class MgrUserAddDataAccess extends CommonDataAccess {

    public void addUser(User user) throws SQLException {
        executeUpdate("insert into lib_user values(\'" + user.getUsername() + "\',\'" + user.getPassword() + "\',"
                + user.getIdentity() + ")");
    }

}
