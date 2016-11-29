package company.codeforce.org.balancesheet.view.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import company.codeforce.org.balancesheet.R;

/**
 * Created by ckumo on 10/24/2016.
 */

public class BalanceSheetActivity extends BaseBalanceSheetActivity implements View.OnClickListener{

    private Button firstPayPeriodBtn;
    private Button secondPayPeriodBtn;
    private TextView yourBalanceTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_sheet);

        firstPayPeriodBtn = (Button) findViewById(R.id.first_pay_period_btn);
        secondPayPeriodBtn = (Button) findViewById(R.id.second_pay_period_btn);
        yourBalanceTV = (TextView) findViewById(R.id.bs_final_balance_tv);

        firstPayPeriodBtn.setOnClickListener(this);
        secondPayPeriodBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;
        switch (id){
            case R.id.first_pay_period_btn:
                intent = new Intent(BalanceSheetActivity.this, FirstPayPeriodActivity.class);
                startActivity(intent);
                break;
            case R.id.second_pay_period_btn:
                intent = new Intent(BalanceSheetActivity.this, SecondPayPeriodActivity.class);
                startActivity(intent);
                break;
        }
    }
}
