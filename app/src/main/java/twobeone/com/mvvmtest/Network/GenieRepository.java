package twobeone.com.mvvmtest.Network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import twobeone.com.mvvmtest.AppConst;
import twobeone.com.mvvmtest.Model.Genie.GenieDomain;
import twobeone.com.mvvmtest.Model.Genie.GenieItem;
import twobeone.com.mvvmtest.Model.Genie.GeniePlayListDomain;
import twobeone.com.mvvmtest.Model.Genie.GeniePlayListItem;
import twobeone.com.mvvmtest.Model.vo.Resource;

public class GenieRepository {
    private static final GenieRepository genieRepository = new GenieRepository();

    public static GenieRepository getInstance() {
        return genieRepository;
    }

    private GenieRepository() {
    }


    public Map<String, Integer> getBodyMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("brand", 1);
        map.put("PARAM_PG_SIZE", 100);

        return map;
    }

    public MutableLiveData<Resource<ArrayList<GenieItem>>> getChartList() {

        MutableLiveData<Resource<ArrayList<GenieItem>>> geineItem = new MutableLiveData<>();

        Call<GenieDomain> call = RetrofitService.getInstance.getGenieChart(getBodyMap());
        call.enqueue(new Callback<GenieDomain>() {
            @Override
            public void onResponse(Call<GenieDomain> call, Response<GenieDomain> response) {
                if (response.isSuccessful()) {

                    GenieDomain body = response.body();

                    if (body != null) {
                        if (body.getResultCode().equals("0")) {
                            geineItem.postValue(Resource.success(body.getItems()));
                        } else {
                            geineItem.postValue(Resource.error(body.getResultMessage(), null));
                        }
                    }
                } else {
                    geineItem.postValue(Resource.error(AppConst.Error.RESPONSE_NOT_SUCCESS, null));
                }
            }

            @Override
            public void onFailure(Call<GenieDomain> call, Throwable t) {
                geineItem.postValue(Resource.error(AppConst.Error.RESPONSE_FAIL, null));
            }
        });

        return geineItem;
    }

    public MutableLiveData<Resource<ArrayList<GeniePlayListItem>>> getDrivingPlayList() {
        MutableLiveData<Resource<ArrayList<GeniePlayListItem>>> playlistItem = new MutableLiveData<>();

        Call<GeniePlayListDomain> call = RetrofitService.getInstance.getDrivingPlayList(getBodyMap());
        call.enqueue(new Callback<GeniePlayListDomain>() {
            @Override
            public void onResponse(Call<GeniePlayListDomain> call, Response<GeniePlayListDomain> response) {
                if (response.isSuccessful()) {
                    GeniePlayListDomain body = response.body();
                    if (body != null) {
                        if (body.getResultCode().equals("0")) {
                            playlistItem.postValue(Resource.success(body.getItems()));
                        } else {
                            playlistItem.postValue(Resource.error(body.getResultCode(), null));
                        }
                    }
                } else {
                    playlistItem.postValue(Resource.error(AppConst.Error.RESPONSE_NOT_SUCCESS, null));
                }
            }

            @Override
            public void onFailure(Call<GeniePlayListDomain> call, Throwable t) {
                playlistItem.postValue(Resource.error(AppConst.Error.RESPONSE_FAIL, null));
            }
        });

        return playlistItem;
    }
}
