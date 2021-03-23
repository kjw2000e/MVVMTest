package twobeone.com.mvvmtest.Interface;

public interface IMainFragmentListener {

    public static final int DIALOG_LOGIN = 0;
    public static final int DIALOG_NETWORK = 1;
    public static final int DIALOG_BLUELINK = 2;
    public static final int DIALOG_DELETED = 3;
    public static final int DIALOG_BAIDU = 4;
    public static final int DIALOG_CONNECTION_FAIL = 5;
    public static final int DIALOG_NO_SONG = 6;
    public static final int DIALOG_NOT_ADULT = 7;
    public static final int DIALOG_DUPLICATE_STREAMING = 8;

    public static final int DIALOG_DELETED_WITHOUT_BUTTON = 100;
    public static final int DIALOG_LOGIN_FAIL = 101;
    public static final int DIALOG_LOGIN_REFRESH_FAIL = 102;

    void onClickMainBtn(String viewid);
    void setPlayerScreen(boolean moveMain);
    void setMainScreen(boolean moveMain);
    void showDialog(int type);
    void dismissDialog(int type);
    void showDialog(int type, String viewId);
    void ChangeSplitScreenStatus(boolean isSSOn);
    void replacePlayableData();
    void stopRefreshAccount();
    void initdata();
    void updatePlayState(int playState);
    void showMainLoading();
    void hideMainLoading();
}
