package twobeone.com.mvvmtest.Player;

import com.google.android.exoplayer2.Player;

import androidx.annotation.Nullable;

public interface PlayerController {
    void addPlayerCallback(int viewId, PlayerCallback callback);

    boolean isPlaying();

    void prepare(int viewId, boolean isLocal, @Nullable String customKey, String url);

    void preparePauseWhenReady(int viewId, boolean isLocal, @Nullable String customKey, String url);

    void setPlay();

    void setPause();

    void setSeekTo(int sec, boolean wasPlaying);

    void setSeekToNotResume(int sec);

    void setSeekToVideo(long sec);

    void setStop();

    void setPlayPauseUI(boolean isPlay);

    void release();

    void setVolume(float volume);

    long getCurrentPosition();

    Player getPlayer();

    void initializePlayer();
}
