package com.example.laboratorio10;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostService {
    String API_ROUTE= "/posts";
    @GET(API_ROUTE)
    Call<List<Post>> getPost();

    String API_ROUTE_ID= "/posts/{id}";
    @GET(API_ROUTE_ID)
    Call<List<Post>> getPostById(@Path("id") String id);

}
