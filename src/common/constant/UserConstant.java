package common.constant;

public abstract class UserConstant {

    public static final int USERNAME_MAXLEN = 20; // 用户名最大长度
    public static final int PASSWORD_MAXLEN = 16; // 密码最大长度

    public static final int IDENTITY_READER = 1;           // 学生
    public static final int IDENTITY_USER_ADMIN = 2;       // 图书馆用户管理员
    public static final int IDENTITY_STORE_ADMIN = 3;      // 仓库管理员
    public static final int IDENTITY_SUPER_USER_ADMIN = 4; // 用户身份管理员 (通过数据库初始设定,不能授予用户该身份)
}
