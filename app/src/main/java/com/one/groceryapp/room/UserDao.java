package com.one.groceryapp.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertdata(UserModel user);

    @Query("SELECT * FROM user_table WHERE email=:email AND password=:password")
    UserModel verifyuser(String email, String password);

    @Query("SELECT * FROM user_table WHERE email=:email")
    UserModel checkuser(String email);

}
