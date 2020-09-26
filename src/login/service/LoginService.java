package login.service;

import common.dao.CommonDataAccess;
import common.util.Tools;
import entity.User;

import java.sql.SQLException;

public class LoginService {

    private CommonDataAccess dao;

    public LoginService() throws SQLException {
        this.dao = new CommonDataAccess();
        this.dao.connectDatabase();
    }

    public void connectDatabase() throws SQLException {
        dao.connectDatabase();
    }

    public void disconnectDatabase() throws SQLException {
        dao.disconnectDatabase();
    }

    // 尝试以user登录 若登录成功则返回登录的User(带有身份) 登录失败则抛出LoginException异常
    public User tryLogin(User user) throws LoginException {
        if (user.getUsername().length() == 0 || user.getPassword().length() == 0) {
            throw new LoginException("请输入用户名与密码");
        }
        for (char ch : user.getUsername().toCharArray()) {
            if (!Tools.isLegalCharacter(ch)) {
                throw new LoginException("用户名中含无效字符，请重新输入");
            }
        }
        for (char ch : user.getPassword().toCharArray()) {
            if (!Tools.isLegalCharacter(ch)) {
                throw new LoginException("密码中含无效字符，请重新输入");
            }
        }
        try {
            User newUser = dao.getUserByUName(user.getUsername());
            if (newUser == null) {
                throw new LoginException("不存在该用户, 请检查输入的用户名");
            }
            if (!user.getPassword().equals(newUser.getPassword())) {
                throw new LoginException("用户名或密码错误");
            }
            return newUser;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new LoginException("数据库出错。请联系管理员");
        }
    }

}
