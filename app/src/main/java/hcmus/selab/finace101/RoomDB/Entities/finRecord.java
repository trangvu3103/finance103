package hcmus.selab.finace101.RoomDB.Entities;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Entity(tableName = "fin_record")
public class finRecord{

    private static final AtomicInteger count = new AtomicInteger(0);

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "fin_record_id")
    private String fin_record_id;

    @NonNull
    @ColumnInfo(name = "fin_record_name")
    private String fin_record_name;

    @ColumnInfo(name = "fin_record_value")
    private Double fin_record_value;

    @ColumnInfo(name = "fin_record_status")
    private String fin_record_status;

    @ColumnInfo(name = "fin_record_dateCreated")
    private Date fin_record_dateCreated;

    public finRecord(@NonNull String fin_record_name,
                     @NonNull Double fin_record_value,
                     @NonNull String fin_record_status) {
        this.fin_record_id = String.valueOf(count.incrementAndGet());
        this.fin_record_name = fin_record_name;
        this.fin_record_value = fin_record_value;
        this.fin_record_status = fin_record_status;

        this.fin_record_dateCreated = new Date(System.currentTimeMillis());
        Log.d("TAG", "finRecord: "+ String.valueOf(fin_record_dateCreated));
    }

    @NonNull
    public String getFin_record_id() {
        return this.fin_record_id;
    }

    @NonNull
    public String getFin_record_name(){ return this.fin_record_name;}

    @NonNull
    public  Double getFin_record_value() { return this.fin_record_value;}

    @NonNull
    public String getFin_record_status(){ return this.fin_record_status;}

    @NonNull
    public  Date getFin_record_dateCreated(){return this.fin_record_dateCreated;}

    public  void setFin_record_id(@NonNull String fin_record_id){
        this.fin_record_id = fin_record_id;
    }

    public void setFin_record_dateCreated(@Nullable Date date){
        if (date != null){
            this.fin_record_dateCreated = date;
        }else{
            this.fin_record_dateCreated = new Date(System.currentTimeMillis());
        }
    }

//    public  void setFin_record_name(@NonNull String fin_record_name){
//        this.fin_record_name = fin_record_name;
//    }
//
//    public  void setFin_record_value(@NonNull Double fin_record_value){
//        this.fin_record_value = fin_record_value;
//    }
//    public  void setFin_record_status(@NonNull String fin_record_status){
//        this.fin_record_status = fin_record_status;
//    }

}