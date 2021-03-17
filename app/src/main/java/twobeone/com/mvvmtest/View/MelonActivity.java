package twobeone.com.mvvmtest.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import twobeone.com.mvvmtest.AppConst;
import twobeone.com.mvvmtest.GlobalStatus;
import twobeone.com.mvvmtest.Interface.OnPlayListItemClickListener;
import twobeone.com.mvvmtest.View.adapter.PlayListAdapter;
import twobeone.com.mvvmtest.Model.Melon.MelonItem;
import twobeone.com.mvvmtest.Model.Melon.MelonStreamingItem;
import twobeone.com.mvvmtest.Model.vo.Resource;
import twobeone.com.mvvmtest.Model.vo.Status;
import twobeone.com.mvvmtest.Player.ExoPlayerService;
import twobeone.com.mvvmtest.Player.PlayerCallback;
import twobeone.com.mvvmtest.Player.PlayerController;
import twobeone.com.mvvmtest.View.viewmodel.MainViewModel;
import twobeone.com.mvvmtest.databinding.ActivityMelonBinding;
import twobeone.com.mvvmtest.databinding.IncludeMiniPlayerBinding;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.exoplayer2.ui.PlayerView;

import java.util.ArrayList;

public class MelonActivity extends AppCompatActivity implements PlayerCallback {
    private ActivityMelonBinding activityMelonBinding;
    private IncludeMiniPlayerBinding miniPlayerBinding;
    private MainViewModel mainViewModel;
    private RecyclerView mRv;
    private PlayListAdapter mPlayListAdapter;

    private PlayerView mPlayerView;
    private boolean mBound = false;
    private ExoPlayerService mService = null;
    private PlayerController playerController = null;

    private RelativeLayout layout_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMelonBinding = ActivityMelonBinding.inflate(getLayoutInflater());
//        miniPlayerBinding = IncludeMiniPlayerBinding.inflate(getLayoutInflater());

        View view = activityMelonBinding.getRoot();
        setContentView(view);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.init();

        GlobalStatus.setCurrentViewId(AppConst.StreamingType.STREAMING_TYPE_MELON);

        mRv = activityMelonBinding.rv;
        mRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        miniPlayerBinding = activityMelonBinding.layoutPlayer;

        mPlayListAdapter = new PlayListAdapter(new OnPlayListItemClickListener() {
            @Override
            public void onClick(Object item, String type, boolean isPlayDepth) {
                mainViewModel.getStreamingInfo((MelonItem)item).observe(MelonActivity.this, new Observer<Resource<MelonStreamingItem>>() {
                    @Override
                    public void onChanged(Resource<MelonStreamingItem> item) {
                        Log.e("kjw333", item.toString());
                        if (item.status == Status.SUCCESS) {
                            if (item.data != null) {
                                if (playerController != null) {
                                    Log.e("kjw333", "path :" + item.data.getGETPATHINFO().getPATH());
                                    String customKey = item.data.getGETPATHINFO().getCID() + item.data.getGETPATHINFO().getMETATYPE() + item.data.getGETPATHINFO().getBITRATE() + item.data.getGETPATHINFO().getPLAYTIME();
                                    Log.e("kjw333", "customKey :" + customKey);
                                    playerController.prepare(AppConst.StreamingType.STREAMING_TYPE_MELON, false, customKey, item.data.getGETPATHINFO().getPATH());
                                }
                            }
                        } else if (item.status == Status.LOADING) {

                        } else {
                            Log.e("kjw333", "error : " + item.message);
                        }
                    }
                });
            }
        });
        mRv.setAdapter(mPlayListAdapter);

        activityMelonBinding.getlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.getChartList().observe(MelonActivity.this, new Observer<Resource<ArrayList<MelonItem>>>() {
                    @Override
                    public void onChanged(Resource<ArrayList<MelonItem>> melonItem) {
                        if (melonItem.status == Status.SUCCESS) {
                            Log.e("kjw333", "success : ");
                            if (melonItem.data != null) {
                                mPlayListAdapter.updateData("melon chart", true, melonItem.data, null);
                            }
                        } else {
                            Log.e("kjw333", "onfail : " + melonItem.message);
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
                playerController.addPlayerCallback(AppConst.StreamingType.STREAMING_TYPE_MELON, MelonActivity.this);

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