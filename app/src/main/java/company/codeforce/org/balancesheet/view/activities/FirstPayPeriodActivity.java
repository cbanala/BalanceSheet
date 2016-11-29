package company.codeforce.org.balancesheet.view.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import company.codeforce.org.balancesheet.R;
import company.codeforce.org.balancesheet.database.MyDBHandler;
import company.codeforce.org.balancesheet.model.BalanceSheetDetails;

/**
 * Created by ckumo on 10/24/2016.
 */
public class FirstPayPeriodActivity extends BaseBalanceSheetActivity implements View.OnClickListener {


    private EditText payPeriod;
    private EditText payCheckDate;
    private EditText openingBalance;
    private EditText rate;
    private EditText hours;
    private EditText creditedAmount;
    private EditText debitedAmount;
    private EditText remainingBalance;
    private TextView finalBalance;
    private Button saveBtn;
    private Button submitBtn;
    private MyDBHandler myDBHandler;
    private Button calculateBtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_first_pay_details);
        setTitle("First Pay Period");
        initViews();

    }

    private void initViews() {

        //Initializing First Pay Check Details views
        //TODO: SET DATE PICKER FOR PAYPERIOD LATER
        payPeriod = (EditText) findViewById(R.id.first_pay_period_et);
        payCheckDate = (EditText) findViewById(R.id.first_paycheck_date_et);
        openingBalance = (EditText) findViewById(R.id.first_opening_balance_et);
        rate = (EditText) findViewById(R.id.first_rate_et);
        hours = (EditText) findViewById(R.id.first_hours_et);
        creditedAmount = (EditText) findViewById(R.id.first_credit_et);
        debitedAmount = (EditText) findViewById(R.id.first_debit_et);
        remainingBalance = (EditText) findViewById(R.id.first_your_balance_et);

        finalBalance = (TextView) findViewById(R.id.first_final_balance_tv);

        saveBtn = (Button) findViewById(R.id.first_save_btn);
        calculateBtn = (Button) findViewById(R.id.first_calculate_btn);
        submitBtn = (Button) findViewById(R.id.first_submit_btn);

        saveBtn.setOnClickListener(this);
        calculateBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
    }

    public void calculateRemainingBalance() {

        float openingBalanceAmount = Float.parseFloat(openingBalance.getText().toString());
        float debitAmount = Float.parseFloat(debitedAmount.getText().toString());

        //Calculate Credit Amount
        float hourlyrate = Float.parseFloat(rate.getText().toString());
        int  totalhours = Integer.parseInt(hours.getText().toString());
        String creditAmount = String.valueOf((hourlyrate * totalhours));
        creditedAmount.setText(creditAmount);

        float remainingBalanceAmount = openingBalanceAmount + (Float.parseFloat(creditAmount) - debitAmount);
        remainingBalance.setText(String.valueOf(remainingBalanceAmount));
        finalBalance.setText("Balance: " +String.valueOf(remainingBalanceAmount));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_save_btn:
                setAlltextValues();
                //TODO: Check All fields are entered
                break;
            case R.id.first_submit_btn:
                //TODO: Submit the data to the MonthView
                getAlltheValues();
                finish();
                break;
            case R.id.first_calculate_btn:
                calculateRemainingBalance();
        }
    }

    private void setAlltextValues() {

        BalanceSheetDetails balanceSheetDetails = new BalanceSheetDetails();
        balanceSheetDetails.setPeriod(payPeriod.getText().toString());
        balanceSheetDetails.setPayCheckDate(payCheckDate.getText().toString());
        balanceSheetDetails.setOpeningBalance(Float.parseFloat(openingBalance.getText().toString()));
        balanceSheetDetails.setRate(Float.parseFloat(rate.getText().toString()));
        balanceSheetDetails.setHours(Integer.parseInt(hours.getText().toString()));
        balanceSheetDetails.setCredit(Float.parseFloat(creditedAmount.getText().toString()));
        balanceSheetDetails.setDebit(Float.parseFloat(debitedAmount.getText().toString()));
        balanceSheetDetails.setEndingBalance(Float.parseFloat(remainingBalance.getText().toString()));

        Log.d("Database", "After Setting all the values...");

        Log.d("Database", "period..." + payPeriod.getText().toString());
        Log.d("Database", "payCheckDate..." + payCheckDate.getText().toString());
        Log.d("Database", "opening balance..." + Float.parseFloat(openingBalance.getText().toString()));
        Log.d("Database", "rate..." + Float.parseFloat(rate.getText().toString()));
        Log.d("Database", "hours..." + Integer.parseInt(hours.getText().toString()));
        Log.d("Database", "credit..." + Float.parseFloat(creditedAmount.getText().toString()));
        Log.d("Database", "debit..." + Float.parseFloat(debitedAmount.getText().toString()));
        Log.d("Database", "endingBalance..." + Float.parseFloat(remainingBalance.getText().toString()));


    }


    private void getAlltheValues() {
        Log.d("Database", "Getting all the values...");

        //TODO: GETTING ALL NULL VALUES....
        BalanceSheetDetails balanceSheetDetails = new BalanceSheetDetails();
        String period = balanceSheetDetails.getPeriod();
        String payCheckDate = balanceSheetDetails.getPayCheckDate();
        float openingBalance = balanceSheetDetails.getOpeningBalance();
        float rate = balanceSheetDetails.getRate();
        int hours = balanceSheetDetails.getHours();
        float credit = balanceSheetDetails.getCredit();
        float debit = balanceSheetDetails.getDebit();
        float endingBalance = balanceSheetDetails.getEndingBalance();

        MyDBHandler.getInstance().insertRecordsIntoBalanceSheetTable(period,payCheckDate,openingBalance,rate,hours,credit,debit,endingBalance);

    }



    public void SubmitData2SQLiteDB(){

        //TODO: GET ALL THE VALUES AND CHECK WHETHER IT IS EMPTY OR NOT IF NOT EMPTY SUBMITDATA(INSERT)

//        Name = GetName.getText().toString();
//
//        PhoneNumber = GetPhoneNumber.getText().toString();
//
//        Subject = GetSubject.getText().toString();
//
//        CheckEditTextIsEmptyOrNot( Name,PhoneNumber, Subject);
//
//        if(CheckEditTextEmpty == true)
//        {
//
//            SQLiteQuery = "INSERT INTO demoTable (name,phone_number,subject) VALUES('"+Name+"', '"+PhoneNumber+"', '"+Subject+"');";
//
//            SQLITEDATABASE.execSQL(SQLiteQuery);
//
//            Toast.makeText(MainActivity.this,"Data Submit Successfully", Toast.LENGTH_LONG).show();
//
//            ClearEditTextAfterDoneTask();
//
//        }
//        else {
//
//            Toast.makeText(MainActivity.this,"Please Fill All the Fields", Toast.LENGTH_LONG).show();
//        }
    }

    //This Method will check whether all the fields are entered or not
    private boolean checkAllFieldsareEntered() {
        //TODO: SHOULD CHECK WHETHER ALL FIELDS ARE ENTERED OR

//        if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(PhoneNumber) || TextUtils.isEmpty(Subject)){
//
//            CheckEditTextEmpty = false ;
        // dialogue will pop up to fill all the fields
//
//        }
//        else {
//            CheckEditTextEmpty = true ;
//        }
        return false; // will return true if entered or false if not entered
    }

}
