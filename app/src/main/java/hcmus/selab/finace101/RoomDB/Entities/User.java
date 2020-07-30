package hcmus.selab.finace101.RoomDB.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class User {
//  Attributes:
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user_id")
    private String ID;

    @NonNull
    @ColumnInfo(name = "user_name")
    private String userName;

    @NonNull
    @ColumnInfo(name = "user_email")
    private String userEmail;


    @NonNull
    @ColumnInfo(name = "user_pw")
    private String userPw;

//  Funcs:
    public User(@NonNull String user_name, @NonNull String email, @NonNull String pw){
        this.userName = user_name;
        this.userEmail = email;
        this.userPw = pw;
    }

    public String getEmail() {
        return userEmail;
    }

    public String getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }


}
