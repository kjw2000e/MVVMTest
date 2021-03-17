package twobeone.com.mvvmtest;

public class AppConst {
    //
    public class StreamingType {
        public static final int STREAMING_TYPE_GENIE = 1000;
        public static final int STREAMING_TYPE_MELON = 2000;
    }

    public class Frag {
        public static final String FRAG_ID_MAIN = "MainFragment";
    }

    // genie
    public class Genie {
        public static final String PLAYLIST_TYPE_GENIE_INQUEUE = "genie_inqueue";
        public static final String PLAYLIST_TYPE_GENIE_CHART = "genie_chart";
        public static final String PLAYLIST_TYPE_GENIE_DRIVING = "genie_driving";
        public static final String PLAYLIST_TYPE_GENIE_MYLIST = "genie_mylist";
        public static final String PLAYLIST_TYPE_GENIE_LATESTMUSIC = "genie_latestmusic";
    }

    //melon
    public class Melon {
        //api
        public static final String CP_ID = "AZ62";
        public static final String CP_KEY = "16LNM2";
        public static final String LOGIN_URL = "https://m.melon.com:4554/cds/support/automelon/login_login.json";

        public static final String LOGIN_ID = "loginId";
        public static final String LOGIN_PW = "loginPw";
        public static final String LOGIN_TOKEN = "loginToken";
        public static final String LOGIN_AUTO = "loginAuto";
        public static final String QUALITY_TYPE = "qualityType";
        public static final String REPEAT_TYPE = "repeatType";
        public static final String SHUFFLE_TYPE = "shuffleType";

        public static final String SCHEME_CLOSE = "popmedia://close";
        public static final String SCHEME_AGREE = "popmedia://agree";
        public static final String SCHEME_NOT_AGREE = "popmedia://notagree";
        public static final String SCHEME_14 = "popmedia://fail14";

        //retrofit
        public static final String RECENT = "recent";
        public static final String RANKING = "ranking";
        public static final String GENRE = "genre";
        public static final String TODAY = "recommend";
        public static final String MYLIST = "mymusic";
        public static final String SEARCH = "search";

        public static final int MELON_SKIP_TIME = 3;

        //use frequency
        public static final String USE_FREQUENCY_RECENT = "use_frequency_recent";
        public static final String USE_FREQUENCY_RANK = "use_frequency_rank";
        public static final String USE_FREQUENCY_GENRE = "use_frequency_genre";
        public static final String USE_FREQUENCY_TODAY = "use_frequency_today";
        public static final String USE_FREQUENCY_MYLIST = "use_frequency_mymusic";

        //preference
        public static final String PREF_MELON_LASTMEMORY_TYPE = "prefMelonLastMemoryType";
        public static final String PREF_MELON_LASTMEMORY_TRACKID = "prefMelonLastMemoryTrackId";
        public static final String PREF_MELON_LASTMEMORY_TRACKLISTID = "prefMelonLastMemoryTrackListId";
        public static final String PREF_MELON_LASTMEMORY_PROGRESS = "prefMelonLastMemoryProgress";
    }

    public class Error {
        public static final String RESPONSE_NOT_SUCCESS = "response is not Success";
        public static final String RESPONSE_FAIL = "response is fail";
        public static final String EXOPLAYER_ONERROR_READTIMEOUT = "Read timed out";
        public static final String EXOPLAYER_ONERROR_UNABLECONNECT = "Unable to connect to";
        public static final String EXOPLAYER_ONERROR_UNEXPECTEDSTREAM = "unexpected end of stream";
        public static final String YOUTUBE_KEY_CHANGED = "youtube_key_changed";
    }
}
