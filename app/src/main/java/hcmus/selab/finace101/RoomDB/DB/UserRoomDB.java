package hcmus.selab.finace101.RoomDB.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hcmus.selab.finace101.RoomDB.Daos.UserDao;
import hcmus.selab.finace101.RoomDB.Entities.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserRoomDB extends RoomDatabase {


    public abstract UserDao UserDao();

    private static volatile UserRoomDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static UserRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDB.class, "finance101_DB")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
