package twobeone.com.mvvmtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import twobeone.com.mvvmtest.Interface.OnMelonItemClickListener;
import twobeone.com.mvvmtest.Model.MelonItem;
import twobeone.com.mvvmtest.Model.MelonStreamingItem;
import twobeone.com.mvvmtest.Player.ExoPlayerService;
import twobeone.com.mvvmtest.Player.PlayerCallback;
import twobeone.com.mvvmtest.Player.PlayerController;
import twobeone.com.mvvmtest.databinding.ActivityMainBinding;
import twobeone.com.mvvmtest.databinding.IncludeMiniPlayerBinding;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.exoplayer2.ui.PlayerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PlayerCallback {

    private ActivityMainBinding activityMainBinding;
    private IncludeMiniPlayerBinding miniPlayerBinding;
    private MainViewModel mainViewModel;
    private RecyclerView mRv;
    private MelonChartAdapter mMelonChartAdapter;

    private PlayerView mPlayerView;
    private boolean mBound = false;
    private ExoPlayerService mService = null;
    private PlayerController playerController = null;

    private RelativeLayout layout_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
//        miniPlayerBinding = IncludeMiniPlayerBinding.inflate(getLayoutInflater());

        View view = activityMainBinding.getRoot();
        setContentView(view);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.init();

        GlobalStatus.setCurrentViewId(AppConst.StreamingType.STREAMING_TYPE_MELON);

        mRv = activityMainBinding.rv;
        mRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        miniPlayerBinding = activityMainBinding.layoutPlayer;

        mMelonChartAdapter = new MelonChartAdapter(new OnMelonItemClickListener() {
            @Override
            public void onClick(MelonItem item) {
                mainViewModel.getStreamingInfo(item).observe(MainActivity.this, new Observer<MelonStreamingItem>() {
                    @Override
                    public void onChanged(MelonStreamingItem melonStreamingItem) {
                        Log.e("kjw333", "here111");

                        if (melonStreamingItem != null) {
                            // 엑소 플레이어 플레이

                            if (playerController != null) {
                                Log.e("kjw333", "path :" + melonStreamingItem.getGETPATHINFO().getPATH());
                                String customKey = melonStreamingItem.getGETPATHINFO().getCID() + melonStreamingItem.getGETPATHINFO().getMETATYPE() + melonStreamingItem.getGETPATHINFO().getBITRATE() + melonStreamingItem.getGETPATHINFO().getPLAYTIME();
                                Log.e("kjw333", "customKey :" + customKey);
                                playerController.preparePauseWhenReady(AppConst.StreamingType.STREAMING_TYPE_MELON, false, customKey, melonStreamingItem.getGETPATHINFO().getPATH());
                            }

//                            miniPlayerBinding.splitTitle.setText(melonStreamingItem.getCONTENTSINFO());
                        }
                    }
                });
            }
        });
        mRv.setAdapter(mMelonChartAdapter);

        activityMainBinding.getlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.getChartList().observe(MainActivity.this, new Observer<ArrayList<MelonItem>>() {
                    @Override
                    public void onChanged(ArrayList<MelonItem> melonItems) {
                        if (melonItems != null) {
                            mMelonChartAdapter.UpdateData(melonItems);
                        }
                    }
                });
            }
        });

        // 서비스 시작
        Intent intent = new Intent(getApplicationContext(), ExoPlayerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("kjw333", "onServiceConnected");
            if (!mBound) {
                ExoPlayerService.LocalBinder binder = (ExoPlayerService.LocalBinder)service;
                mService = binder.getService();
                mBound = true;

                playerController = mService;
                playerController.addPlayerCallback(AppConst.StreamingType.STREAMING_TYPE_MELON, MainActivity.this);

//                mPlayerView.setPlayer(playerController.getPlayer());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };


    @Override
    public void onPrepared(int duration) {
        Log.e("kjw333", "onPrepared : " + duration);

        playerController.setPlay();
    }

    @Override
    public void onPlayed() {
        Log.e("kjw333", "onPlayed : ");
    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onProgress(int sec) {

    }

    @Override
    public void onCompletion(int duration) {

    }

    @Override
    public void onError(String errMsg) {

    }

    @Override
    public void onBuffering() {

    }

    @Override
    public void onBufferingEnd() {

    }
}