package com.one.groceryapp.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.one.groceryapp.model.FeatureProductModel;

import java.util.ArrayList;

public class SharedPreferencesUtils {
    SharedPreferences preferences;
    public final static String LibraryArrayList = "LibraryArrayList";
    public SharedPreferencesUtils(Context context) {
        preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public void setLibraryArrayList(ArrayList<FeatureProductModel> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        preferences.edit().putString(LibraryArrayList, json).apply();
    }

    public ArrayList<FeatureProductModel> getLibraryArrayList() {
        return new Gson().fromJson(preferences.getString(LibraryArrayList, null), new TypeToken<ArrayList<FeatureProductModel>>() {
        }.getType());
    }
}
