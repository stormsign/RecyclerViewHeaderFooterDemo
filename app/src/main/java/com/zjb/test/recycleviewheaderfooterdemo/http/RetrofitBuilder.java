package com.zjb.test.recycleviewheaderfooterdemo.http;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by khb on 2017/3/14.
 */
public class RetrofitBuilder  {

    private final Retrofit retrofit;
    private final MovieRequestService service;
    private Call<MovieResponse> movieInfoCall;

    public RetrofitBuilder(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://op.juhe.cn/onebox/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MovieRequestService.class);
    }

    public void getMovie(String name){
        movieInfoCall = service.getMovieInfo("json", name, "39cabbbfad378ad59b2bbd0c9cb17897");
        movieInfoCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.i("Log", response.body().toString());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getMoviePost(String name){
        Call<MovieResponse> movieInfoPost = service.getMovieInfoPost("json", name, "39cabbbfad378ad59b2bbd0c9cb17897");
        movieInfoPost.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.i("Log", "POST  "+response.body().toString());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void getMoviePost2(String name){
        Map<String, Object> map = new HashMap<>();
        map.put("dtype", "json");
        map.put("q", name);
        map.put("key", "39cabbbfad378ad59b2bbd0c9cb17897");
        Call<MovieResponse> movieInfoPost = service.getMovieInfoPost2(map);
        movieInfoPost.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.i("Log", "POST  "+response.body().toString());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

}
