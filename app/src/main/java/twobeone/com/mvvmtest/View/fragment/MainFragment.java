package twobeone.com.mvvmtest.View.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import twobeone.com.mvvmtest.AppConst;
import twobeone.com.mvvmtest.Interface.OnPlayListItemClickListener;
import twobeone.com.mvvmtest.Model.Genie.GenieItem;
import twobeone.com.mvvmtest.Model.Genie.GeniePlayListItem;
import twobeone.com.mvvmtest.Model.vo.Resource;
import twobeone.com.mvvmtest.Model.vo.Status;
import twobeone.com.mvvmtest.View.adapter.PlayListAdapter;
import twobeone.com.mvvmtest.View.adapter.TabListAdapter;
import twobeone.com.mvvmtest.View.viewmodel.MainFragmentViewModel;
import twobeone.com.mvvmtest.databinding.FragmentMainBinding;

/**
 * made by jiwon
 */
public class MainFragment extends Fragment {
    private FragmentMainBinding mainBinding;
    private TabListAdapter tabListAdapter;
    private ListView mTabList;
    private MainFragmentViewModel mainFragmentViewModel;
    private RecyclerView mRvPlayList;
    private PlayListAdapter<GenieItem, GeniePlayListItem> playListAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainBinding = FragmentMainBinding.inflate(getLayoutInflater());
        mainFragmentViewModel = ViewModelProviders.of(this).get(MainFragmentViewModel.class);

        tabListAdapter = new TabListAdapter(getContext());
        mTabList = mainBinding.lvTabList;
        mTabList.setAdapter(tabListAdapter);
        mTabList.setItemChecked(1, true);
        mTabList.setOnItemClickListener(tabListClickListener);

        mRvPlayList = mainBinding.rvPlaylist;
        mRvPlayList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        playListAdapter = new PlayListAdapter<>(playListClickListener);

        mRvPlayList.setAdapter(playListAdapter);

        return mainBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    public String getViewId() {
        return AppConst.Frag.FRAG_ID_MAIN;
    }

    private void getChartList() {
        mainFragmentViewModel.getChartList().observe(this, new Observer<Resource<ArrayList<GenieItem>>>() {
            @Override
            public void onChanged(Resource<ArrayList<GenieItem>> item) {
                Log.e("kjw333", "status : " + item.status);
                if (item.status == Status.SUCCESS) {
                    if (item.data != null && item.data.size() != 0) {
                        playListAdapter.updateData(AppConst.Genie.PLAYLIST_TYPE_GENIE_CHART, true, item.data, null);
                    }
                } else if (item.status == Status.LOADING) {

                } else {

                }
            }
        });
    }

    private void getDrivingPlayList() {
        mainFragmentViewModel.getDrivingPlayList().observe(this, new Observer<Resource<ArrayList<GeniePlayListItem>>>() {
            @Override
            public void onChanged(Resource<ArrayList<GeniePlayListItem>> item) {
                Log.e("kjw333", "status : " + item.status);
                if (item.status == Status.SUCCESS) {
                    playListAdapter.updateData(AppConst.Genie.PLAYLIST_TYPE_GENIE_DRIVING, false, null, item.data);
                } else if (item.status == Status.LOADING) {
                } else {

                }
            }
        });
    }

    private OnPlayListItemClickListener playListClickListener = new OnPlayListItemClickListener() {
        @Override
        public void onClick(Object item, String type, boolean isPlayDepth) {
            switch (type) {
                case AppConst.Genie.PLAYLIST_TYPE_GENIE_CHART:
                    Log.e("kjw333", "here1111 : " + ((GenieItem)item).getSong_name());
                    break;
                case AppConst.Genie.PLAYLIST_TYPE_GENIE_DRIVING:
                    Log.e("kjw333", "here2222 : " + ((GeniePlayListItem)item).getPlm_title());
                    break;
            }
        }
    };


    AdapterView.OnItemClickListener tabListClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) { // 재생 리스트
                // type 변경

            } else if (position == 1) { // 차트
                // type 변경
                getChartList();
            } else if (position == 2) { // 드라이빙
                getDrivingPlayList();
            }
        }
    };
}