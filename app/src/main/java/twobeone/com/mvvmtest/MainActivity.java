package twobeone.com.mvvmtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import twobeone.com.mvvmtest.Model.MelonItem;
import twobeone.com.mvvmtest.databinding.ActivityMainBinding;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MainViewModel mainViewModel;
    private RecyclerView mRv;
    private ChartAdapter mChartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.init();

        mRv = activityMainBinding.rv;
        mRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mChartAdapter = new ChartAdapter();
        mRv.setAdapter(mChartAdapter);

        activityMainBinding.getlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.getChartList().observe(MainActivity.this, new Observer<ArrayList<MelonItem>>() {
                    @Override
                    public void onChanged(ArrayList<MelonItem> melonItems) {
                        mChartAdapter.UpdateData(melonItems);
                    }
                });
            }
        });
    }
}