package com.intelin.core.library.ultils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.CountDownTimer
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.intelin.core.library.extension.isNotNull
import java.util.Calendar

object AppUtils {

    var gson: Gson = Gson()
    fun countDownTimer(tickTime: Long = 1500, totalTime: Long = 1500, onTick: () -> Unit = {}, onFinish: () -> Unit) {
        object : CountDownTimer(tickTime,totalTime) {
            override fun onTick(p0: Long) {
                onTick.invoke()
            }

            override fun onFinish() {
                onFinish.invoke()
            }
        }.start()
    }

    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun openDatePickerDialog(context: Context, day: Int? = null, month: Int? = null, year: Int? = null, onChange: (day: Int, month: Int, year: Int) -> Unit) {
        val calender = Calendar.getInstance()
        val dayLocal = if (day.isNotNull()) day!! else calender.get(Calendar.DAY_OF_MONTH)
        val monthLocal = if (month.isNotNull()) month!! else calender.get(Calendar.MONTH)
        val yearLocal = if (year.isNotNull()) year!! else calender.get(Calendar.YEAR)
        DatePickerDialog(context, { _, p1, p2, p3 ->
                onChange(p3,p2 + 1,p1)
            }, yearLocal, monthLocal, dayLocal).show()
    }

    @Composable
    fun widthScreen(float: Float = 1f): Dp {
        val screenWidth = with(LocalDensity.current) {
            LocalConfiguration.current.screenWidthDp
        }
        return (screenWidth * float).dp
    }

    @Composable
    fun densityScreen(): Float {
        with(LocalDensity.current) {
            return this@with.density
        }
    }
    @Composable
    fun widthScreenFloat(float: Float = 1f): Float {
        val screenWidth = with(LocalDensity.current) {
            LocalConfiguration.current.screenWidthDp
        }
        return (screenWidth * float)
    }

    @Composable
    fun heightScreen(float: Float = 1f): Dp {
        val screenWidth = with(LocalDensity.current) {
            LocalConfiguration.current.screenHeightDp
        }
        return (screenWidth * float).dp
    }

    @Composable
    fun heightScreenFloat(float: Float = 1f): Float {
        val screenWidth = with(LocalDensity.current) {
            LocalConfiguration.current.screenHeightDp
        }
        return (screenWidth * float)
    }

    fun convertDateToTimeStamp(day: Int, month: Int, year: Int, hour: Int = 0, minute: Int = 0, second: Int = 0): Long {
        require(!(day > 31 || day < 1 || month > 12 || month < 0 || year < 0)) { "Invalid time day [1-31] month [1-12] year > 0" }
        val calender = Calendar.getInstance()
        calender.set(year, month - 1, day, hour, minute, second)
        return calender.timeInMillis / 1000
    }
}