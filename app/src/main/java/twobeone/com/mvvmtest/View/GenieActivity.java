package twobeone.com.mvvmtest.View;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import twobeone.com.mvvmtest.AppConst;
import twobeone.com.mvvmtest.GlobalStatus;
import twobeone.com.mvvmtest.Interface.IMainFragmentListener;
import twobeone.com.mvvmtest.Player.ExoPlayerService;
import twobeone.com.mvvmtest.R;
import twobeone.com.mvvmtest.View.fragment.MainFragment;
import twobeone.com.mvvmtest.View.fragment.PlayFragment;
import twobeone.com.mvvmtest.databinding.ActivityGenieBinding;

public class GenieActivity extends BaseActivity {

    private Context mContext;

    private ActivityGenieBinding genieBinding;
    private HashMap<String, Fragment> baseFragments;
    private MainFragment mainFragment;
    private PlayFragment playFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        genieBinding = ActivityGenieBinding.inflate(getLayoutInflater());
        setContentView(genieBinding.getRoot());

        baseFragments = new HashMap<String, Fragment>();
        mContext = getApplicationContext();

        playFragment = new PlayFragment();
        changeFunctionView("none", AppConst.Frag.FRAG_ID_MAIN);
    }

    private void changeFunctionView(String hideViewId, String showViewId) {
        if (baseFragments.get(showViewId) != null) {
            return;
        }

        Fragment hideFragment = null;
        hideFragment = baseFragments.get(hideViewId);

        if(baseFragments.get(showViewId) == null){
            initFragment(showViewId);
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (hideFragment != null) {
            fragmentTransaction.hide(hideFragment);
        }


        fragmentTransaction.show(baseFragments.get(showViewId));
        fragmentTransaction.commitAllowingStateLoss();
        try {
            getFragmentManager().executePendingTransactions();
        } catch (Exception e) {
            Log.e("kjw333", "" + e.getMessage());
        }

        GlobalStatus.setCurFragment(showViewId);
    }

    private void initFragment(String viewid) {
        Log.e("kjw333", "viewid : " + viewid);
        if (AppConst.Frag.FRAG_ID_MAIN.equals(viewid)){
            mainFragment = new MainFragment();
            mainFragment.setInterface(mainClickListener);
            baseFragments.put(mainFragment.getViewId(), mainFragment);
        } else if (AppConst.Frag.FRAG_ID_PLAY.equals(viewid)) {

            if (playFragment == null) {
                playFragment = new PlayFragment();
            }

            playFragment.setInterface(mainClickListener);
            baseFragments.put(playFragment.getViewId(), playFragment);
        }

        Fragment fragment = baseFragments.get(viewid);

        FrameLayout container = (FrameLayout) findViewById(R.id.container);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(container.getId(), fragment, viewid);
        fragmentTransaction.hide(fragment);

        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void setPlayerController(ExoPlayerService service) {
        if (playFragment != null) {
            playFragment.setPlayerCallback(service);
        }
    }

    public IMainFragmentListener mainClickListener = new IMainFragmentListener() {
        @Override
        public void onClickMainBtn(String viewid) {

        }

        @Override
        public void setPlayerScreen(boolean moveMain) { // fragment 전환
            if (moveMain) {
                changeFunctionView(GlobalStatus.getCurFragment(), AppConst.Frag.FRAG_ID_PLAY);
                playFragment.initPlayerInfoScreen(AppConst.Genie.PLAYLIST_TYPE_CHART);
            }
        }

        @Override
        public void setMainScreen(boolean moveMain) {

        }

        @Override
        public void showDialog(int type) {

        }

        @Override
        public void dismissDialog(int type) {

        }

        @Override
        public void showDialog(int type, String viewId) {

        }

        @Override
        public void ChangeSplitScreenStatus(boolean isSSOn) {

        }

        @Override
        public void replacePlayableData() {

        }

        @Override
        public void stopRefreshAccount() {

        }

        @Override
        public void initdata() {

        }

        @Override
        public void updatePlayState(int playState) {

        }

        @Override
        public void showMainLoading() {

        }

        @Override
        public void hideMainLoading() {

        }
    };
}