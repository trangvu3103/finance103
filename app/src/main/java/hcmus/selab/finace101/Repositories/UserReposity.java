package hcmus.selab.finace101.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import hcmus.selab.finace101.RoomDB.DB.UserRoomDB;
import hcmus.selab.finace101.RoomDB.Daos.UserDao;
import hcmus.selab.finace101.RoomDB.Entities.User;

public class UserReposity {
    private UserDao meh;
    private LiveData<List<User>> allUsers;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    UserReposity(Application application) {
        UserRoomDB db = UserRoomDB.getDatabase(application);
        meh = db.UserDao();
        allUsers = meh.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<User>> getAllWords() {
        return allUsers;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(User user) {
        UserRoomDB.databaseWriteExecutor.execute(() -> {
            UserDao.insert(user);
        });
    }
}
