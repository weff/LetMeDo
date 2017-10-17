package com.jtsoft.letmedo.retrofit;

import com.jtsoft.letmedo.common.ShowApiResponse;
import com.jtsoft.letmedo.model.PayOrderBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wy on 2016/11/21.
 */

public interface RetrofitApi {

    @GET(BizInterface.PAYORDER)
    Observable<ShowApiResponse<PayOrderBean>> getPayOrder(@Query("orderId") Integer orderId, @Query("payType") int payType,@Query("token") String token);

}
