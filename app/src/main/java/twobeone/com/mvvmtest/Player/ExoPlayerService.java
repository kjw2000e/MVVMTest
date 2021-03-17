package twobeone.com.mvvmtest.Player;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.database.DatabaseProvider;
import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.Nullable;
import okhttp3.OkHttpClient;
import twobeone.com.mvvmtest.AppConst;
import twobeone.com.mvvmtest.GlobalStatus;
import twobeone.com.mvvmtest.R;
import twobeone.com.mvvmtest.Utils;


public class ExoPlayerService extends Service implements PlayerController {
    private boolean mBound = false;
    private IBinder mBinder = new LocalBinder();
//    private HashMap<Integer, PlayerCallback> playerCallbackHashMap = new HashMap<>();
    private ArrayList<HashMap<Integer, PlayerCallback>> playerCallbackHashMap = new ArrayList<>();

    //ExoPlayer
    private final int MAX_CACHE_SIZE = 100 * 1024 * 1024;    //100Mb, cache folder maximum size
    private final int MAX_FILE_SIZE = 2 * 1024 * 1024;      //2Mb, cache file maximum size

    private SimpleExoPlayer exoPlayer = null;
    private ExoDataSourceFactory dataSourceFactory = null;
    private boolean isPrepared = false;


    private int mViewId = 0;    //player callback viewId

    private int getViewId() {
        return mViewId;
    }

    public class LocalBinder extends Binder {
        public ExoPlayerService getService() {
            return ExoPlayerService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mBound = true;

        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mBound = false;
        startProgress(false);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        exoPlayer = null;
        dataSourceFactory = null;
    }

    private void startProgress(boolean start) {
        if (start) {
            progressHandler.removeMessages(0);
            progressHandler.sendEmptyMessage(0);
        } else {
            progressHandler.removeMessages(0);
        }
    }

    private Handler progressHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            try {
                int progress = (int) exoPlayer.getCurrentPosition() / 1000;

                for (HashMap<Integer, PlayerCallback> map : playerCallbackHashMap) {
                    if (map.containsKey(GlobalStatus.getCurrentViewId())) {
                        map.get(GlobalStatus.getCurrentViewId()).onProgress(progress);
                    }
                }
            } catch (Exception e) {

            }

            progressHandler.removeMessages(0);
            progressHandler.sendEmptyMessageDelayed(0, 250);
        }
    };

    private void createPlayer() {
        if (exoPlayer == null) {
            dataSourceFactory = new ExoDataSourceFactory(getApplicationContext(), MAX_CACHE_SIZE, MAX_FILE_SIZE);
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getApplicationContext());
            exoPlayer.addListener(eventListener);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(C.USAGE_MEDIA)
                    .setContentType(C.CONTENT_TYPE_MUSIC)
                    .build();
            exoPlayer.setAudioAttributes(audioAttributes, true);
        }
        setVolume(1.0f);
    }

    private Player.EventListener eventListener = new Player.EventListener() {
        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            if (getViewId() != GlobalStatus.getCurrentViewId()) {
                return;
            }

            if (playbackState == Player.STATE_IDLE) {
                Log.e("SG2","onPlayerStateChanged playbackState : Idle");
            } else if (playbackState == Player.STATE_BUFFERING) {
                Log.e("SG2","onPlayerStateChanged playbackState : Buffering");

                for (HashMap<Integer, PlayerCallback> map : playerCallbackHashMap) {
                    if (map.containsKey(GlobalStatus.getCurrentViewId())) {
                        map.get(GlobalStatus.getCurrentViewId()).onBuffering();
                    }
                }
            } else if (playbackState == Player.STATE_READY) {
                if (isPrepared) {
                    isPrepared = false;

                    startProgress(playWhenReady);
                    Log.e("SG2","onPlayerStateChanged playbackState : onPrepared");

                    for (HashMap<Integer, PlayerCallback> map : playerCallbackHashMap) {
                        if (map.containsKey(GlobalStatus.getCurrentViewId())) {
                            map.get(GlobalStatus.getCurrentViewId()).onPrepared((int) exoPlayer.getDuration() / 1000);
                        }
                    }
                } else {
                    if (playWhenReady) {
                        startProgress(true);
                        Log.e("SG2","onPlayerStateChanged playbackState : onPlayed");

                        for (HashMap<Integer, PlayerCallback> map : playerCallbackHashMap) {
                            if (map.containsKey(GlobalStatus.getCurrentViewId())) {
                                map.get(GlobalStatus.getCurrentViewId()).onPlayed();
                            }
                        }
                    } else {
                        startProgress(false);
                        Log.e("SG2","onPlayerStateChanged playbackState : onPaused");

                        for (HashMap<Integer, PlayerCallback> map : playerCallbackHashMap) {
                            if (map.containsKey(GlobalStatus.getCurrentViewId())) {
                                map.get(GlobalStatus.getCurrentViewId()).onPaused();
                            }
                        }
                    }
                }

            } else if (playbackState == Player.STATE_ENDED) {
                Log.e("SG2","onPlayerStateChanged playbackState : onCompletion");
                startProgress(false);

                for (HashMap<Integer, PlayerCallback> map : playerCallbackHashMap) {
                    if (map.containsKey(GlobalStatus.getCurrentViewId())) {
                        map.get(GlobalStatus.getCurrentViewId()).onCompletion((int) exoPlayer.getDuration() / 1000);
                    }
                }
            }
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {
            Log.e("SG2","onPlayerStateChanged playbackState : onPlayerError");
            if (error != null && error instanceof ExoPlaybackException && error.getCause() instanceof BehindLiveWindowException) {
                setPlay();
            }
            startProgress(false);
            String errorMessage = "";
            try {
                switch (error.type) {
                    case ExoPlaybackException.TYPE_SOURCE:
                        errorMessage = error.getSourceException().getMessage();
                        break;

                    case ExoPlaybackException.TYPE_RENDERER:
                        errorMessage = error.getSourceException().getMessage();
                        break;

                    case ExoPlaybackException.TYPE_UNEXPECTED:
                        errorMessage = error.getSourceException().getMessage();
                        break;
                }
            } catch (Exception e) {}

            if (errorMessage == null) {
                errorMessage = "";
            }

            for (HashMap<Integer, PlayerCallback> map : playerCallbackHashMap) {
                if (map.containsKey(GlobalStatus.getCurrentViewId())) {
                    map.get(GlobalStatus.getCurrentViewId()).onError(errorMessage);
                }
            }
        }
    };

    private static SimpleCache simpleCache = null;
    private static SimpleCache getInstanceSimpleCache(long maxSize, Context context) {
        DatabaseProvider databaseProvider = new ExoDatabaseProvider(context);
        if (simpleCache == null)
            simpleCache = new SimpleCache(context.getCacheDir(), new LeastRecentlyUsedCacheEvictor(maxSize));
        return simpleCache;
    }

    class ExoDataSourceFactory implements DataSource.Factory {
        private final Context context;
        private final DefaultDataSourceFactory defaultDatasourceFactory;
        private final long maxFileSize, maxCacheSize;

        ExoDataSourceFactory(Context context, long maxCacheSize, long maxFileSize) {
            super();
            this.context = context;
            this.maxCacheSize = maxCacheSize;
            this.maxFileSize = maxFileSize;
            String userAgent = Util.getUserAgent(context, context.getString(R.string.app_name));
            DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter.Builder(context).build();
            OkHttpClient client = new OkHttpClient.Builder().build();

            defaultDatasourceFactory = new DefaultDataSourceFactory(this.context, bandwidthMeter,
                    new OkHttpDataSourceFactory(client, userAgent));
        }

        @Override
        public DataSource createDataSource() {
            return new CacheDataSource(getInstanceSimpleCache(maxCacheSize, context), defaultDatasourceFactory.createDataSource(),
                    new FileDataSource(), new CacheDataSink(simpleCache, maxFileSize),
                    CacheDataSource.FLAG_BLOCK_ON_CACHE | CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR, null);
        }

        public void release() {
            if (simpleCache != null) {
                try {
                    simpleCache.release();
                    simpleCache = null;
                } catch (Exception e) {

                }
            }
        }
    }

    //Implements
    @Override
    public void addPlayerCallback(int viewId, PlayerCallback callback) {
        HashMap<Integer, PlayerCallback> map = new HashMap<>();
        map.put(viewId, callback);
        playerCallbackHashMap.add(map);
    }

    public void clearPlayerCallback() {
        ArrayList<HashMap<Integer, PlayerCallback>> tempList = new ArrayList<>();

        for (HashMap<Integer, PlayerCallback> map : playerCallbackHashMap) {
            if (map.containsKey(GlobalStatus.getCurrentViewId())) {
                HashMap<Integer, PlayerCallback> tempMap = new HashMap<>();
                tempMap.put(GlobalStatus.getCurrentViewId(), map.get(GlobalStatus.getCurrentViewId()));
                tempList.add(tempMap);
            }
        }

        if (playerCallbackHashMap != null) {
            playerCallbackHashMap.clear();
            playerCallbackHashMap.addAll(tempList);
        }
    }

    @Override
    public boolean isPlaying() {
        if (exoPlayer == null) {
            return false;
        }

        if (exoPlayer.getPlaybackState() == Player.STATE_READY && exoPlayer.getPlayWhenReady()) {
            return true;
        }

        return false;
    }

    @Override
    public void prepare(int viewId, boolean isLocal, @Nullable String customKey, String url) {
        this.mViewId = viewId;
        createPlayer();
        typeSeekTo = TYPE_SEEK_TO_NONE;
        try {
            MediaSource mediaSource;
            if (isLocal) {
                Context context = getApplicationContext();
                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, context.getApplicationInfo().name));
                mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(url));
            } else {
//                ProgressiveMediaSource .Factory factory = new ProgressiveMediaSource.Factory(dataSourceFactory);

                ExtractorMediaSource.Factory factory = new ExtractorMediaSource.Factory(dataSourceFactory);
                    factory.setCustomCacheKey(customKey);
                    mediaSource = factory.createMediaSource(Uri.parse(url));
            }
            if (exoPlayer.getPlaybackState() != Player.STATE_IDLE) {
                setStop();
//                exoPlayer.stop(true);
            }
            isPrepared = true;

            exoPlayer.setPlayWhenReady(true);
            exoPlayer.prepare(mediaSource);
        } catch (Exception e) {
            isPrepared = false;

            if (getViewId() == AppConst.StreamingType.STREAMING_TYPE_MELON && GlobalStatus.getCurrentViewId() == AppConst.StreamingType.STREAMING_TYPE_MELON) {
                for (HashMap<Integer, PlayerCallback> map : playerCallbackHashMap) {
                    if (map.containsKey(GlobalStatus.getCurrentViewId())) {
                        map.get(GlobalStatus.getCurrentViewId()).onError("");
                    }
                }
            }
        }
    }

    @Override
    public void preparePauseWhenReady(int viewId, boolean isLocal, @Nullable String customKey, String url) {
        this.mViewId = viewId;
        createPlayer();
        try {
            MediaSource mediaSource;

            if (isLocal) {
                Context context = getApplicationContext();
                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, context.getApplicationInfo().name));
                mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(url));
            } else {
//                ProgressiveMediaSource.Factory factory = new ProgressiveMediaSource.Factory(dataSourceFactory);

                ExtractorMediaSource.Factory factory = new ExtractorMediaSource.Factory(dataSourceFactory);
                factory.setCustomCacheKey(customKey);
                mediaSource = factory.createMediaSource(Uri.parse(url));
            }
            if (exoPlayer.getPlaybackState() != Player.STATE_IDLE) {
                setStop();
            }
            isPrepared = true;

            exoPlayer.setPlayWhenReady(false);
            exoPlayer.prepare(mediaSource);
        } catch (Exception e) {
            isPrepared = false;

            if (getViewId() == AppConst.StreamingType.STREAMING_TYPE_MELON && GlobalStatus.getCurrentViewId() == AppConst.StreamingType.STREAMING_TYPE_MELON) {
                for (HashMap<Integer, PlayerCallback> map : playerCallbackHashMap) {
                    if (map.containsKey(GlobalStatus.getCurrentViewId())) {
                        map.get(GlobalStatus.getCurrentViewId()).onError("");
                    }
                }
            }
        }
    }

    @Override
    public void setPlay() {
        if (exoPlayer == null) {
            return;
        }

        try {
            setVolume(1.0f);
            if (exoPlayer.getPlaybackState() == Player.STATE_READY) {
                if (!exoPlayer.getPlayWhenReady()) {
                    exoPlayer.setPlayWhenReady(true);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void setPause() {
        if (exoPlayer == null) {
            return;
        }

        try {
            if (exoPlayer.getPlaybackState() == Player.STATE_READY) {
                if (exoPlayer.getPlayWhenReady()) {
                    exoPlayer.setPlayWhenReady(false);
                }
            }
        } catch (Exception e) {
        }
    }

    private final int TYPE_SEEK_TO_NONE = 0;
    private final int TYPE_SEEK_TO_PAUSE = 1;
    private final int TYPE_SEEK_TO_RESUME = 2;

    private int typeSeekTo = TYPE_SEEK_TO_NONE;
    @Override
    public void setSeekTo(int sec, boolean wasPlaying) {
        if (exoPlayer == null) {
            return;
        }

        if (exoPlayer.getPlaybackState() == Player.STATE_READY) {
            int max = (int) exoPlayer.getDuration() / 1000;
            if (sec >= max) {
                setStop();
                for (HashMap<Integer, PlayerCallback> map : playerCallbackHashMap) {
                    if (map.containsKey(GlobalStatus.getCurrentViewId())) {
                        map.get(GlobalStatus.getCurrentViewId()).onCompletion(max);
                    }
                }
            } else {
                exoPlayer.seekTo(sec * 1000);
                if (!exoPlayer.getPlayWhenReady() && wasPlaying) {
                    exoPlayer.setPlayWhenReady(true);
                }
            }
        }
    }

    @Override
    public void setSeekToNotResume(int sec) {
        if (exoPlayer == null) {
            return;
        }

        if (exoPlayer.getPlaybackState() == Player.STATE_READY) {
            exoPlayer.seekTo(sec * 1000);
        }
    }

    @Override
    public void setSeekToVideo(long sec) {
        if (exoPlayer == null) {
            return;
        }

        exoPlayer.seekTo(sec);
    }

    @Override
    public void setStop() {
        startProgress(false);
        try {
            if (exoPlayer != null) {
                exoPlayer.stop(true);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void setPlayPauseUI(boolean isPlay) {
        try {
//            for (HashMap<Integer, PlayerCallback> map : playerCallbackHashMap) {
//                if (map.containsKey(GlobalStatus.getCurrentViewId())) {
//                    if (isPlay) {
//                        map.get(GlobalStatus.getCurrentViewId()).onPlayed();
//                    } else {
//                        map.get(GlobalStatus.getCurrentViewId()).onPaused();
//                    }
//                }
//            }
        } catch (Exception e) {
        }
    }

    @Override
    public void release() {
        try {
            typeSeekTo = TYPE_SEEK_TO_NONE;
            startProgress(false);
            if (exoPlayer != null) {
                exoPlayer.removeListener(eventListener);
                if (exoPlayer.getPlayWhenReady()) {
                    exoPlayer.setVolume(0.0f);
                    exoPlayer.setPlayWhenReady(false);
                }

                exoPlayer.release();
                exoPlayer = null;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void setVolume(float volume) {
        if (exoPlayer == null) {
            return;
        }

        try {
            exoPlayer.setVolume(volume);
        } catch (Exception e) {}
    }

    @Override
    public Player getPlayer() {
        if (exoPlayer != null) {
            return exoPlayer;
        }
        return null;
    }


    @Override
    public long getCurrentPosition() {
        if (exoPlayer != null) {
            return exoPlayer.getCurrentPosition();
        }

        return 0;
    }


    @Override
    public void initializePlayer() {
        createPlayer();
    }
}
