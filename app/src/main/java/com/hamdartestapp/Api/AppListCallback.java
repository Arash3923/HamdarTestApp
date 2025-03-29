package com.hamdartestapp.Api;

import com.hamdartestapp.Api.Models.AppModel;

import java.util.List;

public interface AppListCallback {
    void onSuccess(List<AppModel> appList);
    void onFailure(String errorMessage);
}
