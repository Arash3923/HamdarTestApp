package com.hamdartestapp.Api.Repository;

import android.content.Context;
import android.util.Log;

import com.hamdartestapp.Api.ApiService;
import com.hamdartestapp.Api.AppListCallback;
import com.hamdartestapp.Api.Models.AppModel;
import com.hamdartestapp.Api.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepository {

    private  ApiService apiService;
    private AppListCallback callback;
    Context context;



    public void apiCall(Context context,AppListCallback callback){
        this.context=context;
        this.callback=callback;
        this.apiService = RetrofitClient.getApiService();
        fetchDefaultAppList();
    }



    public void fetchDefaultAppList() {
        Call<List<AppModel>> call = apiService.getDefaultAppList();

        call.enqueue(new Callback<List<AppModel>>() {
            @Override
            public void onResponse(Call<List<AppModel>> call, Response<List<AppModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AppModel> appList = response.body();
                    if (callback != null) {
                        callback.onSuccess(appList);
                    }
                } else {
                    String errorMsg = "Response not successful: " + response.code();
                    if (callback != null) {
                        callback.onFailure(errorMsg);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<AppModel>> call, Throwable t) {
                String errorMsg = "Failed to fetch app list: " + t.getMessage();
                if (callback != null) {
                    callback.onFailure(errorMsg);
                }
            }
        });
    }
}