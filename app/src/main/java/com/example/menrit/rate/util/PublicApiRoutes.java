package com.example.menrit.rate.util;


import java.util.Date;
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
    void login(@Field("new_session[username]") String username, @Field("new_session[password]") String password,Callback<User> callback);
    @GET("/api/post/index")
    void getPosts(Callback<List<Post>> callback);
    @POST("/api/post/create")
    @FormUrlEncoded
    void newPost(@Field("new_post[name]") String name, @Field("new_post[genre]") String type, Callback<Post> callback);
    @POST("/api/session/create_user")
    @FormUrlEncoded
    void newUser(@Field("nu[username]") String username,@Field("nu[email]") String email,@Field("nu[password]") String password,@Field("nu[gender]") String gender, Callback<User> callback);
}
