package company.codeforce.org.balancesheet.utils;

/**
 * Created by ckumo on 10/25/2016.
 */

public class AppConstants {

    public static final String MONTH_FORMAT_SUMMARY = "MMMM yyyy";


    public static final String DATABASE_NAME = "balanaceSheet.db";
    public static final int DATABASE_VERSION = 1;

    //Tables
    public final static String TABLE_NAME_BALANCESHEET = "balance_sheet_table";
    public final static String TABLE_NAME_SUPPORT = "support_table";
    public final static String TABLE_NAME_MONTHLYVIEW = "monthly_view";

    //For BalanceSheet
    public final static String COLUMN_ID = "id";
    public final static String COLUMN_PERIOD = "period";
    public final static String COLUMN_PAYCHECKDATE = "payCheckDate";
    public final static String COLUMN_OPENING_BALANCE = "openingBalance";
    public final static String COLUMN_RATE = "rate";
    public final static String COLUMN_HOURS = "hours";
    public final static String COLUMN_CREDIT = "credit";
    public final static String COLUMN_DEBIT = "debit";
    public final static String COLUMN_ENDING_BALANCE = "endingBalance";

    //For Support
    public final static String COLUMN_SUPPORT_ID = "id";
    public final static String COLUMN_SUPPORT_MONTH = "month";
    public final static String COLUMN_SUPPORT_YEAR = "year";
    public final static String COLUMN_SUPPORT_AMOUNT = "amount";

    //Other Deduction
    public final static String COLUMN_OD_Description = "description";
    public final static String COLUMN_OD_DATE = "date";
    public final static String COLUMN_OD_AMOUNT = "amount";

    //Monthlyview
    public final static String COLUMN_MONTHVIEW_ID = "id";
    public final static String COLUMN_MONTHVIEW_MONTH = "month";
    public final static String COLUMN_MONTHVIEW_YEAR = "year";
    public final static String COLUMN_MONTHVIEW_OPENING_BALANCE = "openingBalance";
    public final static String COLUMN_MONTHVIEW_ENDING_BALANCE = "endingBalance";
    public final static String COLUMN_MONTHVIEW_HOURS = "hours";


}
