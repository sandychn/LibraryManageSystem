package manager.user.update.dao;

import common.dao.CommonDataAccess;
import entity.User;

import java.sql.SQLException;

public class MgrUserUpdateDataAccess extends CommonDataAccess {

    public void userIdentityUpdate(User user) throws SQLException {
        String sql = "update lib_user set uIdentity = \'" + user.getIdentity() + "\' where uName=\'"
                + user.getUsername() + "\' ";
        executeUpdate(sql);
    }

}
