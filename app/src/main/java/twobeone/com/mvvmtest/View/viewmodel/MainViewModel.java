package twobeone.com.mvvmtest.View.viewmodel;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import twobeone.com.mvvmtest.Model.Melon.MelonItem;
import twobeone.com.mvvmtest.Model.Melon.MelonStreamingItem;
import twobeone.com.mvvmtest.Model.vo.Resource;
import twobeone.com.mvvmtest.Network.MelonRepository;

public class MainViewModel extends ViewModel {

    public MutableLiveData<Integer> data = new MutableLiveData<>();

    private MelonRepository melonRepository = MelonRepository.getInstance();

    public void init() {
        data.setValue(0);
    }

    public void increase() {
        data.postValue(data.getValue() + 1);
    }

    public void decrease() {
        data.postValue(data.getValue() - 1);
    }


    public LiveData<Resource<ArrayList<MelonItem>>> getChartList () {
        return melonRepository.getChartList();
    }

    public LiveData<Resource<MelonStreamingItem>> getStreamingInfo (MelonItem item) {
        return melonRepository.getStreamingItem(item);
    }
}
