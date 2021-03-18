package twobeone.com.mvvmtest;

import java.util.List;

public class PlayListManager2<T> {

    private static final PlayListManager2 instance = new PlayListManager2();

    private List<T> mPlayList = null;

    public static PlayListManager2 getInstance() {
        return instance;
    }


}
