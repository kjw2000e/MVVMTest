package twobeone.com.mvvmtest.Model.Genie;

import java.util.ArrayList;

public class GenieRecomInfo {
    /*
            "info": {
        "plm_seq": 10949,
                "favorite_cnt": 11,
                "song_cnt": 30,
                "view_cnt": 5533,
                "listen_cnt": 409,
                "plt_seq": 8,
                "maker_seq": 1,
                "plm_title": "[오아추] 21세기 힙합 대통령 'Drake'의 빌보드 입성 노래들",
                "disp_dt": "20210317",
                "img_path": "http://image.genie.co.kr/Y/IMAGE/Playlist/Channel/GENIE/PLAYLIST_20210317150325.jpg",
                "plt_name": "오아추",
                "plm_content": "오늘의 아티스트 추천! 세계에서 가장 많은 히트곡을 보유한 차트 절대 강자, 래퍼 드레이크! 이번엔 신곡 발표와 동시에 빌보드 차트 '핫 100' 1~3위에 오르는 최초의 기록을 세웠습니다! 드레이크의 빌보드 진입곡 하이라이트를 들어보세요~!",
                "maker_name": "DJ 지니",
                "reg_dt": "2021-03-17 오전 11:30:53",
                "favorite": false,
                "song": {
                    "count": 30,
                    "items": [
                          {
                             "duration": 178,
                            "song_id": 92527816,
                            "artist_name": "Drake",
                            "album_name": "Scary Hours 2",
                            "album_img_path": "http://image.genie.co.kr/Y/IMAGE/IMG_ALBUM/081/933/386/81933386_1614922059148_1_140x140.JPG",
                            "song_adlt_yn": "N",
                            "song_name": "What's Next",
                            "album_id": 81933386,
                            "stm_yn": "Y",
                            "artist_id": 79929677
            },*/


//    private int plm_seq;
//    private int favorite_cnt;
//    private int song_cnt;
//    private int view_cnt;
//    private int listen_cnt;
//    private int plt_seq;
//    private int maker_seq;
//    private String plm_title;
//    private String disp_dt;
//    private String img_path;
//    private String plt_name;

    private SongItem song;

    public SongItem getSong() {
        return song;
    }

    public void setSong(SongItem song) {
        this.song = song;
    }

    public class SongItem {
        private int count;
        private ArrayList<GenieItem> items;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public ArrayList<GenieItem> getItems() {
            return items;
        }

        public void setItems(ArrayList<GenieItem> items) {
            this.items = items;
        }

    }

}
