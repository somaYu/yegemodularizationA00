package material.com.base.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import material.com.base.impl.SettingImpl;

/**
 * Created by cangwang on 2017/8/15.
 */

public class MetaUtil {
    public static int channelNum = 0;
    public static String settingInfo = "";

    /**
     * 获取meta-data值
     *
     * @param context
     * @param metatName key名
     * @return
     */
    public static Object getMetaData(Context context, String metatName) {
        Object obj = null;
        try {
            if (context != null) {
                String pkgName = context.getPackageName();
                ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(pkgName, PackageManager.GET_META_DATA);
                obj = appInfo.metaData.get(metatName);
            }
        } catch (Exception e) {
            Log.e("MetaUtil", e.toString());
        } finally {
            return obj;
        }
    }

    /**
     * 获取渠道号
     *
     * @param context
     * @return
     */
    public static int getChannelNum(Context context) {
        if (channelNum <= 0) {
            Object obj = MetaUtil.getMetaData(context, "channel");
            if (obj != null && obj instanceof Integer) {
                return (int) obj;
            }
        }
        return channelNum;
    }

    /**
     * 获取设置信息类路径
     *
     * @param context
     * @return
     */
    public static String getSettingInfo(Context context) {
        if (settingInfo == null) {
            Object obj = MetaUtil.getMetaData(context, "setting_info");
            if (obj != null && obj instanceof Integer) {
                return (String) obj;
            }
        }
        return settingInfo;
    }

    /**
     * 全局调用设置数据接口
     *
     * @param context 上下文
     * @param data    数据
     */
    public static void settingData(Context context, String data) {
        if (getSettingInfo(context) == null) {
            Log.e("AppMetautil", "setting_info is not found");
        }
        try {
            Class<?> clz = Class.forName(getSettingInfo(context));
            SettingImpl impl = (SettingImpl) clz.newInstance();
            impl.setData(data);
        } catch (ClassNotFoundException e) {
            Log.e("AppMetautil", "getSettingInfo error =" + e.toString());
        } catch (InstantiationException e) {
            Log.e("AppMetautil", "getSettingInfo error =" + e.toString());
        } catch (IllegalAccessException e) {
            Log.e("AppMetautil", "getSettingInfo error =" + e.toString());
        }
    }
}
