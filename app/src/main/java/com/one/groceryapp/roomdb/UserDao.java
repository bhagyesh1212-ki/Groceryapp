package com.one.groceryapp.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.one.groceryapp.model.AddressModel;

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

    @Query("SELECT COUNT(name) FROM address_table")
    int getDataCount();

//    @Query("UPDATE address_table SET name=:newname ,city=:newcity ,zip=:newzip ,mobile_number=:newphone ,address=:address WHERE id=:id")
//    void updateaddress(String newname ,String newcity,String newzip,String newphone,String address, int id);

}


//    UserModel verifyuser(String email, String password);
//    UserModel checkuser(String email);
//@Query("SELECT email FROM user_table")
//String getemail();