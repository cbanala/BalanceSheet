package company.codeforce.org.balancesheet.model;

import android.util.Log;

/**
 * Created by ckumo on 10/28/2016.
 */

public class BalanceSheetDetails {

    private int _id;
    private String _period;
    private String _payCheckDate;
    private float _openingBalance;
    private float _rate;
    private int _hours;
    private float _credit;
    private float _debit;
    private float _endingBalance;

    //Empty Constructor
    public BalanceSheetDetails() {

    }

    //constructor without Id
    public BalanceSheetDetails(String period, String payCheckDate,
                               float openingBalance, float rate,
                               int hours, float credit, float debit, float endingBalance) {
        this._period = period;
        this._payCheckDate = payCheckDate;
        this._openingBalance = openingBalance;
        this._rate = rate;
        this._hours = hours;
        this._credit = credit;
        this._debit = debit;
        this._endingBalance = endingBalance;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getPeriod() {
        Log.d("database", "getPeriod()..." + _period);
        return _period;
    }

    public void setPeriod(String _period) {
        this._period = _period;
        Log.d("database", "setPeriod()..." + _period);
    }

    public String getPayCheckDate() {
        Log.d("database", "getPayCheckDate()..." + _payCheckDate);
        return _payCheckDate;
    }

    public void setPayCheckDate(String _payCheckDate) {
        this._payCheckDate = _payCheckDate;
        Log.d("database", "setPayCheckDate()..." + _payCheckDate);
    }

    public float getOpeningBalance() {
        Log.d("database", "getOpeningBalance()..." + _openingBalance);
        return _openingBalance;
    }

    public void setOpeningBalance(float _openingBalance) {
        this._openingBalance = _openingBalance;
        Log.d("database", "setOpeningBalance()..." + _openingBalance);
    }

    public float getRate() {
        Log.d("database", "getRate()..." + _rate);
        return _rate;
    }

    public void setRate(float _rate) {
        this._rate = _rate;
        Log.d("database", "setRate()..." + _rate);
    }

    public int getHours() {
        Log.d("database", "getHours()..." + _hours);
        return _hours;
    }

    public void setHours(int _hours) {
        this._hours = _hours;
        Log.d("database", "setHours()..." + _hours);
    }

    public float getCredit() {
        Log.d("database", "getCredit()..." + _credit);
        return _credit;
    }

    public void setCredit(float _credit) {
        this._credit = _credit;
        Log.d("database", "setCredit()..." + _credit);
    }

    public float getDebit() {
        Log.d("database", "getDebit()..." + _debit);
        return _debit;
    }

    public void setDebit(float _debit) {
        this._debit = _debit;
        Log.d("database", "setDebit()..." + _debit);
    }

    public float getEndingBalance() {
        Log.d("database", "getEndingBalance()..." + _endingBalance);
        return _endingBalance;
    }

    public void setEndingBalance(float _endingBalance) {
        this._endingBalance = _endingBalance;
        Log.d("database", "setEndingBalance()..." + _endingBalance);
    }
    
}
