package com.one.groceryapp.model;

public class CategoriesModel {
    private int categoryimage;
    private String categoryname;

    public CategoriesModel(String categoryname, int categoryimage) {
        this.categoryname = categoryname;
        this.categoryimage = categoryimage;
    }

    public int getCategoryimage() {
        return categoryimage;
    }

    public void setCategoryimage(int categoryimage) {
        this.categoryimage = categoryimage;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}
