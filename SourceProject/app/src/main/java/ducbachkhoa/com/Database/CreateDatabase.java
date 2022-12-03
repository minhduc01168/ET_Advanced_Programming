package ducbachkhoa.com.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper {
    public static String TB_STAFF = "STAFF";
    public static String TB_DRINK = "DRINK";
    public static String TB_TYPEDRINK = "TYPEDRINK";
    public static String TB_TABLEDRINK = "TABLEDRINK";
    public static String TB_ORDERDRINK = "ORDERDRINK";
    public static String TB_ORDERDETAILS = "ORDERDETAILS";

    public static String TB_STAFF_IDSTAFF = "IDSTAFF";
    public static String TB_STAFF_NAMEUSER = "NAMEUSER";
    public static String TB_STAFF_PASSWORD = "PASSWORD";
    public static String TB_STAFF_GENDER = "GENDER";
    public static String TB_STAFF_DATEBIRTH = "DATEBIRTH";
    public static String TB_STAFF_CCCD = "CCCD";
    public static String TB_STAFF_FULLNAME = "FULLNAME";

    public static String TB_DRINK_IDDRINK = "IDDRINK";
    public static String TB_DRINK_NAMEDRINK = "NAMEDRINK";
    public static String TB_DRINK_PRICE = "PRICE";
    public static String TB_DRINK_IDTYPEDRINK = "IDTYPEDRINK";

    public static String TB_TYPEDRINK_IDTYPEDRINK = "IDTYPEDRINK";
    public static String TB_TYPEDRINK_NAMETYPE = "NAMETYPE";

    public static String TB_TABLEDRINK_IDTABLE = "IDTABLE";
    public static String TB_TABLEDRINK_NAMETABLE = "NAMETABLE";
    public static String TB_TABLEDRINK_STATUS = "STATUS";

    public static String TB_ORDERDRINK_IDORDER = "IDORDER";
    public static String TB_ORDERDRINK_IDSTAFF = "IDSTAFF";
    public static String TB_ORDERDRINK_DATEORDER = "DATEORDER";
    public static String TB_ORDERDRINK_STATUSOERDER = "STATUSORDER";
    public static String TB_ORDERDRINK_IDTABLE = "IDTABLE";

    public static String TB_ORDERDETAILS_IDORDER = "IDORDER";
    public static String TB_ORDERDETAILS_IDDRINK = "IDDRINK";
    public static String TB_ORDERDETAILS_AMOUNT = "AMOUNT";


    public CreateDatabase(@Nullable Context context) {
        super(context, "OrderDrink", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbSTAFF = "CREATE TABLE " + TB_STAFF+ " ( "+ TB_STAFF_IDSTAFF + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TB_STAFF_NAMEUSER + " TEXT, " + TB_STAFF_FULLNAME + " TEXT," + TB_STAFF_PASSWORD + " TEXT," + TB_STAFF_GENDER
                + " TEXT," + TB_STAFF_DATEBIRTH + " TEXT," + TB_STAFF_CCCD + " TEXT )";

        String tbTABLEDRINK = "CREATE TABLE " + TB_TABLEDRINK + " ( "+ TB_TABLEDRINK_IDTABLE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TB_TABLEDRINK_NAMETABLE + " TEXT," + TB_TABLEDRINK_STATUS + " TEXT )";

        String tbDRINK = "CREATE TABLE " + TB_DRINK + " ( " + TB_DRINK_IDDRINK + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_DRINK_NAMEDRINK + " TEXT," + TB_DRINK_IDTYPEDRINK + " INTEGER," + TB_DRINK_PRICE + " TEXT )";

        String tbTYPEDRINK = "CREATE TABLE " + TB_TYPEDRINK + " ( " + TB_TYPEDRINK_IDTYPEDRINK + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_TYPEDRINK_NAMETYPE + " TEXT )";

        String tbORDERDRINK = " CREATE TABLE " + TB_ORDERDRINK + " ( " + TB_ORDERDRINK_IDORDER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_ORDERDRINK_IDTABLE + " INTEGER," + TB_ORDERDRINK_IDSTAFF + " INTEGER," + TB_ORDERDRINK_DATEORDER + " TEXT,"
                + TB_ORDERDRINK_STATUSOERDER + " TEXT )";
        String tbORDERDETAILS = "CREATE TABLE " + TB_ORDERDETAILS + " ( " + TB_ORDERDETAILS_IDORDER + " INTEGER,"
                + TB_ORDERDETAILS_IDDRINK + " INTEGER," + TB_ORDERDETAILS_AMOUNT + " INTEGER,"
                + " PRIMARY KEY (" + TB_ORDERDETAILS_IDORDER + "," +TB_ORDERDETAILS_IDDRINK + "))";
        // lỗi dấu cách trước Integer --->" integer"

        db.execSQL(tbSTAFF);
        db.execSQL(tbTABLEDRINK);
        db.execSQL(tbDRINK);
        db.execSQL(tbTYPEDRINK);
        db.execSQL(tbORDERDRINK);
        db.execSQL(tbORDERDETAILS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
