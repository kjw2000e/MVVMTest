package twobeone.com.mvvmtest.Model.Genie;

public class GeniePlayListItem {
  /*              "plm_seq": 10949,
                        "favorite_cnt": 10,
                        "song_cnt": 30,
                        "view_cnt": 3349,
                        "listen_cnt": 269,
                        "plm_title": "[오아추] 21세기 힙합 대통령 'Drake'의 빌보드 입성 노래들",
                        "disp_dt": "20210317",
                        "img_path": "http://image.genie.co.kr/Y/IMAGE/Playlist/Channel/GENIE/PLAYLIST_20210317150325.jpg",
                        "tags": [
    {
        "tag_code": "SVC008",
            "tag_name": "오아추"
    },
    {
        "tag_code": "DJ0001",
            "tag_name": "DJ 지니"
    },
    {
        "tag_code": "GR0006",
            "tag_name": "랩/힙합"
    },
    {
        "tag_code": "GR0003",
            "tag_name": "R&amp;B/소울"
    },
    {
        "tag_code": "ST0002",
            "tag_name": "드라이브"
    },
    {
        "tag_code": "ST0009",
            "tag_name": "밤"
    }
            ]
},*/

    private int plm_seq;
    private int favorite_cnt;
    private int song_cnt;
    private int view_cnt;
    private int listen_cnt;
    private String plm_title;
    private String disp_dt;
    private String img_path;

    public int getPlm_seq() {
        return plm_seq;
    }

    public void setPlm_seq(int plm_seq) {
        this.plm_seq = plm_seq;
    }

    public int getFavorite_cnt() {
        return favorite_cnt;
    }

    public void setFavorite_cnt(int favorite_cnt) {
        this.favorite_cnt = favorite_cnt;
    }

    public int getSong_cnt() {
        return song_cnt;
    }

    public void setSong_cnt(int song_cnt) {
        this.song_cnt = song_cnt;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public int getListen_cnt() {
        return listen_cnt;
    }

    public void setListen_cnt(int listen_cnt) {
        this.listen_cnt = listen_cnt;
    }

    public String getPlm_title() {
        return plm_title;
    }

    public void setPlm_title(String plm_title) {
        this.plm_title = plm_title;
    }

    public String getDisp_dt() {
        return disp_dt;
    }

    public void setDisp_dt(String disp_dt) {
        this.disp_dt = disp_dt;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
