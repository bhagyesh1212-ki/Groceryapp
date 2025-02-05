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

    @Query("SELECT * FROM address_table WHERE id=:id")
    List<AddressModel> getaddressbyid(int id);

    @Query("SELECT * FROM address_table")
    List<AddressModel> getaddressforfinalpayment();

    @Query("SELECT * FROM cards_table")
    List<CardModel> getcardforfinalpayment();

    @Query("SELECT COUNT(name) FROM address_table")
    int getDataCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insercard(List<CardModel> cardModel);

    @Query("SELECT * FROM cards_table")
    List<CardModel> getallcards();

    @Query("SELECT * FROM cards_table WHERE id=:id")
    List<CardModel> getcardbyid(int id);

    @Query("SELECT COUNT(cardholder) FROM cards_table")
    int getCardDataCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inserttransaction(List<TransactionModel> transactionModelList);

    @Query("SELECT * FROM transaction_table")
    List<TransactionModel> getalltransaction();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertmyorder(List<MyOrderModel> myOrderModelList);

    @Query("SELECT * FROM myorder_table")
    List<MyOrderModel> getallmyorder();

//    @Query("SELECT price FROM myorder_table")
//    List<MyOrderModel> getallprice();

}

//    UserModel verifyuser(String email, String password);
//    UserModel checkuser(String email);
//@Query("SELECT email FROM user_table")
//String getemail();


//    @Query("UPDATE cards_table SET cardnumber=:cardnumber, cardholder=:cardholder, carddate=:carddate, cardcvv=:cardcvv WHERE id=:id")
//    void updatecard(String cardnumber, String cardholder, String carddate, String cardcvv, int id);
//
//    @Query("UPDATE address_table SET name=:name ,city=:city ,zip=:zip ,mobile_number=:mobile_number ,address=:address WHERE id=:id")
//    void updateaddress(String name, String city, String zip, String mobile_number, String address, int id);