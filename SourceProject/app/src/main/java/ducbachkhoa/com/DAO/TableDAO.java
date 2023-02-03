package ducbachkhoa.com.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ducbachkhoa.com.Database.CreateDatabase;

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
}
