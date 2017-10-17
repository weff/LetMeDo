package com.jtsoft.letmedo.retrofit;

import com.jtsoft.letmedo.common.ShowApiResponse;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by qrh on 2017/3/15 0015.
 */

public interface IFileLoad {
    //@GET("/apk/{fileName}")
    @GET()
    Call<ResponseBody> loadFile(@Url String url/*,@Path("fileName") String fileName*/);

    @POST()
    Call<ShowApiResponse> uploadFile(@Url() String url, @Body RequestBody body);

}
