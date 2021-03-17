package twobeone.com.mvvmtest.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import twobeone.com.mvvmtest.AppConst;
import twobeone.com.mvvmtest.GlobalStatus;
import twobeone.com.mvvmtest.R;
import twobeone.com.mvvmtest.View.fragment.MainFragment;
import twobeone.com.mvvmtest.databinding.ActivityGenieBinding;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.HashMap;

public class GenieActivity extends AppCompatActivity {

    private Context mContext;

    private ActivityGenieBinding genieBinding;
    private HashMap<String, Fragment> baseFragments;
    private MainFragment mainFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        genieBinding = ActivityGenieBinding.inflate(getLayoutInflater());
        setContentView(genieBinding.getRoot());

        baseFragments = new HashMap<String, Fragment>();
        mContext = getApplicationContext();


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
//        fragmentTransaction.hide(hideFragment);

        fragmentTransaction.show(baseFragments.get(showViewId));
        fragmentTransaction.commitAllowingStateLoss();
        try {
            getFragmentManager().executePendingTransactions();
        } catch (Exception e) {
            Log.e("kjw333", "" + e.getMessage());
        }
    }

    private void initFragment(String viewid) {
        Log.e("kjw333", "viewid : " + viewid);
        if(AppConst.Frag.FRAG_ID_MAIN.equals(viewid)){
            mainFragment = new MainFragment();
            baseFragments.put(mainFragment.getViewId(), mainFragment);
        }

        Fragment fragment = baseFragments.get(viewid);

        FrameLayout container = (FrameLayout) findViewById(R.id.container);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(container.getId(), fragment, viewid);
        fragmentTransaction.hide(fragment);

        fragmentTransaction.commitAllowingStateLoss();
    }
}