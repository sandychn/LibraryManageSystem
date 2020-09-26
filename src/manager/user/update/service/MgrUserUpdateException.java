package manager.user.update.service;

@SuppressWarnings("serial") // 新建用户/修改用户身份异常处理
public class MgrUserUpdateException extends Exception {

    public MgrUserUpdateException(String message) {
        super(message);
    }

}
