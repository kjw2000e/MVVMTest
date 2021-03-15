package twobeone.com.mvvmtest;

public class GlobalStatus {
    private static boolean engineer = false;
    private static int currentWifiState;
    private static String appVersion;
    private static String appVersionCode;
    private static boolean otaProgress;
    private static boolean apkDownloadStop;

    public static void init() {
        engineer = false;
        currentViewId = 0;
        appVersion = "";
        appVersionCode = "";
        otaProgress = false;
        apkDownloadStop = false;

        MelonStatus.init();
    }

    public static boolean isEngineer() {
        return engineer;
    }

    public static void setEngineer(boolean engineer) {
        engineer = engineer;
    }

    private static int currentViewId;

    public static int getCurrentViewId() {
        return currentViewId;
    }

    public static void setCurrentViewId(int currentviewId) {
        currentViewId = currentviewId;
    }

    public static String getAppVersion() {
        return appVersion;
    }

    public static void setAppVersion(String appVersion) {
        appVersion = appVersion;
    }

    public static String getAppVersionCode() {
        return appVersionCode;
    }

    public static void setAppVersionCode(String appVersionCode) {
        appVersionCode = appVersionCode;
    }

    public static int getCurrentWifiState() {
        return currentWifiState;
    }

    public static void setCurrentWifiState(int currentWifiState) {
        currentWifiState = currentWifiState;
    }

    public static void setDeviceType(String deviceType) {
        deviceType = deviceType;
    }

    public static boolean isOtaProgress() {
        return otaProgress;
    }

    public static boolean isApkDownloadStop() {
        return apkDownloadStop;
    }

    public static void setApkDownloadStop(boolean apkDownloadStop) {
        apkDownloadStop = apkDownloadStop;
    }
}
