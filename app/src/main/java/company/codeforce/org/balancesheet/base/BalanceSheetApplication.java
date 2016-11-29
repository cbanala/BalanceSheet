package company.codeforce.org.balancesheet.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import company.codeforce.org.balancesheet.database.MyDBHandler;
import company.codeforce.org.balancesheet.utils.AppConstants;

/**
 * Created by ckumo on 11/22/2016.
 */
public class BalanceSheetApplication extends Application {

    private static BalanceSheetApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static final BalanceSheetApplication getInstance() {
        return instance;
    }
}
