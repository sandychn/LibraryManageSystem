package register.service;

import java.sql.SQLException;

import common.dao.CommonDataAccess;
import common.util.Tools;
import entity.User;

public class RegService {

	private CommonDataAccess dao;

	public RegService() {
		dao = new CommonDataAccess();
	}

	public User tryRegister(User user) throws RegException {
		if (user.getUsername().length() < 5 || user.getUsername().length() > 20) {
			throw new RegException("用户名长度介于5到20之间");
		}
		if (user.getPassword().length() < 6 || user.getPassword().length() > 16) {
			throw new RegException("密码长度介于6到16之间");
		}
		for (char ch : user.getUsername().toCharArray()) {
			if (!Tools.isLegalCharacter(ch)) {
				throw new RegException("用户名中含无效字符，请重新输入");
			}
		}
		for (char ch : user.getPassword().toCharArray()) {
			if (!Tools.isLegalCharacter(ch)) {
				throw new RegException("密码中含无效字符，请重新输入");
			}
		}
		try {
			try { // 判断是否已存在该用户名
				if (dao.isUserExistsByUname(user.getUsername())) {
					throw new RegException("该用户名已被使用");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				throw new RegException("数据库出错。请联系管理员");
			}
			dao.insertNewUser(user);
			return user;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new RegException("数据库出错。请联系管理员");
		}
	}

}
