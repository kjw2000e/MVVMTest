package twobeone.com.mvvmtest.Model.Melon;

public class MelonItem {
    //    "trackId": "31652857",
//            "albumName": "4.3",
//            "albumImg": "http://image.melon.com/cm/album/images/102/58/451/10258451.jpg",
//            "albumImgLarge": "http://image.melon.com/cm/album/images/102/58/451/10258451.jpg",
//            "playTime": 232,
//            "artistName": "10cm",
//            "songName": "그러나",
//            "isAdult": false,
//            "isService": true
    int id;
    String trackId = "";
    String trackListId = "";
    String albumName = "";
    String albumImg = "";
    String albumImgLarge = "";
    int playTime = 0;
    String plylstTitle = "";
    String artistName = "";
    String songName = "";
    long date;

    boolean isMyList = false;
    boolean isAdult = false;
    boolean isService = true;
    String menuId = "15010101";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrackId() {
        if (trackId == null) {
            return "";
        }
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackListId() {
        if (trackListId == null) {
            return trackListId = "";
        }
        return trackListId;
    }

    public void setTrackListId(String trackListId) {
        if (trackListId == null) {
            trackListId = "";
        }
        this.trackListId = trackListId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg;
    }

    public String getAlbumImgLarge() {
        return albumImgLarge;
    }

    public void setAlbumImgLarge(String albumImgLarge) {
        this.albumImgLarge = albumImgLarge;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getPlylstTitle() {
        return plylstTitle;
    }

    public void setPlylstTitle(String plylstTitle) {
        this.plylstTitle = plylstTitle;
    }

    public boolean isMyList() {
        return isMyList;
    }

    public void setMyList(boolean myList) {
        isMyList = myList;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public boolean isService() {
        return isService;
    }

    public void setService(boolean service) {
        isService = service;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
