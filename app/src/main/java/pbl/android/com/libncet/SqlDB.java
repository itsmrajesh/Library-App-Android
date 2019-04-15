package pbl.android.com.libncet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class SqlDB extends SQLiteOpenHelper {
    //specifying DB name
    public static final String DATABASE_NAME = "NCETLibrary.db";

    int count = 0;
    //specifying table names
    public static final String LOGIN_TABLE_NAME = "Login";
    public static final String STUDENT_TABLE_NAME = "Student";
    public static final String BOOK_TABLE_NAME = "Book";
    public static final String ISSUE_TABLE_NAME = "Library";

    //specifying columns names (login table)
    public static final String LOGIN_USERNAME = "Username";
    public static final String LOGIN_PASSWORD = "Password";

    //specifying columns names (Student table)
    public static final String STUDENT_NAME = "Name";
    public static final String STUDENT_USN = "USN";
    public static final String STUDENT_AGE = "Age";
    public static final String STUDENT_SEM = "Semester";
    public static final String STUDENT_DEPT = "Department";

    //specifying column names (Book table)
    public static final String BOOK_TITLE = "Title";
    public static final String BOOK_ID = "ID";
    public static  final String BOOK_AUTHOR = "Author";

    //specifying column names (Library table)
    public static final String LIBRARY_BOOK_TITLE = "Book_Title";
    public static final String LIBRARY_BOOK_ID = "BOOK_ID";
    public static final String LIBRARY_STUDENT_USN = "Student_USN";
    public static final String LIBRARY_STUDENT_NAME = "Student_Name";
    public static final String LIBRARY_DOI = "DOI";
    public static final String LIBRARY_DOR = "DOR";

    public static final String CREATE_LOGIN_TABLE = "Create table " + LOGIN_TABLE_NAME +"(" + LOGIN_USERNAME + " TEXT PRIMARY KEY,"
            + LOGIN_PASSWORD + " VARCHAR)";

    public static final String CREATE_STUDENT_TABLE = "Create table " + STUDENT_TABLE_NAME + "(" + STUDENT_USN + " VARCHAR PRIMARY KEY,"
            + STUDENT_NAME + " TEXT," + STUDENT_AGE + " INTEGER," + STUDENT_SEM + " TEXT," + STUDENT_DEPT + " TEXT)";

    public static final String CREATE_BOOK_TABLE = "Create table " + BOOK_TABLE_NAME + "(" + BOOK_ID + " INTEGER PRIMARY KEY,"
            + BOOK_TITLE + " TEXT," + BOOK_AUTHOR + " TEXT)" ;

    public static final String CREATE_LIBRARY_TABLE = "Create table " + ISSUE_TABLE_NAME + "(" + LIBRARY_BOOK_ID + " INTEGER PRIMARY KEY,"
            + LIBRARY_BOOK_TITLE + " TEXT," + LIBRARY_STUDENT_USN + " VARCHAR," + LIBRARY_STUDENT_NAME + " TEXT," + LIBRARY_DOI + " VARCHAR,"
            + LIBRARY_DOR + " VARCHAR)";

    public SqlDB(Context context, String donothing){
        super(context, DATABASE_NAME, null, 1);
    }

    public SqlDB( Context context) {
        super(context, DATABASE_NAME, null, 1);
            SQLiteDatabase db = this.getWritableDatabase();
             System.out.println("Login data insert");
            Insertvalues_LOGIN("Rajesh", "pwd");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("On create method");
        db.execSQL(CREATE_LOGIN_TABLE);
        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_LIBRARY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DropTable(LOGIN_TABLE_NAME));
        db.execSQL(DropTable(STUDENT_TABLE_NAME));
        db.execSQL(DropTable(BOOK_TABLE_NAME));
        db.execSQL(DropTable(ISSUE_TABLE_NAME));
    }

    public String DropTable(String tableName){
        String DROP_TABLE = "DROP TABLE IF EXISTS " + tableName;

        return DROP_TABLE;
    }

    public void Insertvalues_LOGIN(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(LOGIN_USERNAME, username);
        contentValues.put(LOGIN_PASSWORD, password);

        long res =db.insert(LOGIN_TABLE_NAME,null,contentValues);

        if (res == 0){
            Log.e("mytag", "Insertvalues_LOGIN: NOT INSERTED");
        }else {
            Log.i("mytag", "Insertvalues_LOGIN:  INSERTED");
        }
    }

    public long Insertvalues_STUDENT(String name,String usn,int age,int sem,String dept){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NAME,name);
        contentValues.put(STUDENT_USN,usn);
        contentValues.put(STUDENT_AGE,age);
        contentValues.put(STUDENT_SEM,sem);
        contentValues.put(STUDENT_DEPT,dept);

        long res = db.insert(STUDENT_TABLE_NAME,null,contentValues);

        if (res == 0){
            Log.e("mytag", "Insertvalues_STUDENT: NOT INSERTED");
        }else {
            Log.i("mytag", "Insertvalues_STUDENT:  INSERTED");
        }

        Cursor cursor = db.rawQuery("SELECT * FROM " + STUDENT_TABLE_NAME,null);

        if (cursor.getCount() == 0){
            Log.i("mytag", "Get_LOGIN_data: NO ENTRIES");
        }else {
            while (cursor.moveToNext()) {
                Log.i("mytag", "Insertvalues_STUDENT: usn " + cursor.getString(0));
                Log.i("mytag", "Insertvalues_STUDENT: name " + cursor.getString(1));
                Log.i("mytag", "Insertvalues_STUDENT: age " + cursor.getInt(2));
                Log.i("mytag", "Insertvalues_STUDENT: sem " + cursor.getInt(3));
                Log.i("mytag", "Insertvalues_STUDENT: dept " + cursor.getString(4));
            }
        }

        return res;
    }

    public long Insertvalues_BOOK(int id,String title,String author){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_ID,id);
        contentValues.put(BOOK_TITLE,title);
        contentValues.put(BOOK_AUTHOR,author);

        long res = db.insert(BOOK_TABLE_NAME,null,contentValues);

        if (res == 0){
            Log.e("mytag", "Insertvalues_STUDENT: NOT INSERTED");
        }else {
            Log.i("mytag", "Insertvalues_STUDENT:  INSERTED");
        }

        Cursor cursor = db.rawQuery("SELECT * FROM " + BOOK_TABLE_NAME,null);

        if (cursor.getCount() == 0){
            Log.i("mytag", "Get_LOGIN_data: NO ENTRIES");
        }else {
            while (cursor.moveToNext()) {
                Log.i("mytag", "Insertvalues_STUDENT: id " + cursor.getString(0));
                Log.i("mytag", "Insertvalues_STUDENT: title " + cursor.getString(1));
                Log.i("mytag", "Insertvalues_STUDENT: author " + cursor.getString(2));
            }
        }

        return res;

    }

    public long Insertvalue_Library(String title,int id,String usn,String name,String doi,String dor){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(LIBRARY_BOOK_ID,id);
        contentValues.put(LIBRARY_BOOK_TITLE,title);
        contentValues.put(LIBRARY_STUDENT_USN,usn);
        contentValues.put(LIBRARY_STUDENT_NAME,name);
        contentValues.put(LIBRARY_DOI,doi);
        contentValues.put(LIBRARY_DOR,dor);

        long res = db.insert(ISSUE_TABLE_NAME,null,contentValues);

        return res;
    }

    public boolean check_issue_count(String USN){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ISSUE_TABLE_NAME,null);

        if (cursor.getCount() == 0){
            return true;
        }else {
            while (cursor.moveToNext()){
                String db_USN = cursor.getString(2);
                if(USN.equals(db_USN)){
                    count++;
                    Log.i("mytag", "check_issue_count =  " + count);
                }
            }
            if (count < 4){
                count = 0;
                return true;
            }else {
                count = 0;
                return false;
            }
        }
    }


}
