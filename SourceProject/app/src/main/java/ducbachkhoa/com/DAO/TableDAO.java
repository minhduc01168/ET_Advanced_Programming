package ducbachkhoa.com.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ducbachkhoa.com.DTO.TableDTO;
import ducbachkhoa.com.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class TableDAO {
    SQLiteDatabase database;
    public TableDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.getWritableDatabase();
    }
    public boolean AddTable(String tenban){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_TABLEDRINK_NAMETABLE, tenban);
        contentValues.put(CreateDatabase.TB_TABLEDRINK_STATUS, "false");

        long kiemtra = database.insert(CreateDatabase.TB_TABLEDRINK,null, contentValues);
        if(kiemtra != 0){
            return true;
        }else
            return false;
    }
    @SuppressLint("Recycle")
    public List<TableDTO> LayTatCaBanAn(){
        List<TableDTO> banAnDTOList = new ArrayList<>();
        String truyvan= "SELECT * FROM " + CreateDatabase.TB_TABLEDRINK;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            TableDTO banAnDTO = new TableDTO();
            /*dòng thêm*/
            int statusColumnIndex = cursor.getColumnIndex(CreateDatabase.TB_TABLEDRINK_STATUS);
            int nameColumnIndex = cursor.getColumnIndex(CreateDatabase.TB_TABLEDRINK_NAMETABLE);
            if (statusColumnIndex != -1 && nameColumnIndex != -1) {
                banAnDTO.setMaBan(cursor.getInt(statusColumnIndex));
                banAnDTO.setTenBan(cursor.getString(nameColumnIndex));
            } else {}
            //banAnDTO.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_TABLEDRINK_STATUS)));
            //banAnDTO.setTenBan(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_TABLEDRINK_NAMETABLE)));
            banAnDTOList.add(banAnDTO);
            cursor.moveToNext();
        }
        return banAnDTOList;
    }

}
