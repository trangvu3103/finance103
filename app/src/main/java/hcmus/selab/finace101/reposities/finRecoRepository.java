package hcmus.selab.finace101.reposities;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import hcmus.selab.finace101.RoomDB.DB.finRecordDB;
import hcmus.selab.finace101.RoomDB.Daos.finRecoDao;
import hcmus.selab.finace101.RoomDB.Entities.finRecord;

public class finRecoRepository {
    private finRecoDao finrecodao;
    private LiveData<List<finRecord>> finReocrds;
    private LiveData<finRecord> curRecord;

    public finRecoRepository(Application application){
        finRecordDB db = finRecordDB.getDatabase(application);
        finrecodao = db.finRecoDao();
        finReocrds = finrecodao.getOrederedRecords();
    }

    public LiveData<List<finRecord>> getAllRecords(){
        return this.finReocrds;
    }

    public void insert(finRecord finrecord){
        finRecordDB.databaseWriteExecutor.execute(() -> {
            finrecodao.insert(finrecord);
        });

    }


}
