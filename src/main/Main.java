package main;

import common.util.Tools;
import login.LoginWindow;

public class Main {

	static {
		try {
			// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Tools.setDefaultFont();
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		LoginWindow loginWindow = new LoginWindow();
	}

}
