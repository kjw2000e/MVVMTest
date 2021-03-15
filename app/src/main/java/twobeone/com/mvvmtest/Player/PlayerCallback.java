package twobeone.com.mvvmtest.Player;

public interface PlayerCallback {
    void onPrepared(int duration);

    void onPlayed();

    void onPaused();

    void onProgress(int sec);

    void onCompletion(int duration);

    void onError(String errMsg);

    void onBuffering();

    void onBufferingEnd();
}
