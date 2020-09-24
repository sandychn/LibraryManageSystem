package common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import entity.Book;
import entity.User;

public class CommonDataAccess extends DataAccess {

	public User getUserByUName(String uName) throws SQLException {
		ResultSet resultSet = executeQuery("select uPassword, uIdentity from lib_user where uName='" + uName + "'");
		if (!resultSet.next())
			return null; // 用户不存在
		return new User(uName, resultSet.getString("uPassword"), resultSet.getInt("uIdentity"));
	}

	// 注册时, 管理员新建、修改用户时均用到此方法
	public boolean isUserExistsByUname(String uName) throws SQLException {
		ResultSet resultSet = executeQuery("select count(*) cnt from lib_user where uName='" + uName + "'");
		resultSet.next();
		return resultSet.getInt("cnt") > 0;
	}

	// 注册时, 管理员新建用户时均用到此方法
	public void insertNewUser(User user) throws SQLException {
		executeUpdate("insert into lib_user values('" + user.getUsername() + "','" + user.getPassword() + "',"
				+ user.getIdentity() + ")");
	}

	// 由ISBN获得这个Book对象, 若不存在该ISBN书则返回null
	public Book getBookByBookISBN(String isbn) throws SQLException {
		ResultSet resultSet = executeQuery("select * from book where bISBN='" + isbn + "'");
		if (!resultSet.next()) {
			return null;
		}
		return new Book(isbn, resultSet.getString("bName"), resultSet.getString("bAuthor"),
				resultSet.getString("bPress"), resultSet.getInt("bClassId"), resultSet.getInt("bCount"),
				resultSet.getInt("bSum"));
	}

	// 取图书类型列表
	public String[] getBookClassList() throws SQLException {
		ResultSet resultSet = executeQuery("select bClassName from book_class");
		ArrayList<String> list = new ArrayList<String>();
		while (resultSet.next()) {
			list.add(resultSet.getString("bClassName"));
		}
		return list.toArray(new String[0]);
	}

	public int getClassIdByClassName(String className) throws SQLException {
		ResultSet resultSet = executeQuery("select bClassId from book_class where bClassName='" + className + "'");
		resultSet.next();
		return resultSet.getInt("bClassId");
	}

	public String getClassNameByClassId(int classId) throws SQLException {
		ResultSet resultSet = executeQuery("select bClassName from book_class where bClassId=" + classId);
		resultSet.next();
		return resultSet.getString("bClassName");
	}

	public int getBookClassCount() throws SQLException {
		ResultSet resultSet = executeQuery("select COUNT(*) cnt from book_class");
		resultSet.next();
		return resultSet.getInt("cnt");
	}

	public Date getDate() throws SQLException {
		ResultSet resultSet = executeQuery("select now() now");
		resultSet.next();
		return resultSet.getDate("now");
	}

	public boolean isBookClassNameExists(String bClassName) throws SQLException {
		String sql = "select bClassId from book_class where bClassName = '" + bClassName + "'";
		ResultSet res = executeQuery(sql);
		return res.next();
	}

	public String getbClassNameBybClassId(int bclassId) throws SQLException {
		ResultSet res = executeQuery("select bClassName from book_class where bClassId = '" + bclassId + "'");
		res.next();
		return res.getString(1);
	}

	public Vector<Vector<String>> getBookTableVector(String bName, String bISBN, String bAuthor, String bPress,
			int bClassId) throws SQLException {
		// 将一个单引号替换为两个单引号, 防止SQL语句出错
		bName = bName.replace("'", "''");
		bISBN = bISBN.replace("'", "''");
		bAuthor = bAuthor.replace("'", "''");
		bPress = bPress.replace("'", "''");

		int constraintCount = 0;
		String sql = "select * from book";
		// 模糊匹配不使用 like, 防止用户输入 % 或 _ 等通配符, 而使用 instr
		if (!bName.isEmpty()) {
			++constraintCount;
			sql += " where ";
			sql += String.format("instr(bName, '%s')>0", bName);
		}
		if (!bISBN.isEmpty()) {
			sql += constraintCount++ == 0 ? " where " : " and ";
			sql += "bISBN='" + bISBN + "'";
		}
		if (!bAuthor.isEmpty()) {
			sql += constraintCount++ == 0 ? " where " : " and ";
			sql += String.format("instr(bAuthor, '%s')>0", bAuthor);
		}
		if (!bPress.isEmpty()) {
			sql += constraintCount++ == 0 ? " where " : " and ";
			sql += String.format("instr(bPress, '%s')>0", bPress);
		}
		if (bClassId != 0) {
			sql += constraintCount++ == 0 ? " where " : " and ";
			sql += "bClassId=" + bClassId;
		}

		ResultSet resultSet = executeQuery(sql);
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		String[] bookClassList = getBookClassList();

		while (resultSet.next()) {
			Vector<String> row = new Vector<String>();
			row.add(resultSet.getString("bName"));
			row.add(resultSet.getString("bAuthor"));
			row.add(resultSet.getString("bPress"));
			row.add(resultSet.getString("bISBN"));
			row.add(bookClassList[resultSet.getInt("bClassId") - 1]);
			row.add(String.valueOf(resultSet.getInt("bSum")));
			row.add(String.valueOf(resultSet.getInt("bCount")));
			data.add(row);
		}
		return data;
	}

}