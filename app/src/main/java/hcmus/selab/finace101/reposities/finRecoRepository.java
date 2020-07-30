package hcmus.selab.finace101.reposities;

import android.app.Application;
import android.util.Log;

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
        Log.d("TAG", "finRecoRepository: " + finReocrds.getValue());
    }

    public LiveData<List<finRecord>> getAllRecords(){
        return this.finReocrds;
    }

    public void insert(finRecord finrecord){
        finRecordDB.databaseWriteExecutor.execute(() -> {
            finrecodao.insert(finrecord);
        });

    }

    public LiveData<finRecord> getfinRecord_name(String name){
        return finrecodao.getfinRecord_name(name);
    }

    public Double getSumVal_State(String state){
        Log.d("TAG", "getSumVal_State: " + String.valueOf(finrecodao.getSumVal_State().getValue()));
        return 0.;
    }

    public Double getSumVal_State_Unknown(String state){

        Log.d("TAG", "getSumVal_State_Unknown: " + String.valueOf(finrecodao.getSumVal_State_Unknown(state).getValue()));
        return (finrecodao.getSumVal_State_Unknown(state).getValue()!=null)?finrecodao.getSumVal_State_Unknown(state).getValue():0.;
    }

    public Double getSumVal(){
        LiveData<finRecord> finLastest_Unknown = getfinRecord_name("Unknown");
        Double res = 0.;
        if(finLastest_Unknown.getValue() != null){
            Double in = getSumVal_State_Unknown("Income");
            Double out = getSumVal_State_Unknown("Expand");
            res = finLastest_Unknown.getValue().getFin_record_value() + in - out;
        }else{
            Double in = getSumVal_State("Income");
            Double out = getSumVal_State("Expand");
            res = in - out;
        }

        return res;
    }

}
