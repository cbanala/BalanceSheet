package company.codeforce.org.balancesheet.view.activities;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import company.codeforce.org.balancesheet.R;
import company.codeforce.org.balancesheet.adapter.BalanceSheetAdapter;
import company.codeforce.org.balancesheet.database.MyDBHandler;
import company.codeforce.org.balancesheet.model.SupportTableDetails;
import company.codeforce.org.balancesheet.presenter.SupportPresenter;
import company.codeforce.org.balancesheet.utils.AppConstants;
import company.codeforce.org.balancesheet.viewInterface.SupportInterface;

/**
 * Created by ckumo on 10/24/2016.
 */

public class SupportActivity extends BaseBalanceSheetActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, DatePickerDialog.OnDateSetListener,
        AdapterView.OnItemSelectedListener, SupportInterface {


    private final static String TAG = SupportActivity.class.getSimpleName();
    private TextView yourDeductionsTV;
    private Spinner monthSpinner;
    private EditText enterAmountET;
    private Button addButton;
    private ListView listView;
    private ArrayList<String> listItems;
    private BalanceSheetAdapter adapter;
    private int mCurrentYear;
    private int mCurrentMonth;
    private int mCurrentDayOfMonth;
    private SimpleDateFormat dateFormat;
    private ArrayAdapter<String> monthAdapter;
    private String selectedMonth;
    private Spinner yearSpinner;
    private String selectedyear;
    private SupportPresenter presenter;
    private boolean CheckEditTextEmpty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_layout);
        setTitle("Support");
        presenter = new SupportPresenter();

        yourDeductionsTV = (TextView) findViewById(R.id.your_deductions_tv);
        enterAmountET = (EditText) findViewById(R.id.enter_amount_et);
        addButton = (Button) findViewById(R.id.add_btn);

        listView = (ListView) findViewById(R.id.list_view);

        initializeMonthlySpinner();

        intializeYearSpinner();

        adapter = new BalanceSheetAdapter(this,new ArrayList());
        listView.setAdapter(adapter);

        addButton.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }

    //Initializing Monthly Spinner
    private void initializeMonthlySpinner() {
        monthSpinner = (Spinner) findViewById(R.id.date_picker_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this,
                R.array.month_list_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        monthSpinner.setAdapter(monthAdapter);
        monthSpinner.setOnItemSelectedListener(this);
    }

    //Initialize Year Spinner
    private void intializeYearSpinner() {
        yearSpinner = (Spinner) findViewById(R.id.enter_year_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this,
                R.array.year_list_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_btn) {
            SupportTableDetails supportTableDetails = new SupportTableDetails();
            supportTableDetails.setCost(Integer.parseInt(enterAmountET.getText().toString()));
            supportTableDetails.setMonth(selectedMonth);
            supportTableDetails.setYear(selectedyear);
            adapter.addBalanceDetails(supportTableDetails);

            SQLiteDatabase database = MyDBHandler.getInstance().getWritableDatabase();
            MyDBHandler.getInstance().onCreate(database);
            MyDBHandler.getInstance().insertRecordsIntoSupportTable(supportTableDetails);
            //TODO: TRYING TO SET THE VALUE OF DEDUCTION
//            yourDeductionsTV.setText(MyDBHandler.getInstance().sumOfEntireColumn(AppConstants.COLUMN_SUPPORT_AMOUNT,
//                    AppConstants.TABLE_NAME_SUPPORT));
//        } else if (v.getId() == R.id.date_picker_spinner) {
//            //Use the current date as the default date in the picker
//            final Calendar calendar = Calendar.getInstance();
//            int year = calendar.get(Calendar.YEAR);
//            int month = calendar.get(Calendar.MONTH);
//            int day = calendar.get(Calendar.DAY_OF_MONTH);
//            new DatePickerDialog(this,this, year, month,day).show();
        }
    }

//    //Check Whether fields are filled and then insert those values in Support Table
//    private void checkFieldsareFilled(SupportTableDetails supportTableDetails) {
//
//        if(TextUtils.isEmpty(supportTableDetails.get_month()) ||
//                TextUtils.isEmpty(supportTableDetails.get_year())){
//
//            String cost = Integer.valueOf(supportTableDetails.get_cost()).toString();
//            if(TextUtils.isEmpty(cost)) {
//                CheckEditTextEmpty = false ;
//            }
//
//
//        }
//        else {
//            CheckEditTextEmpty = true ;
//        }
//    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(SupportActivity.this, "Clicked", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d(TAG,"onDateSet......year:"+year);
        this.mCurrentYear=year;
        this.mCurrentMonth=month;
        this.mCurrentDayOfMonth=dayOfMonth;
    }


    //Spinner DropDown Listeners
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        switch (parent.getId()) {
            case R.id.date_picker_spinner:
                selectedMonth = (String) parent.getItemAtPosition(position);
                break;
            case R.id.enter_year_spinner:
                selectedyear = (String) parent.getItemAtPosition(position);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback

    }

    @Override
    public void addAmountToDeductions(int amount) {
        yourDeductionsTV.setText(amount);
    }
}
