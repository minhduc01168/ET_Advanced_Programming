package ducbachkhoa.com.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ducbachkhoa.com.DTO.TypeDrinkDTO;
import ducbachkhoa.com.Database.CreateDatabase;

public class TypeDrinkDAO {
    SQLiteDatabase database;
    public TypeDrinkDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.getWritableDatabase();
    }

    public boolean ThemLoaiMonAn(String tenloai){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_TYPEDRINK_NAMETYPE, tenloai);

        long kiemtra = database.insert(CreateDatabase.TB_TYPEDRINK, null, contentValues);
        if(kiemtra != 0){
            return true;
        }else{
            return false;
        }
    }

    public List<TypeDrinkDTO> LayDanhSachLoaiMonAn(){
        List<TypeDrinkDTO> loaiMonAnDTOs = new ArrayList<TypeDrinkDTO>();

        String truyvan = "SELECT * FROM " + CreateDatabase.TB_TYPEDRINK;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            TypeDrinkDTO loaiMonAnDTO = new TypeDrinkDTO();
            int maLoaiIndex = cursor.getColumnIndex(CreateDatabase.TB_TYPEDRINK_IDTYPEDRINK);
            if (maLoaiIndex >= 0) {
                loaiMonAnDTO.setMaLoai(cursor.getInt(maLoaiIndex));
            } else {
                loaiMonAnDTO.setMaLoai(-1);
            }

            int tenLoaiIndex = cursor.getColumnIndex(CreateDatabase.TB_TYPEDRINK_NAMETYPE);
            if (tenLoaiIndex >= 0) {
                loaiMonAnDTO.setTenLoai(cursor.getString(tenLoaiIndex));
            } else {
                loaiMonAnDTO.setTenLoai("");
            }
            //loaiMonAnDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_TYPEDRINK_IDTYPEDRINK)));
            //loaiMonAnDTO.setTenLoai(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_TYPEDRINK_NAMETYPE)));
            loaiMonAnDTOs.add(loaiMonAnDTO);
            cursor.moveToNext();
        }

        return loaiMonAnDTOs;
    }
}
