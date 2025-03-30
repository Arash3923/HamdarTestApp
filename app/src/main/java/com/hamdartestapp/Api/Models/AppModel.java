package com.hamdartestapp.Api.Models;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class AppModel {
    @SerializedName("id")
    private String id;

    @SerializedName("pkgName")
    private String pkgName;

    @SerializedName("cat")
    private String cat;

    @SerializedName("iconUrl")
    private String iconUrl;

    @SerializedName("appName")
    private String appName;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    public static AppModel deSerialize(String Json)
    {
        AppModel Item = null;
        try {
            Gson gson = new Gson();
            Type listOfMyClassObject = new TypeToken<AppModel>() {
            }.getType();
            Item = gson.fromJson(Json, listOfMyClassObject);

        } catch (Exception e) {

        }
        if (Item == null) Item = new AppModel();
        return Item;
    }
    public static String serialize(AppModel Item)
    {
        Gson gson = new Gson();
        return gson.toJson(Item);

    }
}