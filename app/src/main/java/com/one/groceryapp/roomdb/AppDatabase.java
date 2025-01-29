package com.one.groceryapp.roomdb;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.one.groceryapp.model.AddressModel;
import com.one.groceryapp.model.FeatureProductModel;

@Database(entities = {UserModel.class, AddressModel.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static final String databaseName = "user_database";
    public abstract UserDao userDao();
    private static volatile AppDatabase INSTANCE;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Address_table` (`zip` TEXT ," + "`country` TEXT ," + "`address` TEXT," + " `city` TEXT ," + "`name` TEXT," + "`id` INTEGER NOT NULL," + "`mobile_number` TEXT," + "`email` TEXT)");
        }
    };

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, databaseName)
                            .allowMainThreadQueries()
                            .addMigrations(MIGRATION_1_2)
                            .build();
                    Log.d("TAG", "New instance created...");
                }
            }
        }
        return INSTANCE;
    }
}
