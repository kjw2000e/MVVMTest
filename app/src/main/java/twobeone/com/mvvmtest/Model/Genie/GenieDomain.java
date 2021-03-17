package twobeone.com.mvvmtest.Model.Genie;

import java.util.ArrayList;

public class GenieDomain {

    /*{
        "resultCode": "0",
            "itemSize": 100,
            "resultMessage": "성공",
            "items": [
        {
            "rownum": 1,
                "song_id": 86992414,
                "artist_id": 80130534,
                "album_id": 80927790,
                "duration": 198,
                "song_name": "롤린 (Rollin')",
                "artist_name": "브레이브걸스 (Brave girls)",
                "album_name": "Rollin'",
                "song_adlt_yn": "N",
                "stm_yn": "Y",
                "img_path": "http://image.genie.co.kr/Y/IMAGE/IMG_ALBUM/080/927/790/80927790_1614654739083_1_140x140.JPG"

        },
    ....*/


    private String resultCode;
    private int itemSize;
    private String resultMessage;
    private ArrayList<GenieItem> items;


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

    public ArrayList<GenieItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<GenieItem> items) {
        this.items = items;
    }
}
