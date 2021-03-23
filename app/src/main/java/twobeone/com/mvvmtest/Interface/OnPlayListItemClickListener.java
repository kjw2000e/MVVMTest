package twobeone.com.mvvmtest.Interface;

import twobeone.com.mvvmtest.Model.Melon.MelonItem;

public interface OnPlayListItemClickListener {
    void onClick(Object item, int position, String type, boolean isPlayDepth);
}
