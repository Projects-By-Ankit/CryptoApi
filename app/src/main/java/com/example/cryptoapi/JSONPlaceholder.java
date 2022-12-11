package com.example.cryptoapi;


import java.util.List;


import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface JSONPlaceholder {
    @GET("markets")
    Call<List<Post>> getPost(@Query("vs_currency") String curr,
                             @Query("order") String order,
                             @Query("sparkline") String flag);

//    https://api.coingecko.com/api/v3/coins/markets?
//    vs_currency=inr&order=market_cap_desc&per_page=100&page=1&sparkline=false

    @GET("markets")
    Call<List<Post>> getSinglePost(@Query("vs_currency") String curr,
                                   @Query("ids") String ids,
                                   @Query("order") String order,
                                   @Query("sparkline") String flag);

//    https://api.coingecko.com/api/v3/coins/markets?
//    vs_currency=usd&ids=ethereum&order=market_cap_desc&sparkline=false
}
