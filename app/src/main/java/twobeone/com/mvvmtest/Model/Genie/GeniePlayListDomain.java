package twobeone.com.mvvmtest.Model.Genie;

import java.util.ArrayList;

public class GeniePlayListDomain {
    private String resultCode;
    private int itemSize;
    private String resultMessage;
    private ArrayList<GeniePlayListItem> items;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public int getItemSize() {
        return itemSize;
    }

    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public ArrayList<GeniePlayListItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<GeniePlayListItem> items) {
        this.items = items;
    }
}
