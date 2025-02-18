package com.one.groceryapp.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.one.groceryapp.model.AddressModel;
import com.one.groceryapp.model.CardModel;
import com.one.groceryapp.model.MyOrderModel;
import com.one.groceryapp.model.TransactionModel;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertdata(UserModel user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAddress(List<AddressModel> addressModel);

    @Query("UPDATE user_table SET password=:newpassword WHERE password=:password")
    void update(String newpassword, String password);

    @Query("SELECT password FROM user_table ORDER BY id DESC LIMIT 1")
    String getpassword();

    @Query("SELECT phonenumber FROM user_table ORDER BY id DESC LIMIT 1")
    String getphonenumber();

    @Query("SELECT * FROM address_table")
    List<AddressModel> getaddress();

    @Query("SELECT COUNT(name) FROM address_table")
    int getDataCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insercard(List<CardModel> cardModel);

    @Query("SELECT * FROM cards_table")
    List<CardModel> getallcards();

    @Query("SELECT COUNT(cardholder) FROM cards_table")
    int getCardDataCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertmyorder(List<MyOrderModel> myOrderModelList);

    @Query("SELECT * FROM myorder_table")
    List<MyOrderModel> getallmyorder();
}