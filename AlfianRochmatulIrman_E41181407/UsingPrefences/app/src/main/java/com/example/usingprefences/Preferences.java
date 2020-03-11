package com.example.usingprefences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
//    Pendeklarasian key-data berupa String, untuk sebagai wadah penyimpanan data.
//    Jadi setiap data mempunyai key yang berbeda satu sama lain

    static final String KEY_USER_TEREGISTER = "user", KEY_PASS_TEREGISTER = "pass";
    static final String KEY_USERNAME_SEDANG_LOGIN = "Username_logged_in";
    static final String KEY_STATUS_SEDANG_LOGIN = "Status_logged_in";

//    Pendeklarasian shared preferences yang berdasarkan parameter context
    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

//    Deklarasi Edit preferences dan mengubah data
//    yang memiliki key isi KEY_USER_TEREGISTER dengan parameter username

    public static void setRegisteredUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_TEREGISTER, username);
        editor.apply();
    }

//    Mengembalikan nilai dari key KEY_USER_TEREGISTER berupa String
    public static String getRegisteredUser(Context context){
        return getSharedPreference(context).getString(KEY_USER_TEREGISTER, "");
    }

//    deklarasi edit pereferences dan mengubah data
//    yang memiliki key KEY_PASS_TEREGISTER dengan parameter password

    public static void setRegisterPass(Context context, String password){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_PASS_TEREGISTER, password);
        editor.apply();
    }

//    Mengembalikan nilai dari key KEY_PASS_TEREGISTER berupa String
    public static String getRegisteredPass(Context context){
        return getSharedPreference(context).getString(KEY_PASS_TEREGISTER, "");
    }

//    deklarasi edit preferences dan mengubah data
//    yang memiliki key KEY_USERNAME_SEDANG_LOGIN dengan parameter username

    public static void setLoggedInUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME_SEDANG_LOGIN, username);
        editor.apply();
    }

//    Mengembalikan nilai dari key KEY_USERNAME_SEDANG_LOGIN berupa String
    public static String getLoggedInUser(Context context){
        return getSharedPreference(context).getString(KEY_USERNAME_SEDANG_LOGIN, "");
    }
//    Deklarasi Edit Preferences dan mengubah data
//    yang memiliki key KEY_STATUS_SEDANG_LOGIN dengan parameter status

    public static void setLoggedInStatus(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_STATUS_SEDANG_LOGIN, status);
        editor.apply();
    }

//    Mengembalikan nilai dari key key-status-sedang-login berupa boolean
    public static boolean getLoggedInStatus(Context context){
        return getSharedPreference(context).getBoolean(KEY_STATUS_SEDANG_LOGIN, false);
    }

//    Deklarasi edit prefrences dan menghapus data, sehingga menjadikannya bernilai degfault
//    khusus data yang memiliki key KEY_USERNAME_SEDANG_LOGIN dan key-status-sedang-login
    public static void clearLoggedInUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_USERNAME_SEDANG_LOGIN);
        editor.remove(KEY_STATUS_SEDANG_LOGIN);
        editor.apply();
    }
}
