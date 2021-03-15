package twobeone.com.mvvmtest;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import twobeone.com.mvvmtest.Model.MelonDomain;
import twobeone.com.mvvmtest.Model.MelonStreamingItem;


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
            .baseUrl("http://www.popmediacloud.com:8081/cloud/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitService getInstance = retrofit.create(RetrofitService.class);

    @GET("melon/newChartList")
    Call<MelonDomain> getMelonChartList();

    @POST("melon/newStreaming")
    Call<MelonStreamingItem> getMelonStreaming(@Body Map<String, String> params);
}