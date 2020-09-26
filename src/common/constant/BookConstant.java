package common.constant;

public abstract class BookConstant {

    public static final int ISBN_LEN = 13;
    public static final int NAME_MAXLEN = 40;
    public static final int AUTHOR_MAXLEN = 20;
    public static final int PRESS_MAXLEN = 40;
    public static final int CLASS_MAXLEN = 20;
    public static final long DEFAULT_BLDUE = 30; // 默认借期
    public static final long RENEW_BLDUE = 60; // 续借借期
    public static final long TRY_RENEW_DAY = 7; // 借期剩余 TRY_RENEW_DAY 之后才能续借

}
