package twobeone.com.mvvmtest;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.WindowManager;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    private static Application sApplication;

    public static String getCookie() {
        String cookie = "";

        cookie += "PCID=" + MelonStatus.getPcid() + ";";
        if (MelonStatus.getCookies() != null && MelonStatus.getCookies().size() > 0) {
            for (int i = 0; i < MelonStatus.getCookies().size(); i++) {
                cookie += " " + MelonStatus.getCookies().get(i) + ";";
            }
        }

        Log.e("kjw333", "cookie : " + cookie);

        try {
            String urlencode = URLEncoder.encode(cookie, "utf-8");
            Log.e("kjw333", "encode cookie : " + urlencode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return cookie;
    }

    public static Map<String, String> makeExtraParams() {
        Map<String, String> params = new HashMap<>();

        try {
            params.put("userAgent", makeUserAgent());
        } catch (Exception e) {
            Log.e("SG2", "makeExtraParams Build.VERSION.RELEASE : ", e);
        }
        params.put("cpid", AppConst.Melon.CP_ID);
        params.put("cpkey", AppConst.Melon.CP_KEY);
        params.put("cookie", Utils.getCookie());

        return params;
    }

    public static String makeUserAgent() {
        String osVersion = "Android " + Build.VERSION.RELEASE;
        return AppConst.Melon.CP_ID + "; " + osVersion + "; " + MelonStatus.getAppVersion() + "; " + MelonStatus.getPhoneModel();
    }
}
