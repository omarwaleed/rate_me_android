package com.example.menrit.rate.util;


import java.util.List;

import com.example.menrit.rate.model.*;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface PublicApiRoutes {

    @POST("/api/session/create")
    @FormUrlEncoded
    void login(@Field("params[username]") String username, @Field("params[password]") String password,
               Callback<User> callback);
    @GET("/api/post/index")
    void getPosts(Callback<List<Post>> callback);
}