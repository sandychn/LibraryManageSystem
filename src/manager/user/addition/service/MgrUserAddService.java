package manager.user.addition.service;

import java.sql.SQLException;

import common.constant.UserConstant;
import common.dao.CommonDataAccess;
import common.util.Tools;
import entity.User;
import manager.user.addition.dao.MgrUserAddDataAccess;

public class MgrUserAddService {// 新建用户操作

	private MgrUserAddDataAccess dao;

	public MgrUserAddService() {
		dao = new MgrUserAddDataAccess();
	}

	public CommonDataAccess getConnection() {
		return dao;
	}

	public User tryRegister(User user) throws MgrUserAddException {
		if (user.getUsername().length() < 5 || user.getUsername().length() > 20) {
			throw new MgrUserAddException("用户名长度介于5到20之间");
		}
		if (user.getPassword().length() < 6 || user.getPassword().length() > 16) {
			throw new MgrUserAddException("密码长度介于6到16之间");
		}
		if (user.getIdentity() != UserConstant.IDENTITY_USER_ADMIN
				&& user.getIdentity() != UserConstant.IDENTITY_READER) {
			throw new MgrUserAddException(
					"身份只能为管理员(" + UserConstant.IDENTITY_USER_ADMIN + ")或学生(" + UserConstant.IDENTITY_READER + ")");
		}
		for (char ch : user.getUsername().toCharArray()) {
			if (!Tools.isLegalCharacter(ch)) {
				throw new MgrUserAddException("用户名中含无效字符，请重新输入");
			}
		}
		for (char ch : user.getPassword().toCharArray()) {
			if (!Tools.isLegalCharacter(ch)) {
				throw new MgrUserAddException("密码中含无效字符，请重新输入");
			}
		}
		try {
			try { // 判断是否已存在该用户名
				if (dao.isUserExistsByUname(user.getUsername())) {
					throw new MgrUserAddException("该用户名已被使用");
				}
			} catch (SQLException sqle) {
				throw new MgrUserAddException("数据库出错。请联系管理员");
			}
			dao.addUser(user);
			return user;
		} catch (SQLException sqle) {
			throw new MgrUserAddException("数据库出错。请联系管理员");
		}
	}

}
