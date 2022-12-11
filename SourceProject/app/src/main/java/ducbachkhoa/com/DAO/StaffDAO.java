package ducbachkhoa.com.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ducbachkhoa.com.DTO.StaffDTO;
import ducbachkhoa.com.Database.CreateDatabase;

public class StaffDAO {
    SQLiteDatabase database;
    public StaffDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.getWritableDatabase();
    }
    public long AddStaff(StaffDTO staffDTO){
        ContentValues contentValues =new ContentValues();
        contentValues.put(CreateDatabase.TB_STAFF_NAMEUSER, staffDTO.getName_user());
        contentValues.put(CreateDatabase.TB_STAFF_FULLNAME, staffDTO.getFull_name());
        contentValues.put(CreateDatabase.TB_STAFF_CCCD, staffDTO.getCccd());
        contentValues.put(CreateDatabase.TB_STAFF_GENDER, staffDTO.getGender());
        contentValues.put(CreateDatabase.TB_STAFF_PASSWORD, staffDTO.getPassword());
        contentValues.put(CreateDatabase.TB_STAFF_DATEBIRTH, staffDTO.getDate_birth());
        //bien ktra de xem them thanh cong khong?
        long kiemtra = database.insert(CreateDatabase.TB_STAFF, null, contentValues);
        return kiemtra;
    }
    public boolean CheckStaff(){
        String truyvan = "SELECT *FROM " + CreateDatabase.TB_STAFF;
        Cursor cursor = database.rawQuery(truyvan,null);
        if(cursor.getCount() != 0){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean CheckDangNhap(String tendangnhap, String matkhau){
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_STAFF + " WHERE " + CreateDatabase.TB_STAFF_NAMEUSER + " = '" +tendangnhap
                + "' AND " + CreateDatabase.TB_STAFF_PASSWORD + " = '" + matkhau + "'";
        Cursor cursor = database.rawQuery(truyvan,null);
        if(cursor.getCount() != 0){
            return true;
        }
        else {
            return false;
        }
    }
}
