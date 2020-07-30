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

//    @Query("SELECT COALESCE(sum(COALESCE(value,0)), 0) From expense_table")
//    LiveData<Double> getTotal();

    @Query("SELECT *\n" +
            "FROM fin_record t1\n" +
            "WHERE fin_record_name = :name\n" +
            "ORDER BY fin_record_dateCreated DESC\n" +
            "LIMIT 1")
    LiveData<finRecord> getfinRecord_name(String name);

    @Query("SELECT * FROM fin_record")
    LiveData<List<finRecord>> getSumVal_State ();

    @Query("SELECT COALESCE(sum(COALESCE(fin_record_value,0)), 0)\n" +
            "FROM fin_record t1\n" +
            "WHERE t1.fin_record_status = :state and t1.fin_record_dateCreated > (SELECT t2.fin_record_dateCreated\n" +
            "                 FROM fin_record t2\n" +
            "                 WHERE t2.fin_record_name = "+"\"Unknown\"" +" ORDER BY t2.fin_record_dateCreated DESC LIMIT 1)")
    LiveData<Double> getSumVal_State_Unknown (String state);

}
