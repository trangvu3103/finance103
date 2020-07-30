package hcmus.selab.finace101.RoomDB.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hcmus.selab.finace101.RoomDB.Daos.finRecoDao;
import hcmus.selab.finace101.RoomDB.Entities.finRecord;
import hcmus.selab.finace101.support.DateConverter;

@Database(entities = {finRecord.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class finRecordDB extends RoomDatabase{
    public abstract finRecoDao finRecoDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile finRecordDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static finRecordDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (finRecordDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            finRecordDB.class, "finance_DB")
//                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
//    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//
//            // If you want to keep data through app restarts,
//            // comment out the following block
////            databaseWriteExecutor.execute(() -> {
////                // Populate the database in the background.
////                // If you want to start with more words, just add them.
////                finRecoDao dao = INSTANCE.finRecoDao();
////                dao.deleteAll();
////
////                finRecord word = new finRecord("Hello");
////                dao.insert(word);
////                word = new Word("World");
////                dao.insert(word);
//            });
//        }
//    };
}