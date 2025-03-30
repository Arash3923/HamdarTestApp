package com.hamdartestapp.Views.ListApp;

import static com.hamdartestapp.DataBase.ConstantDB.database;
import static com.hamdartestapp.Utility.TimeStamp.CheckTimeStampAppList;
import static com.hamdartestapp.Utility.TimeStamp.saveTimeStampListApp;
import static com.hamdartestapp.Utility.Validation.isNetworkConnected;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hamdartestapp.Api.AppListCallback;
import com.hamdartestapp.Api.Models.AppModel;
import com.hamdartestapp.Api.Repository.AppRepository;
import com.hamdartestapp.DataBase.RoomDB;
import com.hamdartestapp.R;
import com.hamdartestapp.Views.ListApp.adapter.ListAppAdapter;
import com.hamdartestapp.databinding.ActivityListAppBinding;

import java.util.ArrayList;
import java.util.List;

public class ListAppActivity extends AppCompatActivity {
    ActivityListAppBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_app);
        getList();
        onClick();

    }

    private void onClick() {
        activityBinding.btn.setOnClickListener(view -> {
            getList();
        });
    }

    private void getList() {
        if (!CheckTimeStampAppList(this))
            getListFromServer();
        else getListFromDatabase();
    }

    private void getListFromDatabase() {
        successGetListApp();
        Toast.makeText(this, "دریافت از دیتابیس", Toast.LENGTH_SHORT).show();
        database = RoomDB.getInstance(ListAppActivity.this);
        List<AppModel> appList = database.mainDao().getAppList();
        setList(appList);
    }

    private void getListFromServer() {
        Toast.makeText(this, "دریافت از سرور", Toast.LENGTH_SHORT).show();
        if (!isNetworkConnected(this)) {
            errorGetListApp();
        } else {
            AppRepository appRepository = new AppRepository();
            appRepository.apiCall(this, new AppListCallback() {
                @Override
                public void onSuccess(List<AppModel> appList) {
                    successGetListApp();
                    saveListAppToDatabase(appList);
                    saveTimeStampListApp(ListAppActivity.this);
                    setList(appList);
                }

                @Override
                public void onFailure(String errorMessage) {
                    activityBinding.message.setText(errorMessage);
                    errorGetListApp();
                }
            });
        }
    }

    private void successGetListApp() {
        activityBinding.recycler.setVisibility(View.VISIBLE);
        activityBinding.btn.setVisibility(View.GONE);
        activityBinding.message.setVisibility(View.GONE);
    }

    private void errorGetListApp() {
        activityBinding.recycler.setVisibility(View.GONE);
        activityBinding.btn.setVisibility(View.VISIBLE);
        activityBinding.message.setVisibility(View.VISIBLE);
    }


    private void saveListAppToDatabase(List<AppModel> appList) {
        database = RoomDB.getInstance(ListAppActivity.this);
        database.mainDao().deleteAllItem();
        database.mainDao().insert(appList);
    }


    private void setList(List<AppModel> appList) {
        RecyclerView.Adapter adapter;
        activityBinding.recycler.setNestedScrollingEnabled(false);
        activityBinding.recycler.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new ListAppAdapter(this, (ArrayList<AppModel>) appList);
        activityBinding.recycler.setAdapter(adapter);
    }

}