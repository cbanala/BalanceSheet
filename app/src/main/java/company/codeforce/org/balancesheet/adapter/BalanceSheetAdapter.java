package company.codeforce.org.balancesheet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import company.codeforce.org.balancesheet.R;
import company.codeforce.org.balancesheet.model.SupportTableDetails;
import company.codeforce.org.balancesheet.presenter.SupportPresenter;

/**
 * Created by ckumo on 10/25/2016.
 */

public class BalanceSheetAdapter extends BaseAdapter {

    private final Context mContext;
    private final SupportPresenter supportPresenter;
    private List<SupportTableDetails> mBalanceDetailList;
    private ViewHolder mViewHolder;



    public BalanceSheetAdapter(Context mContext, List<SupportTableDetails> supportTableDetails) {
        this.mContext = mContext;
        mBalanceDetailList = new ArrayList();
        mBalanceDetailList.addAll(supportTableDetails);
        supportPresenter = new SupportPresenter();
    }

    @Override
    public int getCount() {
        return mBalanceDetailList.size();
    }

    @Override
    public SupportTableDetails getItem(int position) {
        return mBalanceDetailList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.single_list_item,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        mViewHolder = (ViewHolder) convertView.getTag();

        SupportTableDetails item = getItem(position);

        mViewHolder.monthItemTV.setText("" + item.getMonth() + "'" +item.getYear());
        mViewHolder.moneyItemTV.setText("$" +item.getCost());

        return convertView;
    }


    public void addBalanceDetails(SupportTableDetails supportTableDetails) {
            mBalanceDetailList.add(supportTableDetails);
            notifyDataSetChanged();
    }

    static class ViewHolder {

        private final TextView monthItemTV;
        private final TextView moneyItemTV;

        public ViewHolder(View convertView) {
            monthItemTV = (TextView) convertView.findViewById(R.id.left_item);
            moneyItemTV = (TextView) convertView.findViewById(R.id.right_item);
        }
    }
}
