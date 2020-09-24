package common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.UIManager;

// 工具类
public class Tools {

	// 设定默认字体
	public static void setDefaultFont() {
		UIManager.put("Label.font", new java.awt.Font("微软雅黑", 0, 14));
		UIManager.put("Button.font", new java.awt.Font("微软雅黑", 0, 14));
		UIManager.put("TextField.font", new java.awt.Font("微软雅黑", 0, 14));
		UIManager.put("PasswordField.font", new java.awt.Font("微软雅黑", 0, 14));
		UIManager.put("ComboBox.font", new java.awt.Font("微软雅黑", 0, 14));
	}

	// 判断是否为合法字符(数字/大小写字母)
	public static boolean isLegalCharacter(int ch) {
		return Character.isDigit(ch) || Character.isUpperCase(ch) || Character.isLowerCase(ch);
	}

	// 将"yyyy-MM-dd"类型的String转为Date
	public static Date stringToDate(String s) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(s);
	}

}
