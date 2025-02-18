package com.one.groceryapp.utils;

import com.one.groceryapp.R;
import com.one.groceryapp.model.CategoriesModel;
import com.one.groceryapp.model.DemoModel;
import com.one.groceryapp.model.FeatureProductModel;

import java.util.ArrayList;

public class Constants {

    public static ArrayList<FeatureProductModel> productModels=featureProductModelArrayList();

    public static ArrayList<FeatureProductModel> arrayList =productModels;

    public static ArrayList<FeatureProductModel> featureProductModelArrayList() {
        ArrayList<FeatureProductModel> featureProductModelArrayList = new ArrayList<>();
        featureProductModelArrayList.add(new FeatureProductModel(8, "Fresh Peach", "dozen", R.drawable.peach, false, 1, false, false, "", false,R.drawable.apple_bg));
        featureProductModelArrayList.add(new FeatureProductModel(7, "Avacoda", "2.0 lbs", R.drawable.aocado, false, 1, true, false, "", false,R.drawable.avacoda_bg));
        featureProductModelArrayList.add(new FeatureProductModel(9, "Pineapple", "1.50 lbs", R.drawable.pineapplepieces, false, 1, false, false, "", false,R.drawable.pineapple_bg));
        featureProductModelArrayList.add(new FeatureProductModel(7, "Black Grapes", "5.0 lbs", R.drawable.grapes, false, 1, false, true, "-20%", false,R.drawable.graphs_bg));
        featureProductModelArrayList.add(new FeatureProductModel(2, "Pomegranate", "1.50 lbs", R.drawable.pomegranate, false, 1, true, false, "", false,R.drawable.pomegranate_bg));
        featureProductModelArrayList.add(new FeatureProductModel(3, "Fresh B roccoli", "1 kg", R.drawable.greenfreshbroccoli, false, 1, false, false, "", false,R.drawable.broccoli_bg));
        return featureProductModelArrayList;
    }
    public static ArrayList<CategoriesModel> categoriesModelArrayList() {
        ArrayList<CategoriesModel> categoriesModelArrayList = new ArrayList<>();
        categoriesModelArrayList.add(new CategoriesModel("Vegetables", R.drawable.leaf, R.color.vegetables_green));
        categoriesModelArrayList.add(new CategoriesModel("Fruits", R.drawable.apple, R.color.fruit_apple));
        categoriesModelArrayList.add(new CategoriesModel("Beverages", R.drawable.drinks, R.color.beverage_cold_drinks_yellow));
        categoriesModelArrayList.add(new CategoriesModel("Grocery", R.drawable.grocery, R.color.grocery_purple));
        categoriesModelArrayList.add(new CategoriesModel("Edible oil", R.drawable.oil, R.color.oil_blue));
        categoriesModelArrayList.add(new CategoriesModel("Household", R.drawable.household, R.color.house_hold_pink));
        return categoriesModelArrayList;
    }

    public static ArrayList<DemoModel> get_home_title() {
        ArrayList<DemoModel> title_home_list = new ArrayList<>();
        DemoModel data1 = new DemoModel("Categories");
        title_home_list.add(data1);
        DemoModel data2 = new DemoModel("Featured products");
        title_home_list.add(data2);
        return title_home_list;
    }
}
