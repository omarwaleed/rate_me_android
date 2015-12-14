package util;


import java.util.List;

import model.*;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface PublicApiRoutes {

    @POST("/sessions")
    @FormUrlEncoded
    void login(@Field("params[username]") String username, @Field("params[password]") String password,
               Callback<User> callback);
    @GET("/products")
    void getPosts(Callback<List<Post>> callback);
}
