package com.zyh.playerpicture.cache;


/**
 * author: luqihua
 * date:2019-07-31
 * description:
 **/
public class SPToolsImpl extends SPTools {
    private static final String DEVICE_NAME = "device_name";
    private static final String DES_KEY = "des_key";

    private static final String PREVIOUS_IP = "previous_ip";
    private static final String CHECK_UP = "check_up";
    private static final String CountDownTime = "countdown_time";

    private static final String SERVER_IP = "server_ip";
    private static final String SERVER_PORT = "server_port";


    private static final String FTP_SERVER_IP = "ftp_server_ip";
    private static final String FTP_PORT = "ftp_port";
    private static final String FTP_USERNAME = "ftp_username";
    private static final String FTP_PASSWORD = "ftp_password";
    private static final String PRE_VERSION = "pre_version";
    private static final String PRE_SYSTEM_VERSION = "pre_system_version";
    private static final String IS_SYSTEM_UPDATE = "is_system_update";
    private static final String IS_LOGIN_STATE = "is_login_state";


    /*===========*/
    public static void setDeviceName(String name) {
        getDefault().edit()
                .putString(DEVICE_NAME, name)
                .apply();
    }

    public static String getDeviceName() {
        return getDefault().getString(DEVICE_NAME, null);
    }

    /*===========*/
    public static void setDESKey(String key) {
        getDefault().edit()
                .putString(DES_KEY, key)
                .apply();
    }

    public static String getDESKey() {
        return getDefault().getString(DES_KEY, null);
    }


    /*===========*/
    public static void setServerIp(String ip) {
        getDefault().edit()
                .putString(SERVER_IP, ip)
                .apply();
    }

    public static String getServerIp() {
        return getDefault().getString(SERVER_IP, null);
    }

    /*===========*/
    public static void setServerPort(int port) {
        getDefault().edit()
                .putInt(SERVER_PORT, port)
                .apply();
    }

    public static int getServerPort() {
        return getDefault().getInt(SERVER_PORT, 3000);
    }


    /*===========*/



    public static void setCountDownTime(Long countdowntime) {
        getDefault().edit()
                .putLong(CountDownTime, countdowntime)
                .apply();
    }

    /*===========*/
    public static long getCountDownTime() {
        return getDefault().getLong(CountDownTime, 1000*60*40);
    }

    public static void setCheckUp(boolean ischeckup) {
        getDefault().edit()
                .putBoolean(CHECK_UP, ischeckup)
                .apply();
    }



    /*=====ftp======*/

    public static boolean getCheckUp() {
        return getDefault().getBoolean(CHECK_UP, false);
    }

    public static int getFtpPort() {
        return getDefault().getInt(FTP_PORT, 21);
    }

    public static String getFtpUsername() {
        return getDefault().getString(FTP_USERNAME, "anonymous");
    }

    public static String getFtpPassword() {
        return getDefault().getString(FTP_PASSWORD, "1");
    }

    public static void setFtpServerIp(String serverIp) {
        getDefault().edit()
                .putString(FTP_SERVER_IP, serverIp)
                .apply();
    }

    public static void setFtpPort(int port) {
        getDefault().edit()
                .putInt(FTP_PORT, port)
                .apply();
    }

    public static void setFtpUsername(String username) {
        getDefault().edit()
                .putString(FTP_USERNAME, username)
                .apply();
    }

    public static void setFtpPassword(String password) {
        getDefault().edit()
                .putString(FTP_PASSWORD, password)
                .apply();
    }




    //是否系统升级
    public static boolean isSystemUpdate() {
//        String currentVersion = StarnetManager.getInstance().getSystemVersion();
//        String preVersion = getDefault().getString(PRE_SYSTEM_VERSION, currentVersion);
//        getDefault().edit()
//                .putString(PRE_SYSTEM_VERSION, currentVersion)
//                .apply();
//        return preVersion != currentVersion;
        return getDefault().getBoolean(IS_SYSTEM_UPDATE,false);
    }
    public static void setisSystemUpdate(boolean is_system_update) {
        getDefault().edit()
                .putBoolean(IS_SYSTEM_UPDATE, is_system_update)
                .apply();
    }
    //是否登录成功
    public static boolean isLoginState() {
        return getDefault().getBoolean(IS_LOGIN_STATE,false);
    }
    public static void setisLoginState(boolean is_login_state) {
        getDefault().edit()
                .putBoolean(IS_LOGIN_STATE, is_login_state)
                .apply();
    }
}
