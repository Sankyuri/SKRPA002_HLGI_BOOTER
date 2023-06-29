package jp.sankyuri.hoyolabgenshinbooter

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor


//
//  アプリケーションの環境設定を読み書きするクラス。
//
object AppPref {
    private lateinit var pref: SharedPreferences
    private lateinit var edit: Editor


    //
    // Set current Preferences.
    // You need call first.
    //
    fun setSharedPreferences(
        a_pref: SharedPreferences )
    {
        pref = a_pref
    }




    fun contains(
        a_key  : String ): Boolean
    {
        return pref.contains( a_key )
    }


    fun remove(
        a_key  : String ): Boolean
    {
        edit = pref.edit()
        edit.remove( a_key )
        return edit.commit()
    }


    fun clear()
    {
        edit = pref.edit()
        edit.clear()
        edit.commit()
    }




    fun setString(
            a_key  : String,
            a_value: String? ): Boolean
    {
        edit = pref.edit()
        edit.putString( a_key, a_value )
        return edit.commit()
    }

    fun setStringSet(
        a_key  : String,
        a_value: Set<String>? ): Boolean
    {
        edit = pref.edit()
        edit.putStringSet( a_key, a_value )
        return edit.commit()
    }

    fun setInt(
        a_key  : String,
        a_value: Int ): Boolean
    {
        edit = pref.edit()
        edit.putInt( a_key, a_value )
        return edit.commit()
    }

    fun setLong(
        a_key  : String,
        a_value: Long ): Boolean
    {
        edit = pref.edit()
        edit.putLong( a_key, a_value )
        return edit.commit()
    }

    fun setFloat(
        a_key  : String,
        a_value: Float ): Boolean
    {
        edit = pref.edit()
        edit.putFloat( a_key, a_value )
        return edit.commit()
    }

    fun setBoolean(
        a_key  : String,
        a_value: Boolean ): Boolean
    {
        edit = pref.edit()
        edit.putBoolean( a_key, a_value )
        return edit.commit()
    }




    fun getAll(): MutableMap<String, *>?
    {
        return pref.all
    }


    fun getString(
            a_key    : String,
            a_default: String? = "" ): String?
    {
        return pref.getString( a_key, a_default )
    }

    fun getStringSet(
            a_key    : String,
            a_default: Set<String>? = null ): Set<String>?
    {
        return pref.getStringSet( a_key, a_default )
    }

    fun getInt(
        a_key    : String,
        a_default: Int = 0 ): Int
    {
        return pref.getInt( a_key, a_default )
    }

    fun getLong(
        a_key    : String,
        a_default: Long = 0L ): Long
    {
        return pref.getLong( a_key, a_default )
    }

    fun getFloat(
        a_key    : String,
        a_default: Float = 0.0F ): Float
    {
        return pref.getFloat( a_key, a_default )
    }

    fun getBoolean(
        a_key    : String,
        a_default: Boolean = false ): Boolean
    {
        return pref.getBoolean( a_key, a_default )
    }


}







