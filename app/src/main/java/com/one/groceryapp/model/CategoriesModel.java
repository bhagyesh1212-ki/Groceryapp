package com.one.groceryapp.model;

import java.io.Serializable;

public class CategoriesModel implements Serializable {
    private int categoryimage;
    private String categoryname;
    private int categorycolor;

    public CategoriesModel(String categoryname, int categoryimage, int categorycolor) {
        this.categoryname = categoryname;
        this.categoryimage = categoryimage;
        this.categorycolor = categorycolor;
    }

    public int getCategorycolor() {
        return categorycolor;
    }

    public void setCategorycolor(int categorycolor) {
        this.categorycolor = categorycolor;
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
