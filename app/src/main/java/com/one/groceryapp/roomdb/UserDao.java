package com.one.groceryapp.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.one.groceryapp.model.FeatureProductModel;

import java.util.ArrayList;

@Dao
public interface UserDao {

    @Insert
    void insertdata(UserModel user);

    @Query("UPDATE user_table SET password=:newpassword WHERE password=:password;")
    void update(String newpassword, String password);

//    UserModel verifyuser(String email, String password);

    @Query("SELECT password FROM user_table ORDER BY id DESC LIMIT 1")
    String getpassword();


    @Query("SELECT phonenumber FROM user_table ORDER BY id DESC LIMIT 1")
    String getphonenumber();

//    UserModel checkuser(String email);

    @Query("SELECT email FROM user_table")
    String getemail();
}
