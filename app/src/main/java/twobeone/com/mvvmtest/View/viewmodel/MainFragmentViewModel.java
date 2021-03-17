package twobeone.com.mvvmtest.View.viewmodel;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import twobeone.com.mvvmtest.Model.Genie.GenieItem;
import twobeone.com.mvvmtest.Model.Genie.GeniePlayListItem;
import twobeone.com.mvvmtest.Model.vo.Resource;
import twobeone.com.mvvmtest.Network.GenieRepository;

public class MainFragmentViewModel extends ViewModel {

    private GenieRepository repository = GenieRepository.getInstance();


    public MutableLiveData<Resource<ArrayList<GenieItem>>> getChartList() {
        return repository.getChartList();
    }

    public MutableLiveData<Resource<ArrayList<GeniePlayListItem>>> getDrivingPlayList() {
        return repository.getDrivingPlayList();
    }
}
