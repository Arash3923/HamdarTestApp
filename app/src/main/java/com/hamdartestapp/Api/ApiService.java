package com.hamdartestapp.Api;

import com.hamdartestapp.Api.Models.AppModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("rest/api/public/server/defaultAppList")
    Call<List<AppModel>> getDefaultAppList();
}