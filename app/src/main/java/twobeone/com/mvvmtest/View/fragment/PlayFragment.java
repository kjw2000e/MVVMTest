package twobeone.com.mvvmtest.View.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import twobeone.com.mvvmtest.AppConst;
import twobeone.com.mvvmtest.GeniePlayListManager;
import twobeone.com.mvvmtest.Interface.IMainFragmentListener;
import twobeone.com.mvvmtest.Interface.OnPlayListItemClickListener;
import twobeone.com.mvvmtest.Model.Genie.GenieItem;
import twobeone.com.mvvmtest.Model.Genie.GeniePlayListItem;
import twobeone.com.mvvmtest.Model.Genie.GenieStreamingItem;
import twobeone.com.mvvmtest.Model.vo.Resource;
import twobeone.com.mvvmtest.Model.vo.Status;
import twobeone.com.mvvmtest.Player.PlayerCallback;
import twobeone.com.mvvmtest.Player.PlayerController;
import twobeone.com.mvvmtest.R;
import twobeone.com.mvvmtest.View.adapter.PlayListAdapter;
import twobeone.com.mvvmtest.View.viewmodel.MainFragmentViewModel;
import twobeone.com.mvvmtest.databinding.FragmentPlayBinding;

/**
 * mady by jiwon
 */
public class PlayFragment extends Fragment implements PlayerCallback {
    private FragmentPlayBinding playBinding;
    private IMainFragmentListener mainActListener;
    private RecyclerView mRvPlayList;
    private PlayListAdapter<GenieItem, GeniePlayListItem> playListAdapter;
    private MainFragmentViewModel mainFragmentViewModel;

    private PlayerController playerController = null;

    public PlayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        playBinding = FragmentPlayBinding.inflate(getLayoutInflater());
        mRvPlayList = playBinding.rvPlaylist;

        mRvPlayList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mainFragmentViewModel = ViewModelProviders.of(this).get(MainFragmentViewModel.class);
        playListAdapter = new PlayListAdapter<>(playListClickListener);

        mRvPlayList.setAdapter(playListAdapter);

        return playBinding.getRoot();
    }

    // todo base 포함
    public String getViewId() {
        return AppConst.Frag.FRAG_ID_PLAY;
    }

    // todo base 포함
    public void setInterface(IMainFragmentListener listener) {
        mainActListener = listener;
    }

    // todo base 포함
    public void setPlayerCallback(PlayerController controller) {
        if (controller == null) {
            return;
        }

        if (playerController == null) {
            playerController = controller;
            playerController.addPlayerCallback(AppConst.StreamingType.STREAMING_TYPE_GENIE, this);
        }
    }


    private OnPlayListItemClickListener playListClickListener = new OnPlayListItemClickListener() {
        @Override
        public void onClick(Object item, int position, String type, boolean isPlayDepth) {
            switch (type) {
                case AppConst.Genie.PLAYLIST_TYPE_CHART:
                    getStreamingPath(((GenieItem) item).getSong_id());
                    break;
                case AppConst.Genie.PLAYLIST_TYPE_DRIVING:
                    Log.e("kjw333", "here2222 : " + ((GeniePlayListItem) item).getPlm_title());

//                    if (!isPlayDepth) {
//                        getRecommList(((GeniePlayListItem) item).getPlm_seq());
//                    } else {
//                        // 스트리밍 요청
//                        getStreamingPath(((GenieItem)item).getSong_id());
//                    }
                    break;
            }
        }
    };

    public void initPlayerInfoScreen(String type) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (playListAdapter != null) {
                    playListAdapter.updateData(type, GeniePlayListManager.getInstance().getPlayList());
                }

                getStreamingPath(GeniePlayListManager.getInstance().getCurItem().getSong_id());
            }
        }, 1000);

    }


    public void setListName() {
        GeniePlayListManager manager = GeniePlayListManager.getInstance();

//        if (manager != null) {
//            String msg = "";
//
//            int cur = manager.getCurPlayCategory();
//            if (cur == GeniePlayListManager.PLAY_CATEGORY_CHARTPLAY_CATEGORY_POPULAR_RECOMM
//                    || cur == GeniePlayListManager.PLAY_CATEGORY_DRIVING_RECOMM) {
//                msg = getResources().getString(R.string.recomm);
//            } else if (cur == PlayListManager.PLAY_CATEGORY_CHART) {
//                msg = getResources().getString(R.string.chart);
//            } else if (cur == PlayListManager.PLAY_CATEGORY_RECENTLY) {
//                msg = getResources().getString(R.string.recently);
//            } else if (cur == PlayListManager.PLAY_CATEGORY_FAVORITE) {
//                msg = getResources().getString(R.string.favorite);
//            } else if (cur == PlayListManager.PLAY_CATEGORY_MYLIST) {
//                msg = getResources().getString(R.string.mylist);
//            } else if (cur == PlayListManager.PLAY_CATEGORY_SEARCH) {
//                msg = getResources().getString(R.string.search);
//            }
//
//            if (mTvList != null) {
//                mTvList.setText(msg);
//            }
//        }
    }

    private void getStreamingPath(int songId) {
        mainFragmentViewModel.getStreamingPath(songId).observe(this, new Observer<Resource<ArrayList<GenieStreamingItem>>>() {
            @Override
            public void onChanged(Resource<ArrayList<GenieStreamingItem>> item) {
                Log.e("kjw333", "" + item.status);
                if (item.status == Status.SUCCESS) {
                    if (item.data != null) {
                        if (playerController != null) {
                            Log.e("kjw333", "path :" + item.data.get(0).getResource_url());
//                            String customKey = item.data.get(0).get() + item.data.getGETPATHINFO().getMETATYPE() + item.data.getGETPATHINFO().getBITRATE() + item.data.getGETPATHINFO().getPLAYTIME();
//                            Log.e("kjw333", "customKey :" + customKey);
                            playerController.prepare(AppConst.StreamingType.STREAMING_TYPE_GENIE, false, null, item.data.get(0).getResource_url());
                        }
                    }
                } else if (item.status == Status.LOADING) {

                } else {

                }
            }
        });
    }

    // player callback
    @Override
    public void onPrepared(int duration) {
        Log.e("kjw333", "onPrepared");
    }

    @Override
    public void onPlayed() {

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