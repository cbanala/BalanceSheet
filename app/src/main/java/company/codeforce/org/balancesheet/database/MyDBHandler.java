package company.codeforce.org.balancesheet.database;

import android.content.ContentValues;
import android.content.Context;
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

    private final static String TABLE_NAME_BALANCESHEET = "balance_sheet_table";
    private final static String TABLE_NAME_SUPPORT = "support_table";
    private final static String TABLE_NAME_MONTHLYVIEW = "monthly_view";

    String TABLE_NAME[] = {
                            TABLE_NAME_BALANCESHEET,
                            TABLE_NAME_SUPPORT,
                            TABLE_NAME_MONTHLYVIEW
                            };

    //For BalanceSheet
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_PERIOD = "period";
    private final static String COLUMN_PAYCHECKDATE = "payCheckDate";
    private final static String COLUMN_OPENING_BALANCE = "openingBalance";
    private final static String COLUMN_RATE = "rate";
    private final static String COLUMN_HOURS = "hours";
    private final static String COLUMN_CREDIT = "credit";
    private final static String COLUMN_DEBIT = "debit";
    private final static String COLUMN_ENDING_BALANCE = "endingBalance";

    //For Support
    private final static String COLUMN_SUPPORT_ID = "id";
    private final static String COLUMN_SUPPORT_MONTH = "month";
    private final static String COLUMN_SUPPORT_YEAR = "year";
    private final static String COLUMN_SUPPORT_AMOUNT = "amount";

    //Other Deduction
    private final static String COLUMN_OD_Description = "description";
    private final static String COLUMN_OD_DATE = "date";
    private final static String COLUMN_OD_AMOUNT = "amount";

    //Monthlyview
    private final static String COLUMN_MONTHVIEW_MONTH = "month";
    private final static String COLUMN_MONTHVIEW_YEAR = "year";
    private final static String COLUMN_MONTH_OPENING_BALANCE = "openingBalance";
    private final static String COLUMN_MONTH_ENDING_BALANCE = "endingBalance";
    private final static String COLUMN_MONTH_HOURS = "hours";
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
        String query = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME_SUPPORT + "(" +
        COLUMN_SUPPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_SUPPORT_MONTH + " TEXT," +
                COLUMN_SUPPORT_YEAR + " TEXT," +
                COLUMN_SUPPORT_AMOUNT + " INTEGER" + ")";
        db.execSQL(query);
        Log.d("DATABASE" , "CreateSupportTable is Called....");
    }

    //Creating BalanceSheet Table
    private void createBalanceSheetTable(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_BALANCESHEET + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PERIOD + " TEXT," +
                COLUMN_PAYCHECKDATE + " TEXT," +
                COLUMN_OPENING_BALANCE + " REAL," +
                COLUMN_RATE + " REAL," +
                COLUMN_HOURS + " INTEGER," +
                COLUMN_CREDIT + " REAL," +
                COLUMN_DEBIT + " REAL," +
                COLUMN_ENDING_BALANCE + " REAL" + ")";
        db.execSQL(query);
        Log.d("DATABASE" , "CreateBalanceSheetTable is Called....");
    }



    public void insertRecordsIntoBalanceSheetTable(String period,
                                                   String payCheckDate,
                                                   float openingBalance,
                                                   float rate, int hours,
                                                   float credit, float debit,
                                                   float endingBalance) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PERIOD, period);
        values.put(COLUMN_PAYCHECKDATE, payCheckDate);
        values.put(COLUMN_OPENING_BALANCE, openingBalance);
        values.put(COLUMN_RATE, rate);
        values.put(COLUMN_HOURS, hours);
        values.put(COLUMN_CREDIT, credit);
        values.put(COLUMN_DEBIT, debit);
        values.put(COLUMN_ENDING_BALANCE, endingBalance);
        database.insert(TABLE_NAME_BALANCESHEET,null,values);

        Log.d("Database", "insertingRecordsWithValues() method....");
        Log.d("Database", "period..." + period);
        Log.d("Database", "payCheckDate..." + payCheckDate);
        Log.d("Database", "opening balance..." + openingBalance);
        Log.d("Database", "rate..." + rate);
        Log.d("Database", "hours..." + hours);
        Log.d("Database", "credit..." + credit);
        Log.d("Database", "debit..." + debit);
        Log.d("Database", "endingBalance..." + endingBalance);

        Log.d("Database", "Inserting all the values...");
        database.close();
    }

    public void insertRecordsIntoSupportTable(SupportTableDetails supportTableDetails) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUPPORT_MONTH, supportTableDetails.getMonth());
        values.put(COLUMN_SUPPORT_YEAR, supportTableDetails.getYear());
        values.put(COLUMN_SUPPORT_AMOUNT, supportTableDetails.getCost());
        database.insert(TABLE_NAME_SUPPORT,null,values);

        Log.d("Database", "after inserting month in support..." + supportTableDetails.getMonth());
        Log.d("Database", "after inserting year in support..." + supportTableDetails.getYear());
        Log.d("Database", "after inserting cost in support..." + supportTableDetails.getCost());
        database.close();
    }
}
