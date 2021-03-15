package twobeone.com.mvvmtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import twobeone.com.mvvmtest.Interface.OnMelonItemClickListener;
import twobeone.com.mvvmtest.Model.MelonItem;

public class MelonChartAdapter extends RecyclerView.Adapter<MelonChartAdapter.ViewHolder> {

    private ArrayList<MelonItem> mChartList = new ArrayList<>();
    private OnMelonItemClickListener mClickListener;

    public MelonChartAdapter(OnMelonItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    public void UpdateData(ArrayList<MelonItem> chartList) {
        clearData();
        this.mChartList = chartList;
        notifyDataSetChanged();
    }

    public void clearData() {
        mChartList.clear();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTvTitle.setText(mChartList.get(position).getSongName());
        holder.onBindClickListener(mChartList.get(position), mClickListener);
    }

    @Override
    public int getItemCount() {
        return mChartList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
        }

        public void onBindClickListener(MelonItem item, OnMelonItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(item);
                }
            });
        }
    }
}
