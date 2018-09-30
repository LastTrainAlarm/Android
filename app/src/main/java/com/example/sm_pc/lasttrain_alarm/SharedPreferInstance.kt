package com.example.sm_pc.lasttrain_alarm

import android.content.Context
import android.content.SharedPreferences

class SharedPreferInstance {
    //키값
    // Boolean -> LOGIN, NOTICE, LOCK, BLIND
    // String - > EMAIL, UID, GENDER, PASSWORD, TOKEN , AGE

    fun putPreferString(key: String, value: String) {
        editor!!.putString(key, value)
        editor!!.commit()
    }

    fun putPreferInt(key: String, value: Int) {
        editor!!.putInt(key, value)
        editor!!.commit()
    }

    fun putPreferBoolean(key: String, value: Boolean) {
        editor!!.putBoolean(key, value)
        editor!!.commit()
    }

    fun getPreferInt(key: String): Int? {
        return prefer!!.getInt(key, 0)
    }

    fun getPreferString(key: String): String? {
        return prefer!!.getString(key, null)
    }


    fun getPreferBoolean(key: String): Boolean? {
        return prefer!!.getBoolean(key, false)
    }

    companion object {

        val PREFERENCE_NAME = "lasttrain_sharedPreference"
        private var instance: SharedPreferInstance? = null
        private var mContext: Context? = null
        private var prefer: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null

        fun getInstance(context: Context): SharedPreferInstance {
            mContext = context

            if (instance == null) {
                instance = SharedPreferInstance()
            }
            if (prefer == null) {
                prefer = mContext!!.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                editor = prefer!!.edit()
            }
            return instance as SharedPreferInstance
        }
    }

}
