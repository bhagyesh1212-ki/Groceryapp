package com.one.groceryapp.roomdb;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.one.groceryapp.model.AddressModel;
import com.one.groceryapp.model.CardModel;
import com.one.groceryapp.model.MyOrderModel;
import com.one.groceryapp.model.TransactionModel;

@Database(entities = {UserModel.class, AddressModel.class, CardModel.class, TransactionModel.class, MyOrderModel.class}, version = 8)
public abstract class AppDatabase extends RoomDatabase {
    private static final String databaseName = "user_database";
    public abstract UserDao userDao();
    private static volatile AppDatabase INSTANCE;
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Address_table` (`id` INTEGER NOT NULL," + "`name` TEXT," + "`email` TEXT," + "`mobile_number` TEXT," + "`zip` TEXT ," + "`country` TEXT ," + "`address` TEXT," + " `city` TEXT )");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Cards_table` (`id` INTEGER NOT NULL," + "`cardnumber` TEXT," + "`cardholder` TEXT  ," + "`carddate` TEXT," + " `cardcvv` TEXT ," + "PRIMARY KEY(`id`))");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `transaction_table` (`date` TEXT ," + "`price` INTEGER NOT NULL," + " `id` INTEGER NOT NULL," + "PRIMARY KEY(`id`))");
        }
    };

    static final Migration MIGRATION_4_5 = new Migration(4,5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `myorder_table` (`date` TEXT ," + "`price` INTEGER NOT NULL," + "`item` INTEGER NOT NULL," + " `id` INTEGER NOT NULL," + "PRIMARY KEY(`id`))");
        }
    };

    static final Migration MIGRATION_5_6 = new Migration(5,6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Address_table ADD COLUMN isswitched INTEGER DEFAULT 0 NOT NULL");
        }
    };

    static final Migration MIGRATION_6_7 = new Migration(6,7) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Cards_table ADD COLUMN isSwitched INTEGER DEFAULT 0 NOT NULL");
        }
    };

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, databaseName)
                            .allowMainThreadQueries()
                            .addMigrations(MIGRATION_1_2)
                            .addMigrations(MIGRATION_2_3)
                            .addMigrations(MIGRATION_3_4)
                            .addMigrations(MIGRATION_4_5)
                            .addMigrations(MIGRATION_5_6)
                            .addMigrations(MIGRATION_6_7)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
