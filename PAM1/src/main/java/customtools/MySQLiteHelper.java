package customtools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by admin on 3/9/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_MAHASISWA = "tbl_mahasiswa";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NIM = "nim";
    public static final String COLUMN_NAMA = "nama";

    private static final String DATABASE_NAME = "db_mahasiswa.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_MAHASISWA + "("
            + COLUMN_NIM + " TEXT UNIQUE, "
            + COLUMN_NAMA + " TEXT);";

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_MAHASISWA);
        onCreate(db);
    }

    public ArrayList<DataMahasiswa> getAllDataMahasiswa(){
        ArrayList<DataMahasiswa> dataMahasiswa = new ArrayList<DataMahasiswa>();
        String query = "SELECT * FROM " + TABLE_MAHASISWA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                DataMahasiswa mhs = new DataMahasiswa();
                mhs.setNim(cursor.getString(0));
                mhs.setNama(cursor.getString(1));
                dataMahasiswa.add(mhs);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dataMahasiswa;
    }

    public void addMahasiswa(DataMahasiswa mahasiswa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NIM, mahasiswa.getNim());
        values.put(COLUMN_NAMA, mahasiswa.getNama());
        db.insert(TABLE_MAHASISWA, null, values);
        db.close();
    }

    public void deleteMahasiswa(String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        //int id =
        db.delete(TABLE_MAHASISWA, COLUMN_NIM + " = ? "  , new String[]{nim});
        db.close();
    }

}
