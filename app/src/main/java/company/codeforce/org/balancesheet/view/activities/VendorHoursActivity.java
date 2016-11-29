package company.codeforce.org.balancesheet.view.activities;

import android.os.Bundle;

import company.codeforce.org.balancesheet.R;

/**
 * Created by ckumo on 10/27/2016.
 */
public class VendorHoursActivity extends BaseBalanceSheetActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Vendor Hours");
        setContentView(R.layout.activity_vendor_hours);
    }
}
