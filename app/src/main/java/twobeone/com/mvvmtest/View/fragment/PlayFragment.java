package twobeone.com.mvvmtest.View.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import twobeone.com.mvvmtest.R;
import twobeone.com.mvvmtest.databinding.FragmentPlayBinding;

/**
 * mady by jiwon
 */
public class PlayFragment extends Fragment {
    private FragmentPlayBinding playBinding;

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



        return playBinding.getRoot();
    }
}