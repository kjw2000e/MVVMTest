package twobeone.com.mvvmtest.Network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import twobeone.com.mvvmtest.AppConst;
import twobeone.com.mvvmtest.Model.Genie.GenieDomain;
import twobeone.com.mvvmtest.Model.Genie.GenieItem;
import twobeone.com.mvvmtest.Model.Genie.GeniePlayListDomain;
import twobeone.com.mvvmtest.Model.Genie.GeniePlayListItem;
import twobeone.com.mvvmtest.Model.Genie.GenieRecommDomain;
import twobeone.com.mvvmtest.Model.Genie.GenieStreamingDomain;
import twobeone.com.mvvmtest.Model.Genie.GenieStreamingItem;
import twobeone.com.mvvmtest.Model.vo.Resource;

public class GenieRepository {
    private static final String RESPONSE_SUCCESS = "0";
    private static final GenieRepository genieRepository = new GenieRepository();

    public static GenieRepository getInstance() {
        return genieRepository;
    }

    private GenieRepository() {
    }


    public Map<String, Integer> getDefaultBodyMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put(AppConst.Genie.PARAM_BRAND, 1);
        map.put(AppConst.Genie.PARAM_PG_SIZE, 100);

        return map;
    }

    public MutableLiveData<Resource<ArrayList<GenieItem>>> getChartList() {

        MutableLiveData<Resource<ArrayList<GenieItem>>> geineItem = new MutableLiveData<>();

        Call<GenieDomain> call = RetrofitService.getInstance.getGenieChart(getDefaultBodyMap());
        call.enqueue(new Callback<GenieDomain>() {
            @Override
            public void onResponse(Call<GenieDomain> call, Response<GenieDomain> response) {
                if (response.isSuccessful()) {

                    GenieDomain body = response.body();

                    if (body != null) {
                        if (body.getResultCode().equals(RESPONSE_SUCCESS)) {
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

        Call<GeniePlayListDomain> call = RetrofitService.getInstance.getDrivingPlayList(getDefaultBodyMap());
        call.enqueue(new Callback<GeniePlayListDomain>() {
            @Override
            public void onResponse(Call<GeniePlayListDomain> call, Response<GeniePlayListDomain> response) {
                if (response.isSuccessful()) {
                    GeniePlayListDomain body = response.body();
                    if (body != null) {
                        if (body.getResultCode().equals(RESPONSE_SUCCESS)) {
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

    public MutableLiveData<Resource<ArrayList<GenieItem>>> getRecommList(int playlistId) {
        MutableLiveData<Resource<ArrayList<GenieItem>>> item = new MutableLiveData<>();
        Map<String, Integer> map = new HashMap<>();
        map.put(AppConst.Genie.PARAM_BRAND, 1);
        map.put(AppConst.Genie.PARAM_PLAYLIST_ID, playlistId);

        Call<GenieRecommDomain> call = RetrofitService.getInstance.getRecommendSongList(map);
        call.enqueue(new Callback<GenieRecommDomain>() {
            @Override
            public void onResponse(Call<GenieRecommDomain> call, Response<GenieRecommDomain> response) {
                if (response.isSuccessful()) {
                    GenieRecommDomain body = response.body();
                    if (body != null) {
                        if (body.getResultCode().equals(RESPONSE_SUCCESS)) {
                            item.postValue(Resource.success(body.getInfo().getSong().getItems()));
                        } else {
                            item.postValue(Resource.error(body.getResultCode(), null));
                        }
                    } else {
                        item.postValue(Resource.error("body is null", null));
                    }
                } else {
                    item.postValue(Resource.error(AppConst.Error.RESPONSE_FAIL, null));
                }
            }

            @Override
            public void onFailure(Call<GenieRecommDomain> call, Throwable t) {
                item.postValue(Resource.error(AppConst.Error.RESPONSE_FAIL, null));
            }
        });

        return item;
    }

    public MutableLiveData<Resource<ArrayList<GenieStreamingItem>>> getStreamingPath (int songId) {
        MutableLiveData<Resource<ArrayList<GenieStreamingItem>>> item = new MutableLiveData<>();

        Map<String, Integer> params = new HashMap<>();
        params.put(AppConst.Genie.PARAM_BRAND, 1);
        params.put(AppConst.Genie.PARAM_SONG_ID, songId);


        Call<GenieStreamingDomain> call = RetrofitService.getInstance.getStreamingPath(params);
        call.enqueue(new Callback<GenieStreamingDomain>() {
            @Override
            public void onResponse(Call<GenieStreamingDomain> call, Response<GenieStreamingDomain> response) {
                if (response.isSuccessful()) {
                    GenieStreamingDomain body = response.body();
                    if (body != null) {
                        if (body.getResultCode().equals(RESPONSE_SUCCESS)) {
                            item.postValue(Resource.success(body.getItems()));
                        } else {
                            item.postValue(Resource.error(body.getResultCode(), null));
                        }
                    } else {

                    }
                } else {
                    item.postValue(Resource.error(AppConst.Error.RESPONSE_NOT_SUCCESS, null));
                }
            }

            @Override
            public void onFailure(Call<GenieStreamingDomain> call, Throwable t) {
                item.postValue(Resource.error(AppConst.Error.RESPONSE_FAIL, null));
            }
        });

        return item;
    }
}
