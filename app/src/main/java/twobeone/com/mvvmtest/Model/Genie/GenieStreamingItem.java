package twobeone.com.mvvmtest.Model.Genie;

public class GenieStreamingItem {

/*            "artist_id": 80972152,
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
                    "abm_img_path": "http%3A%2F%2Fimage.genie.co.kr%2FY%2FIMAGE%2FIMG_ALBUM%2F081%2F743%2F752%2F81743752_1605852404684_1_600x600.JPG"*/

    /*                "rownum": 1,
                        "song_id": 86992414 //추가
                        "album_name": "Rollin'", // 추가
                        "song_adlt_yn": "N", // 추가
                        "stm_yn": "Y", // 추가
                        "img_path": "http://image.genie.co.kr/Y/IMAGE/IMG_ALBUM/080/927/790/80927790_1614654739083_1_140x140.JPG"*/



    private int artist_id;
    private int album_id;
    private int duration;
    private int popularity;
    private int stream_log_second;
    private String song_name;
    private String artist_name;
    private String activity;  // 좋아요 유무
    private String stream_log;
    private String streaming_license_yn;
    private String mrstm_yn;
    private String dpmrstm_yn;
    private String resource_url;
    private String bitrate;
    private String abm_img_path;

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

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getStream_log_second() {
        return stream_log_second;
    }

    public void setStream_log_second(int stream_log_second) {
        this.stream_log_second = stream_log_second;
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

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getStream_log() {
        return stream_log;
    }

    public void setStream_log(String stream_log) {
        this.stream_log = stream_log;
    }

    public String getStreaming_license_yn() {
        return streaming_license_yn;
    }

    public void setStreaming_license_yn(String streaming_license_yn) {
        this.streaming_license_yn = streaming_license_yn;
    }

    public String getMrstm_yn() {
        return mrstm_yn;
    }

    public void setMrstm_yn(String mrstm_yn) {
        this.mrstm_yn = mrstm_yn;
    }

    public String getDpmrstm_yn() {
        return dpmrstm_yn;
    }

    public void setDpmrstm_yn(String dpmrstm_yn) {
        this.dpmrstm_yn = dpmrstm_yn;
    }

    public String getResource_url() {
        return resource_url;
    }

    public void setResource_url(String resource_url) {
        this.resource_url = resource_url;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getAbm_img_path() {
        return abm_img_path;
    }

    public void setAbm_img_path(String abm_img_path) {
        this.abm_img_path = abm_img_path;
    }
}
