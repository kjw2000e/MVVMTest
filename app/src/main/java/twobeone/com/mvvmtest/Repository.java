package twobeone.com.mvvmtest;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import twobeone.com.mvvmtest.Model.MelonDomain;
import twobeone.com.mvvmtest.Model.MelonItem;

public class Repository {
    private static final Repository repository = new Repository();

    private final MutableLiveData<ArrayList<MelonItem>> melonItem = new MutableLiveData<>();

    public static Repository getInstance() {
        return repository;
    }

    private Repository() {
    }

    public MutableLiveData<ArrayList<MelonItem>> getChartList() {

        Call<MelonDomain> call = RetrofitService.getInstance.getMelonChartList();
        call.enqueue(new Callback<MelonDomain>() {
            @Override
            public void onResponse(Call<MelonDomain> call, Response<MelonDomain> response) {
                if (response.isSuccessful()) {
                    MelonDomain melonDomain = response.body();

                    if (melonDomain != null) {
                        // todo 전송
                        melonItem.postValue(melonDomain.getContent());
                    }
               }
            }

            @Override
            public void onFailure(Call<MelonDomain> call, Throwable t) {

            }
        });

        return melonItem;
    }
}
