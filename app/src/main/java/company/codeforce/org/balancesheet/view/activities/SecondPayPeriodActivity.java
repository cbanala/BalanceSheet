package company.codeforce.org.balancesheet.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import company.codeforce.org.balancesheet.R;

/**
 * Created by ckumo on 10/24/2016.
 */
public class SecondPayPeriodActivity extends  BaseBalanceSheetActivity implements View.OnClickListener{

    private EditText fromDate;
    private EditText toDate;
    private EditText payCheckDate;
    private EditText remainingBalance;
    private EditText rate;
    private EditText hours;
    private EditText creditedAmount;
    private EditText debitedAmount;
    private EditText endingBalance;
    private TextView finalBalance;
    private Button saveBtn;
    private Button submitBtn;
    private EditText payPeriod;
    private Button calculateBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_pay_details);
        setTitle("Second Pay Period");
        initViews();
    }

    private void initViews() {

        //Initializing Second Pay Check Details views
        //TODO: SET DATE PICKER FOR PAYPERIOD LATER
        payPeriod = (EditText) findViewById(R.id.second_pay_period_et);
        payCheckDate = (EditText) findViewById(R.id.second_paycheck_date_et);
        remainingBalance = (EditText) findViewById(R.id.second_your_balance_et);
        rate = (EditText) findViewById(R.id.second_rate_et);
        hours = (EditText) findViewById(R.id.second_hours_et);
        creditedAmount = (EditText) findViewById(R.id.second_credit_et);
        debitedAmount = (EditText) findViewById(R.id.second_debit_et);
        endingBalance = (EditText) findViewById(R.id.second_ending_balance_et);

        finalBalance = (TextView) findViewById(R.id.second_final_balance_tv);

        saveBtn = (Button) findViewById(R.id.second_save_btn);
        calculateBtn = (Button) findViewById(R.id.second_calculate_btn);
        submitBtn = (Button) findViewById(R.id.second_submit_btn);

        saveBtn.setOnClickListener(this);
        calculateBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
