package hcmus.selab.finace101.RoomDB.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hcmus.selab.finace101.RoomDB.Entities.User;

@Dao
public class UserDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public static void insert(User user);

    @Query("DELETE FROM Users")
    void deleteAll();

    @Query("SELECT * from Users ORDER BY user_id ASC")
    public LiveData<List<User>> getAlphabetizedWords();
}
