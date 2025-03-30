package com.hamdartestapp.DataBase;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.hamdartestapp.Api.Models.AppModel;


import java.util.List;


@Dao
public interface MainDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<AppModel> mainData);


    @Query("SELECT * FROM AppList")
    List<AppModel> getAppList();

    @Query("SELECT COUNT(*) FROM AppList")
    Integer getSizeAppList();

    @Query("DELETE FROM AppList")
    abstract void deleteAllItem();



}