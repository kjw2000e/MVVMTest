package twobeone.com.mvvmtest.Model.Genie;

import java.util.ArrayList;

public class GenieStreamingDomain extends BaseDomain{

     /*   "resultCode": "0",
                "itemSize": 1,
                "resultMessage": "성공",
                "items": [
    {
        "artist_id": 80972152,
            "album_id": 81743752,
            "duration": 335,
            "popularity": 32721,
            "stream_log_second": 60,
            "song_name": "VVS (Feat. JUSTHIS) (Prod. by GroovyRoom)",
            "artist_name": "미란이 (Mirani) & 먼치맨 (MUNCHMAN) & Khundi Panda & 머쉬베놈 (MUSHVENOM)",
            "activity": "false",
            "stream_log": "",
            "streaming_license_yn": "N",
            "mrstm_yn": "N",
            "dpmrstm_yn": "N",
            "resource_url": "http://giicdn.genie.co.kr/ND_F/SONG/MP3/091/423/91423133_192k.mp3?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkdXJhdGlvbiI6NjAsInBhdGgiOiIvTkRfRi9TT05HL01QMy8wOTEvNDIzLzkxNDIzMTMzXzE5MmsubXAzIiwiaXNzIjoiZ3AiLCJleHAiOjE2MTYwMzA4MTYsInBsYXlzdGFydCI6MH0.T2efwF38fUB2uyHgmnDI8oupQEaL0X1na0kPyQ7zGZw",
            "bitrate": "192",
            "abm_img_path": "http%3A%2F%2Fimage.genie.co.kr%2FY%2FIMAGE%2FIMG_ALBUM%2F081%2F743%2F752%2F81743752_1605852404684_1_600x600.JPG"
    }
    ]*/


    private int itemSize;
    private ArrayList<GenieStreamingItem> items;

    public int getItemSize() {
        return itemSize;
    }

    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }

    public ArrayList<GenieStreamingItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<GenieStreamingItem> items) {
        this.items = items;
    }
}
