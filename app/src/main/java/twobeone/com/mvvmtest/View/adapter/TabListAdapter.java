package twobeone.com.mvvmtest.View.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import twobeone.com.mvvmtest.R;
import twobeone.com.mvvmtest.databinding.ItemTabListBinding;

public class TabListAdapter extends BaseAdapter {
    private Context mContext;
    private String[] tabList;

    public TabListAdapter(Context context) {
        mContext = context;
        tabList = mContext.getResources().getStringArray(R.array.activity_genie_tab);
    }

    @Override
    public int getCount() {
        return tabList.length;
    }

    @Override
    public String getItem(int position) {
        return tabList[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tab_list, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.position = position;
            viewHolder.tvTab = convertView.findViewById(R.id.tv_tab_title);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTab.setText(tabList[position]);

        return convertView;
    }

    public class ViewHolder {
        public int position;
        public TextView tvTab;
    }
}
