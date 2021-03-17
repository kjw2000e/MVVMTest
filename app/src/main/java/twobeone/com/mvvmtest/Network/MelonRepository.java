package twobeone.com.mvvmtest.Network;

import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import twobeone.com.mvvmtest.MelonStatus;
import twobeone.com.mvvmtest.Model.Melon.MelonDomain;
import twobeone.com.mvvmtest.Model.Melon.MelonItem;
import twobeone.com.mvvmtest.Model.Melon.MelonStreamingItem;
import twobeone.com.mvvmtest.Model.vo.Resource;
import twobeone.com.mvvmtest.Utils;

public class MelonRepository {
    private static final MelonRepository melonRepository = new MelonRepository();

    public static MelonRepository getInstance() {
        return melonRepository;
    }

    private MelonRepository() {
    }

    public MutableLiveData<Resource<ArrayList<MelonItem>>> getChartList() {
        MutableLiveData<Resource<ArrayList<MelonItem>>> melonItem = new MutableLiveData<>();

        Call<MelonDomain> call = RetrofitService.getInstance.getMelonChartList();
        call.enqueue(new Callback<MelonDomain>() {
            @Override
            public void onResponse(Call<MelonDomain> call, Response<MelonDomain> response) {
                if (response.isSuccessful()) {
                    MelonDomain melonDomain = response.body();

                    if (melonDomain != null) {
                        melonItem.setValue(Resource.success(response.body().getContent()));
                    }
               }
            }

            @Override
            public void onFailure(Call<MelonDomain> call, Throwable t) {
                melonItem.setValue(Resource.error(t.getMessage(), null));
            }
        });

        return melonItem;
    }

    public MutableLiveData<Resource<MelonStreamingItem>> getStreamingItem(MelonItem item) {
        MutableLiveData<Resource<MelonStreamingItem>> streamingItem = new MutableLiveData<>();

        Map<String, String> p = Utils.makeExtraParams();
        //new API
        p.put("ukey", MelonStatus.getMember_key());
        p.put("hwKey", MelonStatus.getPcid());
        p.put("changeSt", MelonStatus.getIsChangeST());
        p.put("sessionId", MelonStatus.getSession_id());
        p.put("cId", item.getTrackId());
        p.put("metaType", MelonStatus.getMetaType());
        p.put("bitrate", MelonStatus.getBitrate());

        MelonStatus.setIsChangeST("N"); //한번 동시 스트리밍 처리 후 초기화
        MelonStatus.setCid(item.getTrackId());
        MelonStatus.setMenuid(item.getMenuId());
        MelonStatus.setBitrate("");
        MelonStatus.setLoggingToken("");

        Call<MelonStreamingItem> call = RetrofitService.getInstance.getMelonStreaming(p);
        call.enqueue(new Callback<MelonStreamingItem>() {
            @Override
            public void onResponse(Call<MelonStreamingItem> call, Response<MelonStreamingItem> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        MelonStreamingItem data = response.body();

                        if (data != null && data.getGETPATHINFO() != null) {
                            MelonStatus.setBitrate(data.getGETPATHINFO().getBITRATE());
                            MelonStatus.setLoggingToken(data.getGETPATHINFO().getLOGGINGTOKEN());
                        } else {
                            MelonStatus.setBitrate("");
                            MelonStatus.setLoggingToken("");
                        }

                        String result = data.getRESULT();
                        Log.e("SG2","startStreaming result : " + result);


                        if ("0".equals(result)) {
                            //로그인 (상품보유)
                            data.setACTIONCODE("100");
                            data.setMESSAGE("");
                            streamingItem.postValue(Resource.success(data));
                        }  else if ("-2002".equals(result)) {
                            //중복 스트리밍
                            streamingItem.postValue(null);
                        } else if (data.getGETPATHINFO() != null) {
                            //미로그인, 미결제, 권리사 등등
                            if(data.getGETPATHINFO().getPATH() == null || "".equals(data.getGETPATHINFO().getPATH())){
                                streamingItem.postValue(Resource.error("not path", null));
                            }else{
                                streamingItem.postValue(Resource.success(data));
                            }
                        } else {
                            streamingItem.postValue(Resource.error("result" + result, null));
                        }
                    }
                } else {
                    streamingItem.postValue(Resource.error("not successful", null));
                }
            }

            @Override
            public void onFailure(Call<MelonStreamingItem> call, Throwable t) {
                streamingItem.postValue(Resource.error(t.getMessage(), null));
                Log.e("kjw333", "onFailure : " + t.getMessage());
            }
        });

        return streamingItem;
    }
}
