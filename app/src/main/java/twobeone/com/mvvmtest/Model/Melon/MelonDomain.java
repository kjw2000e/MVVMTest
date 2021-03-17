package twobeone.com.mvvmtest.Model.Melon;

import java.util.ArrayList;

public class MelonDomain {
    String error;
    String listType;
    ArrayList<MelonItem> content;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public ArrayList<MelonItem> getContent() {
        return content;
    }

    public void setContent(ArrayList<MelonItem> content) {
        this.content = content;
    }
}
