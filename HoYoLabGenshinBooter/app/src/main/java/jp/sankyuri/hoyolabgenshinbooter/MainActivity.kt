package jp.sankyuri.hoyolabgenshinbooter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.coroutines.yield
import java.lang.Exception
import java.sql.Time
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity()
{

    override fun onCreate( savedInstanceState: Bundle? )
    {
        super.onCreate( savedInstanceState )

        // 環境設定のセットアップ。
        AppPref.setSharedPreferences( getSharedPreferences( "pref", 0 ) )

        checkAppPref()


        // 原神を起動。
        // ※ HoYoLAB を先に操作したいので、原神を先に起動しバックグラウンド送りにしている。
        bootApp(
            StringParams.GENSHIN_PACKAGE ,
            StringParams.GENSHIN_ACTIVITY,
            R.string.genshin_error )


        // 日付が変わっていたら HoYoLab を起動。
        if ( wasDateReset() )
        {
            bootApp(
                StringParams.HOYOLAB_PACKAGE ,
                StringParams.HOYOLAB_ACTIVITY,
                R.string.hoyolab_error )
        }


        finish()

    }








    // アプリ起動
    private fun bootApp(
            packageName : String,
            activityName: String,
            errorTextId : Int
    )
    {
        try {
            val intent = intent.setClassName( packageName, activityName )
            startActivity( intent )

        }
        catch ( e: Exception ) {
            Toast.makeText ( applicationContext, errorTextId, Toast.LENGTH_SHORT )
                .show()
        }
    }




    // 環境設定に日時の記録があるか
    private fun checkAppPref()
    {
        // 無かったら作る
        if ( ! AppPref.contains( StringParams.KEY_LAST_USAGE ))
        {
            AppPref.setString( StringParams.KEY_LAST_USAGE, TimeTool.DEFAULT_DATE_TIME )
        }
    }




    // 原神がリセットされているか
    private fun wasDateReset(): Boolean
    {
        // 現在日時
        val dateCrt = TimeTool.createNow()

        // 前の日時(環境設定に記録されている日時)
        val dateLst = TimeTool().apply {
            datetime = AppPref.getString( StringParams.KEY_LAST_USAGE )!!
        }


        // リセットされているか
        if ( wasDateResetCompDate( dateCrt, dateLst) )
        {
            // 現在の日時を記録
            AppPref.setString( StringParams.KEY_LAST_USAGE, dateCrt.datetime )

            return true
        }
        // 4.それ以外(日付が変わっていない)
        return false
    }


    // 日時比較
    private fun wasDateResetCompDate(
            dateCrt: TimeTool,
            dateLst: TimeTool
    ): Boolean
    {
        val crtDate = dateCrt.getDateInt()
        val crtTime = dateCrt.getTimeInt()
        val lstDate = dateLst.getDateInt()
        val lstTime = dateLst.getTimeInt()


        // 日時を比較
        return (
            // 日付は2日以上空いている。
            wasDateReset2Days  ( crtDate,          lstDate          ) ||
            // 日付は次の日以降で、現在時間は04:00かそれ以降。
            wasDateResetNewDay ( crtDate, crtTime, lstDate          ) ||
            // 日付は同じで、前の時間は04:00よりも前で、現在時間は04:00かそれ以降。
            wasDateResetSameDay( crtDate, crtTime, lstDate, lstTime )
        )
    }

    // 日付は次の日以降で、現在時間は04:00かそれ以降。
    private fun wasDateResetNewDay(
            crtDate: Int,
            crtTime: Int,
            lstDate: Int )  =
        lstDate < crtDate              &&
        TimeTool.RESET_TIME <= crtTime

    // 日付は同じで、前の時間は04:00よりも前で、現在時間は04:00かそれ以降。
    private fun wasDateResetSameDay(
            crtDate: Int,
            crtTime: Int,
            lstDate: Int,
            lstTime: Int ) =
        lstDate == crtDate             &&
        lstTime < TimeTool.RESET_TIME  &&
        TimeTool.RESET_TIME <= crtTime

    // 日付は2日以上空いている。
    private fun wasDateReset2Days(
            crtDate: Int,
            lstDate: Int ) =
        crtDate - lstDate >= 2




}



