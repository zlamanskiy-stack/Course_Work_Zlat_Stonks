package com.example.course_work_zlat_stonks;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public class RetrofitService {
    @GET("api/stats/{ticker}")
    Call<StockResponse> getStockData(@Path("ticker") String ticker);
}
