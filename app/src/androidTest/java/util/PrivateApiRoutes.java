package util;


import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.PATCH;
import retrofit.http.Path;

public interface PrivateApiRoutes {

    @PATCH("/products/{product_id}/buy")
    void patchProductBuy(@Path("product_id") long productId, Callback<Response> callback);
}
