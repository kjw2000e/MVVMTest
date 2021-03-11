package twobeone.com.mvvmtest;

import android.util.Log;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import twobeone.com.mvvmtest.Model.MelonItem;

public class MainViewModel extends ViewModel {

    public MutableLiveData<Integer> data = new MutableLiveData<>();

    private Repository repository = Repository.getInstance();

    public void init() {
        data.setValue(0);
    }

    public void increase() {
        data.postValue(data.getValue() + 1);
    }

    public void decrease() {
        data.postValue(data.getValue() - 1);
    }


    public MutableLiveData<ArrayList<MelonItem>>  getChartList () {
        return repository.getChartList();
    }
}
