package twobeone.com.mvvmtest;

import java.util.ArrayList;

public class MelonStatus {
    private static String session_id = "";
    private static String member_key = "0";
    private static String loggingToken = "";
    private static String bitrate = "";
    private static String isChangeST = "N";  //Y, N
    private static String cid = "";
    private static String menuid = "";
    private static String ctype = "";
    private static String mdn = "0000";
    private static String pcid = "";
    private static String appVersion = "";
    private static String deviceId = "";
    private static String phoneModel = "";
    private static String metaType = "SKM";
    private static ArrayList<String> cookies = null;

    public static void init() {
        session_id = "";
        member_key = "0";
        loggingToken = "";
        bitrate = "";
        isChangeST = "N";
        cid = "";
        menuid = "";
        ctype = "";
        mdn = "0000";
        pcid = "";
        appVersion = "";
        deviceId = "";
        phoneModel = "";
        metaType = "SKM";
        cookies = null;
    }

    public static String getSession_id() {
        return session_id;
    }

    public static void setSession_id(String session_id) {
        MelonStatus.session_id = session_id;
    }

    public static String getMember_key() {
        return member_key;
    }

    public static void setMember_key(String member_key) {
        MelonStatus.member_key = member_key;
    }

    public static String getLoggingToken() {
        return loggingToken;
    }

    public static void setLoggingToken(String loggingToken) {
        MelonStatus.loggingToken = loggingToken;
    }

    public static String getBitrate() {
        return bitrate;
    }

    public static void setBitrate(String bitrate) {
        MelonStatus.bitrate = bitrate;
    }

    public static String getIsChangeST() {
        return isChangeST;
    }

    public static void setIsChangeST(String isChangeST) {
        MelonStatus.isChangeST = isChangeST;
    }

    public static String getCid() {
        return cid;
    }

    public static void setCid(String cid) {
        MelonStatus.cid = cid;
    }

    public static String getMenuid() {
        return menuid;
    }

    public static void setMenuid(String menuid) {
        MelonStatus.menuid = menuid;
    }

    public static String getCtype() {
        return ctype;
    }

    public static void setCtype(String ctype) {
        MelonStatus.ctype = ctype;
    }

    public static String getMdn() {
        return mdn;
    }

    public static void setMdn(String mdn) {
        MelonStatus.mdn = mdn;
    }

    public static String getPcid() {
        return pcid;
    }

    public static void setPcid(String pcid) {
        MelonStatus.pcid = pcid;
    }

    public static String getAppVersion() {
        return appVersion;
    }

    public static void setAppVersion(String appVersion) {
        MelonStatus.appVersion = appVersion;
    }

    public static String getDeviceId() {
        return deviceId;
    }

    public static void setDeviceId(String deviceId) {
        MelonStatus.deviceId = deviceId;
    }

    public static String getPhoneModel() {
        return phoneModel;
    }

    public static void setPhoneModel(String phoneModel) {
        MelonStatus.phoneModel = phoneModel;
    }

    public static String getMetaType() {
        return metaType;
    }

    public static void setMetaType(String metaType) {
        MelonStatus.metaType = metaType;
    }

    public static ArrayList<String> getCookies() {
        return cookies;
    }

    public static void setCookies(ArrayList<String> cookies) {
        MelonStatus.cookies = cookies;
    }

    public static void addCookies(String cookie) {
        if (cookies == null) {
            cookies = new ArrayList<>();
        }

        MelonStatus.cookies.add(cookie);
    }
}
