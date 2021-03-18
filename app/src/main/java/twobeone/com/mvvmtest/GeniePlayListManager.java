package twobeone.com.mvvmtest;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import twobeone.com.mvvmtest.Model.Genie.GenieItem;

public class GeniePlayListManager {
    private static final String TAG = "PlayListManager";

    // 클래스가 로딩될때 먼저 인스턴스 생성
    private static final GeniePlayListManager manager = new GeniePlayListManager();

    public static final int PLAY_CATEGORY_NONE = -1;
    public static final int PLAY_CATEGORY_INQUEUE = 0;
    public static final int PLAY_CATEGORY_CHART = 1;
    public static final int PLAY_CATEGORY_DRIVING = 2;
    public static final int PLAY_CATEGORY_MYLIST = 3;
    public static final int PLAY_CATEGORY_RECOMMEND = 4;
    public static final int PLAY_CATEGORY_FAVORITE = 5;
    public static final int PLAY_CATEGORY_RECENTLY = 6;
    public static final int PLAY_CATEGORY_SEARCH = 7;

    private int requestIndex = 0;
    private int downloadrequestIndex = 0;
    private int curPlayPosition = 0;
    private int curIndex = -1;
    private int curSuffleIndex = 0;
    private int tempSuffleIndex = 0;

    private List<GenieItem> mPlayList = null;
    private int curPlayCategory = PLAY_CATEGORY_NONE;
    private String curPlaySubID = null;

    private ArrayList<Integer> mShuffleList;

    private boolean isRepeatAll = true;
    private boolean isShuffle = false;

    private static Bitmap thumbImage;
    private static String thumbUrl;

    private boolean isLastMemory = false;
    private String mLastMemorySongid = null;
    private String mLastMemorySongName = null;
    private String mLastMemoryArtistName = null;
    //    private static int lastMemoryTime = 0;
    private int mLastMemoryCategory = -1;
    private String mLastMemorySubId = null;

    private String mLyrics = null;
    private boolean isNoLyrics = false;

    public static GeniePlayListManager getInstance() {
        return manager;
    }

    public int getCurPlayPosition() {
        return curPlayPosition;
    }

    public void setCurPlayPosition(int position) {
        curPlayPosition = position;
    }

    public int getCurIndex() {
        return curIndex;
    }

    public void setCurIndex(int cur) {
        curIndex = cur;
        mLyrics = null;
        isNoLyrics = false;
    }

    public void setCurShuffleIndex(int position) {
        curSuffleIndex = position;
    }

    public List<GenieItem> getPlayList() {
        if (mPlayList == null) {
            mPlayList = new ArrayList<GenieItem>();
        }

        return mPlayList;
    }

    public void setPlayList(int position, List<GenieItem> playList) {
        setCurIndex(position);
        setPlayList(playList);
    }

    public void setPlayList(List<GenieItem> playList) {
        if (mPlayList == null) {
            mPlayList = new ArrayList<GenieItem>();
        } else {
            mPlayList.clear();
        }
        if (mShuffleList == null) {
            mShuffleList = new ArrayList<Integer>();
        } else {
            mShuffleList.clear();
        }

        if (playList != null) {
            mPlayList.addAll(playList);
            setShuffleList();
        }
    }

    public void replacePlayData(boolean isPlayable) { // url 이 없가나 play 불가능한 경우 stm_yn 값 변경 후 다시 추가?
        GenieItem song = getCurItem();
        if (isPlayable) {
            song.setStm_yn(GenieItem.VALUE_Y);
        } else {
            song.setStm_yn(GenieItem.VALUE_N);
        }
        replacePlayData(song);
    }

    public void deleteCurrentItem() {
        Log.d(TAG, "deleteCurrentItem()");

        if (mPlayList != null && mPlayList.size() > 0) {
            mPlayList.remove(getCurIndex());
        }
    }

    public boolean replacePlayData(GenieItem data) {
        Log.d(TAG, "replacePlayData()");
        boolean result = false;
        if (mPlayList == null) {
            mPlayList = new ArrayList<GenieItem>();
        }
        if (mShuffleList == null) {
            mShuffleList = new ArrayList<Integer>();
        }

        if (data != null) {
            int songId = data.getSong_id();
            Log.i(TAG, "data.getSong_id() " + songId + " data.getSongName() " + data.getSong_name());

            GenieItem curSong = getCurItem();
            if (curSong != null) {
                int curId = curSong.getSong_id();
                if (curId == songId) {
                    mPlayList.remove(curIndex);
                    mPlayList.add(curIndex, data);
                    return true;
                }
            }

            for (int i = 0; i < mPlayList.size(); i++) {
                GenieItem song = mPlayList.get(i);

                if (song.getSong_id() == songId) {
                    Log.i(TAG, "song.getSong_id() " + song.getSong_id());
                    mPlayList.remove(i);
                    mPlayList.add(i, data);
                    result = true;
                    break;
                }
            }

        }

        return result;
    }

    public boolean isRepeatAll() {
        return isRepeatAll;
    }

    public void setRepeatAll(boolean repeatAll) {
        isRepeatAll = repeatAll;
    }

    public boolean isShuffle() {
        return isShuffle;
    }

    public void setShuffle(boolean shuffle) {
        isShuffle = shuffle;
    }

    public boolean isFavorite() {
        GenieItem song = getCurItem();

        int favorite = 0;

        if (song != null) {
            if (song.getStreamingItem() != null) {

                if (song.getStreamingItem().getActivity().equals("true")) {
                    favorite = 1;
                }
            }

        }

        return favorite == 1;
    }

    public void isFavorite(boolean isFavorite) {
        String favorite = "false";

        if (isFavorite) {
            favorite = "true";
        }

        if (mPlayList.size() > 0) {
            GenieItem song = mPlayList.get(getCurIndex());

            if (song != null) {
                if (song.getStreamingItem() != null) {
                    song.getStreamingItem().setAbm_img_path(favorite);
                }
            }
        }
    }

    public void setShuffleList() {
        int list_size = 0;
        mShuffleList = new ArrayList<Integer>();

        list_size = mPlayList.size();

        if (curIndex >= list_size || curIndex < 0) {
            curIndex = 0;
        }

        for (int i = 0; i < list_size; i++) {
            if (i != curIndex)//skip current play
                mShuffleList.add(i);
        }

        list_size = mShuffleList.size();
        while (1 <= --list_size) {
            int random_index = (int) (Math.random() * (list_size + 1));
            int temp = mShuffleList.get(random_index);
            mShuffleList.set(random_index, mShuffleList.get(list_size));
            mShuffleList.set(list_size, temp);
        }

        mShuffleList.add(0, curIndex);
        setCurShuffleIndex(0);
    }

    public int getCurPlayCategory() {
        return curPlayCategory;
    }

    public void setCurPlayCategory(int category) {
        curPlayCategory = category;
        curPlaySubID = null;

        if (category == PLAY_CATEGORY_NONE) {
            curIndex = -1;
            curPlayPosition = -1;
        }
    }

    public void setCurPlayCategory(int category, String subid) {
        curPlayCategory = category;
        curPlaySubID = subid;

        if (category == PLAY_CATEGORY_NONE) {
            curIndex = -1;
            curPlayPosition = -1;
            curPlaySubID = null;
        }
    }

    public String getCurPlaySubID() {
        return curPlaySubID;
    }

    public void setCurPlaySubID(String subId) {
        curPlaySubID = subId;
    }

    public GenieItem getItem(int position) {
        if (mPlayList == null || position < 0 || getCount() <= position) {
            return null;
        }

        return mPlayList.get(position);
    }

    public GenieItem getCurItem() {
        if (mPlayList == null || getCurIndex() < 0 || getCount() <= getCurIndex()) {
            return null;
        }

        return mPlayList.get(getCurIndex());
    }

    public int getCount() {
        if (mPlayList == null) {
            mPlayList = new ArrayList<GenieItem>();
        }

        return mPlayList.size();
    }

    public boolean isInsertCategory() {
        if (curPlayCategory == PLAY_CATEGORY_RECENTLY) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isLastMemory() {
        return isLastMemory;
    }

    public void setIsLastMemory(boolean lastMemory) {
        isLastMemory = lastMemory;
    }

    public void setLastMemorySongid(String lastMemorySongid) {
        mLastMemorySongid = lastMemorySongid;
    }

    public String getLastMemorySongName() {
        return mLastMemorySongName;
    }

    public void setLastMemorySongName(String lastMemorySongName) {
        mLastMemorySongName = lastMemorySongName;
    }

    public String getLastMemoryArtistName() {
        return mLastMemoryArtistName;
    }

    public void setLastMemoryArtistName(String lastMemoryArtistName) {
        mLastMemoryArtistName = lastMemoryArtistName;
    }

    public int getLastMemoryCategory() {
        return mLastMemoryCategory;
    }

    public void setLastMemoryCategory(int lastMemoryCategory) {
        mLastMemoryCategory = lastMemoryCategory;
    }

    public String getLastMemorySubId() {
        return mLastMemorySubId;
    }

    public void setLastMemorySubId(String lastMemorySubId) {
        mLastMemorySubId = lastMemorySubId;
    }

    public void setlastmemoryIndex(List<GenieItem> list) {
        if (list != null) {
            if (getCount() < 1 || mLastMemorySongid == null || mLastMemorySongid.length() < 1) {
                Log.w(TAG, "setlastmemoryIndex not ready");
            } else {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (mLastMemorySongid.equals(list.get(i).getSong_id())) {
                        curIndex = i;
                        setShuffleList();
                        return;
                    }
                }
            }
        }

        curIndex = 0;
        setShuffleList();
    }

    public String getLyrics() {
        return mLyrics;
    }

    public void setLyrics(String lyrics, boolean b) {
        mLyrics = lyrics;
        isNoLyrics = b;
    }

    public boolean isNoLyrics() {
        return isNoLyrics;
    }

    public int getDownloadrequestIndex() {
        return downloadrequestIndex;
    }

    public void setDownloadRequestIndex() {
        downloadrequestIndex = curIndex;
    }

    public int getRequestIndex() {
        return requestIndex;
    }

    public void setRequestIndex() {
        requestIndex = curIndex;
    }

    public boolean getNextPlayPosition() {
        Log.d(TAG, "getNextPlayPosition()");
        if (mPlayList == null) {
            return false;
        }

        for (int i = 0; i < getCount(); i++) {
            setCurIndex(getAvailPosition(1));
            setCurShuffleIndex(tempSuffleIndex);

            if (isPlayable(curIndex)) {
                return true;
            }
        }

        return false;
    }

    public boolean getNextPlayPositionFromStart() {
        Log.d(TAG, "getNextPlayPositionFromStart()");
        if (mPlayList == null) {
            return false;
        }

        for (int i = getCurIndex(); i < getCount(); i++) {
            setCurIndex(i);
            setCurShuffleIndex(tempSuffleIndex);

            if (isPlayable(curIndex)) {
                return true;
            }
        }

        for (int i = 0; i < getCurIndex(); i++) {
            setCurIndex(i);
            setCurShuffleIndex(tempSuffleIndex);

            if (isPlayable(curIndex)) {
                return true;
            }
        }

        return false;
    }

    public boolean getPrevPlayPosition() {
        Log.d(TAG, "getPrevPlayPosition()");
        if (mPlayList == null) {
            return false;
        }

        for (int i = 0; i < getCount(); i++) {
            setCurIndex(getAvailPosition(0));
            setCurShuffleIndex(tempSuffleIndex);

            if (isPlayable(curIndex)) {
                return true;
            }
        }

        return false;
    }

    public int getAvailPosition(int op) {
        int avail = 0;
        int playmode = getPlaymode();
        tempSuffleIndex = curSuffleIndex;

        if (op == 0) {
            if (playmode == 0 || playmode == 1) {
                int prev = getCurIndex() - 1;
                if (prev < 0) {
                    avail = mPlayList.size() - 1;
                } else {
                    avail = prev;
                }
            } else {
                int prev = tempSuffleIndex - 1;
                if (prev < 0) {
                    setShuffleList();//refresh list
                    tempSuffleIndex = mShuffleList.size() - 1;
                    avail = mShuffleList.get(tempSuffleIndex);
                } else {
                    tempSuffleIndex = prev;
                    avail = mShuffleList.get(prev);
                }
            }
        } else {
            if (playmode == 0 || playmode == 1) {
                int next = getCurIndex() + 1;
                if (next == mPlayList.size()) {
                    avail = 0;
                } else {
                    avail = next;
                }
            } else {

                int next = tempSuffleIndex + 1;
                if (next == mShuffleList.size()) {
                    setShuffleList();//refresh list
                    if (next == 1) {
                        tempSuffleIndex = 0;
                    } else {
                        tempSuffleIndex = 1;
                    }
                    avail = mShuffleList.get(tempSuffleIndex);
                } else {
                    tempSuffleIndex = next;
                    avail = mShuffleList.get(next);
                }
            }
        }
        return avail;
    }

    /**
     * 0:전체반복 1:한곡반복 2:임의재생 3:한곡반복,임의재생
     **/
    public int getPlaymode() {
        if (isRepeatAll) {
            if (isShuffle != true) {
                return 0;
            } else {
                return 2;
            }
        } else {
            if (isShuffle != true) {
                return 1;
            } else {
                return 3;
            }
        }
    }

    public Bitmap getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(Bitmap bitmap) {
        this.thumbImage = bitmap;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String url) {
        this.thumbUrl = url;
    }

    public void initLastMemoryInfo() {
        Log.d(TAG, "initLastMemoryInfo");
        isLastMemory = false;
        mLastMemorySongid = null;
        mLastMemoryCategory = -1;
        mLastMemorySubId = null;
    }

    public boolean isPlayable(int position) {
        GenieItem song = getItem(position);

        if (song != null) {
            if (song.getStm_yn().equals(GenieItem.VALUE_Y)) {
                Log.i(TAG, "isPlayable [" + position + "] return true");
                return true;
            }
        }

        return false;
    }

    public boolean isPlayable() {
        return isPlayable(curIndex);
    }

    // 좋아요 목록 삭제
    public void removePlayListViaId(List<String> ids, boolean isDelete) {
        Log.e(TAG, "removePlayList() " + isDelete);
        if (ids != null) {
            GenieItem curSong = null;

            curSong = getCurItem();

            for (String str : ids) {
                Log.e(TAG, "str " + str);
                for (int i = 0; i < mPlayList.size(); i++) {
                    GenieItem song = mPlayList.get(i);

                    Log.e(TAG, "song.getSong_id() " + song.getSong_id());
                    if (str.equals(String.valueOf(song.getSong_id()))) {
                        if (isDelete) {
                            mPlayList.remove(i);
                        } else {
                            mPlayList.get(i).getStreamingItem().setActivity(GenieItem.VALUE_FALSE);
                        }
                        break;
                    }
                }
            }

            checkCurrItemViaId(curSong);
        }
    }

    public void removePlayListViaId(String id, boolean isDelete) {
        Log.e(TAG, "removePlayList() " + isDelete);
        if (id != null) {
            GenieItem curSong = null;

            curSong = getCurItem();

            for (int i = 0; i < mPlayList.size(); i++) {
                GenieItem song = mPlayList.get(i);

                Log.e(TAG, "song.getSong_id() " + song.getSong_id());
                if (id.equals(String.valueOf(song.getSong_id()))) {
                    if (isDelete) {
                        mPlayList.remove(i);
                    } else {
                        mPlayList.get(i).getStreamingItem().setActivity(GenieItem.VALUE_FALSE);
                    }
                    break;
                }
            }

            checkCurrItemViaId(curSong);
        }
    }

    private void checkCurrItemViaId(GenieItem song) {
        Log.e(TAG, "checkCurrItemViaId()");
        setShuffleList();

        Log.e(TAG, "song.getSong_id() " + song.getSong_id());

        for (int i = 0; i < mPlayList.size(); i++) {
            Log.e(TAG, "mPlayList.get(i).getSong_id() " + mPlayList.get(i).getSong_id());
            if (song.getSong_id() == mPlayList.get(i).getSong_id()) {
                setCurIndex(i);
                break;
            }
        }
    }
}
