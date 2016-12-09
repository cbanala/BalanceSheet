package company.codeforce.org.balancesheet.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import company.codeforce.org.balancesheet.R;

/**
 * Created by ckumo on 12/8/2016.
 */

public class MonthlyViewActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_view);
        setTitle("Monthly View");
    }
}
