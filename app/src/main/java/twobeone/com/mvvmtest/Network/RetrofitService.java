package twobeone.com.mvvmtest.Network;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import twobeone.com.mvvmtest.Model.Genie.GenieDomain;
import twobeone.com.mvvmtest.Model.Genie.GeniePlayListDomain;
import twobeone.com.mvvmtest.Model.Genie.GenieRecommDomain;
import twobeone.com.mvvmtest.Model.Melon.MelonDomain;
import twobeone.com.mvvmtest.Model.Melon.MelonStreamingItem;


public interface RetrofitService {
    String TAG = "RetrofitService";
    int TIME_OUT_MILLISECONDS = 15 * 1000;    //10초


    OkHttpClient client = new OkHttpClient()
            .newBuilder()
            .followRedirects(true)
            .followSslRedirects(true)
            .callTimeout(TIME_OUT_MILLISECONDS, TimeUnit.MILLISECONDS)
            .connectTimeout(TIME_OUT_MILLISECONDS, TimeUnit.MILLISECONDS)
            .readTimeout(TIME_OUT_MILLISECONDS, TimeUnit.MILLISECONDS)
            .writeTimeout(TIME_OUT_MILLISECONDS, TimeUnit.MILLISECONDS)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.popmediacloud.com:8081/cloud/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitService getInstance = retrofit.create(RetrofitService.class);



    // http://www.popmediacloud.com:8081/cloud/mss/realtimechart/req

    // 지니
    @POST("mss/realtimechart/req")
    Call<GenieDomain> getGenieChart(@Body Map<String, Integer> params);

    @POST("/cloud/mss/drivingchart/req")
    Call<GeniePlayListDomain> getDrivingPlayList(@Body Map<String, Integer> params);

    // 드라이빙 플레이 리스트 songlist api
    @POST("/cloud/mss/recommendsongschart/req")
    Call<GenieRecommDomain> getRecommendSongList(@Body Map<String, Integer> params);


    // 멜론
    @GET("api/melon/newChartList")
    Call<MelonDomain> getMelonChartList();

    @POST("api/melon/newStreaming")
    Call<MelonStreamingItem> getMelonStreaming(@Body Map<String, String> params);
}