package com.hamdartestapp.Views.ListApp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hamdartestapp.Api.AppListCallback;
import com.hamdartestapp.Api.Models.AppModel;
import com.hamdartestapp.Api.Repository.AppRepository;
import com.hamdartestapp.R;
import com.hamdartestapp.Views.ListApp.adapter.ListAppAdapter;
import com.hamdartestapp.databinding.ActivityListAppBinding;

import java.util.ArrayList;
import java.util.List;

public class ListAppActivity extends AppCompatActivity  {
ActivityListAppBinding activityBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_app);
        getList();

    }

    private void getList() {
        AppRepository appRepository = new AppRepository();
        appRepository.apiCall(this, new AppListCallback() {
            @Override
            public void onSuccess(List<AppModel> appList) {

                runOnUiThread(() -> {
                    setList(appList);
                });
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

    private void setList(List<AppModel> appList) {
        RecyclerView.Adapter adapter;
        activityBinding.recycler.setNestedScrollingEnabled(false);
        activityBinding.recycler.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new ListAppAdapter(this, (ArrayList<AppModel>) appList);
        activityBinding.recycler.setAdapter(adapter);
    }

}