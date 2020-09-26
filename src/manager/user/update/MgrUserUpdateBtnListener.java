package manager.user.update;

import common.constant.UserConstant;
import manager.user.update.service.MgrUserUpdateException;
import manager.user.update.service.MgrUserUpdateService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MgrUserUpdateBtnListener implements ActionListener {// 修改用户身份按钮事件

    private MgrUserUpdateWindow userUpdate;
    private MgrUserUpdateService mgrUserUpdateService;

    public MgrUserUpdateBtnListener(MgrUserUpdateWindow userUpdate, MgrUserUpdateService mgrUserUpdateService) {
        this.userUpdate = userUpdate;
        this.mgrUserUpdateService = mgrUserUpdateService;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String uName = userUpdate.getUName();
            if (mgrUserUpdateService.getUserExists() == false) {
                JOptionPane.showMessageDialog(null, "请先查询该用户身份", "更新失败", JOptionPane.ERROR_MESSAGE);
            } else if (mgrUserUpdateService.judgeUserExists(uName) == false) {
                JOptionPane.showMessageDialog(null, "该用户不存在", "更新失败", JOptionPane.ERROR_MESSAGE);
            } else if (mgrUserUpdateService.getUser().getIdentity() == UserConstant.IDENTITY_SUPER_USER_ADMIN) {
                JOptionPane.showMessageDialog(null, "无该用户的修改权限", "更身份新失败", JOptionPane.ERROR_MESSAGE);
                mgrUserUpdateService.setUserExists(false);
            } else {
                mgrUserUpdateService.updateUserIdentity(userUpdate.getUserNewIdentity());
                JOptionPane.showMessageDialog(null, "该用户身份已成功修改", "成功", JOptionPane.INFORMATION_MESSAGE);
                mgrUserUpdateService.setUserExists(false);
                int identity = mgrUserUpdateService.getUser().getIdentity();
                if (identity == UserConstant.IDENTITY_READER) {
                    userUpdate.setUId("学生");
                } else if (identity == UserConstant.IDENTITY_USER_ADMIN) {
                    userUpdate.setUId("图书馆用户管理员");
                } else if (identity == UserConstant.IDENTITY_STORE_ADMIN) {
                    userUpdate.setUId("仓库管理员");
                }
            }
        } catch (MgrUserUpdateException exp) {
            JOptionPane.showMessageDialog(null, exp.getMessage(), "失败", JOptionPane.ERROR_MESSAGE);
        }
    }
}
