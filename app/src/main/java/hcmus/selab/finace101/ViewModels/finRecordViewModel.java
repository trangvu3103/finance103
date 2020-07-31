package hcmus.selab.finace101.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import hcmus.selab.finace101.RoomDB.Entities.finRecord;
import hcmus.selab.finace101.reposities.finRecoRepository;

public class finRecordViewModel extends AndroidViewModel {

    private finRecoRepository mfinRecoRepository;
    private LiveData<List<finRecord>> allRecord;

    public finRecordViewModel(@NonNull Application application) {
        super(application);
        mfinRecoRepository = new finRecoRepository(application);
        allRecord = mfinRecoRepository.getAllRecords();
        Log.d("TAG", "finRecordViewModel: " + hashCode());

//        getCur();
    }

    public LiveData<List<finRecord>> getAllRecords(){

        return this.allRecord;
    }

    public void insert(finRecord record){
        mfinRecoRepository.insert(record);
    }

    public Double getSumVal(){
        return this.mfinRecoRepository.getSumVal();
    }

    public boolean isEmpty(){
        return this.allRecord.getValue().isEmpty();
    }
}
