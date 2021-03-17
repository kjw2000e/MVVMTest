package twobeone.com.mvvmtest.View.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import twobeone.com.mvvmtest.Interface.OnPlayListItemClickListener;
import twobeone.com.mvvmtest.Model.Genie.GenieItem;
import twobeone.com.mvvmtest.Model.Genie.GeniePlayListItem;
import twobeone.com.mvvmtest.Model.Melon.MelonItem;
import twobeone.com.mvvmtest.R;

public class PlayListAdapter<T, G> extends RecyclerView.Adapter<PlayListAdapter.ViewHolder> {

    private String mType = "";
    private ArrayList<T> mSongList = new ArrayList<>();
    private ArrayList<G> mPlayList = new ArrayList<>();
    private OnPlayListItemClickListener mClickListener;

    private boolean isPlayDepth = false;

    public PlayListAdapter(OnPlayListItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    public void updateData(String type, boolean isPlayDepth, ArrayList<T> songList, ArrayList<G> playList) {
        this.mType = type;
        this.isPlayDepth = isPlayDepth;

        if (isPlayDepth) {
            mSongList.clear();
            mSongList.addAll(songList);
        } else {
            mPlayList.clear();
            mPlayList.addAll(playList);
        }

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        T t = null;
        G g = null;

        if (isPlayDepth) {
            t = mSongList.get(position);
            if (t != null) {
                if (t instanceof MelonItem) {
                    holder.mTvTitle.setText(((MelonItem) t).getSongName());
                } else if (t instanceof GenieItem) {
                    holder.mTvTitle.setText(((GenieItem) t).getSong_name());
                }

                holder.onBindClickListener(t, mType, isPlayDepth, mClickListener);
            }
        } else {
            g = mPlayList.get(position);
            if (g != null) {
                if (g instanceof GeniePlayListItem) {
                    holder.mTvTitle.setText(((GeniePlayListItem) g).getPlm_title());
                }

                holder.onBindClickListener(g, mType, isPlayDepth, mClickListener);
            }
        }

    }

    @Override
    public int getItemCount() {
        if (isPlayDepth) {
            return mSongList.size();
        } else {
            return mPlayList.size();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
        }

        public void onBindClickListener(Object item, String type, boolean isPlayDepth, OnPlayListItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(item, type, isPlayDepth);
                }
            });
        }
    }
}
