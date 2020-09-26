package common.util;

import entity.User;

public class CurrentUser {

    private static User currentUser;

    public static void setCurrentUser(User currentUser) {
        CurrentUser.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

}
