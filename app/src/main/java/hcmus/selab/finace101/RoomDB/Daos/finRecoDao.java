package hcmus.selab.finace101.RoomDB.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hcmus.selab.finace101.RoomDB.Entities.finRecord;

@Dao
public interface finRecoDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(finRecord record);

    @Query("DELETE FROM fin_record")
    void deleteAll();

    @Query("SELECT * from fin_record ORDER BY fin_record_dateCreated DESC")
    LiveData<List<finRecord>> getOrederedRecords();

}
