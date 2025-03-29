package com.hamdartestapp.Views.ListApp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.hamdartestapp.Api.AppListCallback;
import com.hamdartestapp.Api.Models.AppModel;
import com.hamdartestapp.Api.Repository.AppRepository;
import com.hamdartestapp.R;

import java.util.List;

public class ListAppActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_app);
        getList();

    }

    private void getList() {
        AppRepository appRepository = new AppRepository();
        appRepository.apiCall(this, new AppListCallback() {
            @Override
            public void onSuccess(List<AppModel> appList) {
                for (int i = 0; i <appList.size() ; i++) {
                    Toast.makeText(ListAppActivity.this, appList.get(i).getAppName()+"", Toast.LENGTH_SHORT).show();
                }
//                runOnUiThread(() -> {
//                });
            }

            @Override
            public void onFailure(String errorMessage) {

//                runOnUiThread(() -> {
//                    // نمایش خطا به کاربر
//                    Toast.makeText(ListAppActivity.this, "خطا در دریافت لیست برنامه‌ها", Toast.LENGTH_SHORT).show();
//                });
            }
        });
    }

}