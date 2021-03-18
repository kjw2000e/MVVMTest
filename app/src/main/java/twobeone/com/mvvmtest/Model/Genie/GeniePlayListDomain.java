package twobeone.com.mvvmtest.Model.Genie;

import java.util.ArrayList;

public class GeniePlayListDomain extends BaseDomain{
    private int itemSize;
    private ArrayList<GeniePlayListItem> items;

    public int getItemSize() {
        return itemSize;
    }

    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }

    public ArrayList<GeniePlayListItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<GeniePlayListItem> items) {
        this.items = items;
    }
}
