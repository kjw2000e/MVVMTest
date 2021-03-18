package twobeone.com.mvvmtest.Model.Genie;

public class GenieItem {

    /*
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
    */
    public static final String VALUE_Y = "Y";
    public static final String VALUE_N = "N";
    public static final String VALUE_FALSE = "false";
    public static final String VALUE_TRUE = "true";

    private int rownum;
    private int song_id;
    private int artist_id;
    private int album_id;
    private int duration;
    private String song_name;
    private String artist_name;
    private String album_name;
    private String song_adlt_yn;
    private String stm_yn;
    private String img_path;

    private GenieStreamingItem streamingItem;

    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getSong_adlt_yn() {
        return song_adlt_yn;
    }

    public void setSong_adlt_yn(String song_adlt_yn) {
        this.song_adlt_yn = song_adlt_yn;
    }

    public String getStm_yn() {
        return stm_yn;
    }

    public void setStm_yn(String stm_yn) {
        this.stm_yn = stm_yn;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public GenieStreamingItem getStreamingItem() {
        return streamingItem;
    }

    public void setStreamingItem(GenieStreamingItem streamingItem) {
        this.streamingItem = streamingItem;
    }
}
