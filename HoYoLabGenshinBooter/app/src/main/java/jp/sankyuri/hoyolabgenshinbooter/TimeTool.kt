package jp.sankyuri.hoyolabgenshinbooter

import android.annotation.SuppressLint
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date

class TimeTool
{

    companion object {

        // リセットされる時間(04:00)
        const val RESET_TIME = 400

        // yyyyMMDDHHmm の初期値(エポック時間準拠)
        const val DEFAULT_DATE_TIME = "197001010000"




        // 現在時刻を yyyyMMDDHHmm の形式で取得する
        @SuppressLint("SimpleDateFormat")
        fun createNow(): TimeTool
        {
            val df = SimpleDateFormat( "yyyyMMddHHmm" )
            val date = Date( System.currentTimeMillis() )
            return TimeTool().apply {
                datetime = df.format( date )
            }
        }




        // yyyyMMDD
        private const val DATE_LEN   = 8
        private const val DATE_START = 0
        private const val DATE_END   = DATE_START + DATE_LEN

        // HHmm
        private const val TIME_LEN   = 4
        private const val TIME_START = DATE_END
        private const val TIME_END   = TIME_START + TIME_LEN

        private const val DATETIME_LEN = DATE_LEN + TIME_LEN


    }



    // yyyyMMDDHHmm で記録する
    var datetime = DEFAULT_DATE_TIME
    // ※yyyyMMDDHHmm が入ってきたと信じる




    fun getDate(): String = datetime.substring( DATE_START, DATE_END )
    fun getTime(): String = datetime.substring( TIME_START, TIME_END )


    fun getDateInt(): Int = getDate().toInt()
    fun getTimeInt(): Int = getTime().toInt()




}


