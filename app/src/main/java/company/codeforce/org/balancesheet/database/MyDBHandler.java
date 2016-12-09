package company.codeforce.org.balancesheet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import company.codeforce.org.balancesheet.base.BalanceSheetApplication;
import company.codeforce.org.balancesheet.model.BalanceSheetDetails;
import company.codeforce.org.balancesheet.model.SupportTableDetails;
import company.codeforce.org.balancesheet.utils.AppConstants;

/**
 * Created by ckumo on 10/26/2016.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;



    String TABLE_NAME[] = {
            AppConstants.TABLE_NAME_BALANCESHEET,
            AppConstants.TABLE_NAME_SUPPORT,
            AppConstants.TABLE_NAME_MONTHLYVIEW
    };


    private static MyDBHandler instance;

    //Constructor to invoke this class
    private MyDBHandler(Context context) {
        super(context, AppConstants.DATABASE_NAME, null, AppConstants.DATABASE_VERSION);
        Log.d("DATABASE" , "MyHandler is Called....");
    }

    public static MyDBHandler getInstance() {
        if(instance == null) {
            instance = new MyDBHandler(BalanceSheetApplication.getInstance().getApplicationContext());
        }
        return instance;
    }

    //onCreate callback is invoked when the database is actually opened
    //for Example by a call to getWritableDatabase().
    //The Database is not opened when the database helper object itself is called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DATABASE" , "MyHandler onCreate() is Called....");
        createBalanceSheetTable(db);
        createSupportTable(db);
        createMonthlyViewTable(db);
    }

    //The onUpgrade() method will only be called when the version integer is larger than the current version running in the app.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        for(String tables : TABLE_NAME){
            //Drop Older table if existed.
            db.execSQL("DROP TABLE IF EXISTS" + tables);
        }
        //Create tables again
        onCreate(db);
    }

    //Creating Support Table
    private void createSupportTable(SQLiteDatabase db) {
        String query = " CREATE TABLE IF NOT EXISTS " + AppConstants.TABLE_NAME_SUPPORT + "(" +
                AppConstants.COLUMN_SUPPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                AppConstants.COLUMN_SUPPORT_MONTH + " TEXT," +
                AppConstants.COLUMN_SUPPORT_YEAR + " TEXT," +
                AppConstants.COLUMN_SUPPORT_AMOUNT + " INTEGER" + ")";
        db.execSQL(query);
        Log.d("DATABASE" , "CreateSupportTable is Called....");
    }

    //Creating BalanceSheet Table
    private void createBalanceSheetTable(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + AppConstants.TABLE_NAME_BALANCESHEET + "(" +
                AppConstants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                AppConstants.COLUMN_PERIOD + " TEXT," +
                AppConstants.COLUMN_PAYCHECKDATE + " TEXT," +
                AppConstants.COLUMN_OPENING_BALANCE + " REAL," +
                AppConstants.COLUMN_RATE + " REAL," +
                AppConstants.COLUMN_HOURS + " INTEGER," +
                AppConstants.COLUMN_CREDIT + " REAL," +
                AppConstants.COLUMN_DEBIT + " REAL," +
                AppConstants.COLUMN_ENDING_BALANCE + " REAL" + ")";
        db.execSQL(query);
        Log.d("DATABASE" , "CreateBalanceSheetTable is Called....");
    }

    //Creating MonthlyView Table
    private void createMonthlyViewTable(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + AppConstants.TABLE_NAME_MONTHLYVIEW + "(" +
                AppConstants.COLUMN_MONTHVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                AppConstants.COLUMN_MONTHVIEW_MONTH + " TEXT," +
                AppConstants.COLUMN_MONTHVIEW_YEAR + " INTEGER," +
                AppConstants.COLUMN_MONTHVIEW_OPENING_BALANCE + " REAL," +
                AppConstants.COLUMN_MONTHVIEW_ENDING_BALANCE + " REAL," +
                AppConstants.COLUMN_MONTHVIEW_HOURS + " INTEGER" + ")";
        db.execSQL(query);

    }

    public void insertRecordsIntoBalanceSheetTable(BalanceSheetDetails balanceSheetDetails) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppConstants.COLUMN_PERIOD, balanceSheetDetails.getPeriod());
        values.put(AppConstants.COLUMN_PAYCHECKDATE, balanceSheetDetails.getPayCheckDate());
        values.put(AppConstants.COLUMN_OPENING_BALANCE, balanceSheetDetails.getOpeningBalance());
        values.put(AppConstants.COLUMN_RATE, balanceSheetDetails.getRate());
        values.put(AppConstants.COLUMN_HOURS, balanceSheetDetails.getHours());
        values.put(AppConstants.COLUMN_CREDIT, balanceSheetDetails.getCredit());
        values.put(AppConstants.COLUMN_DEBIT, balanceSheetDetails.getDebit());
        values.put(AppConstants.COLUMN_ENDING_BALANCE, balanceSheetDetails.getEndingBalance());
        database.insert(AppConstants.TABLE_NAME_BALANCESHEET,null,values);

        Log.d("Database", "insertingRecordsWithValues() method....");
        Log.d("Database", "period..." + balanceSheetDetails.getPeriod());
        Log.d("Database", "payCheckDate..." + balanceSheetDetails.getPayCheckDate());
        Log.d("Database", "opening balance..." + balanceSheetDetails.getOpeningBalance());
        Log.d("Database", "rate..." + balanceSheetDetails.getRate());
        Log.d("Database", "hours..." + balanceSheetDetails.getHours());
        Log.d("Database", "credit..." + balanceSheetDetails.getCredit());
        Log.d("Database", "debit..." + balanceSheetDetails.getDebit());
        Log.d("Database", "endingBalance..." + balanceSheetDetails.getEndingBalance());

        Log.d("Database", "Inserting all the values...");
        database.close();
    }

    public void insertRecordsIntoSupportTable(SupportTableDetails supportTableDetails) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppConstants.COLUMN_SUPPORT_MONTH, supportTableDetails.getMonth());
        values.put(AppConstants.COLUMN_SUPPORT_YEAR, supportTableDetails.getYear());
        values.put(AppConstants.COLUMN_SUPPORT_AMOUNT, supportTableDetails.getCost());
        database.insert(AppConstants.TABLE_NAME_SUPPORT,null,values);

        Log.d("Database", "after inserting month in support..." + supportTableDetails.getMonth());
        Log.d("Database", "after inserting year in support..." + supportTableDetails.getYear());
        Log.d("Database", "after inserting cost in support..." + supportTableDetails.getCost());
        database.close();
    }

    //get the sum of entire row
    public int sumOfEntireColumn(String columnName, String tableName) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT SUM(" + columnName + ") FROM " + tableName;
        Cursor cursor = database.rawQuery(query,null);
        cursor.close();

        return cursor.getCount();
    }
}
