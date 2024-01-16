package com.intelin.core.library.extension

import android.os.Build
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.google.gson.Gson
import com.intelin.core.library.repository.data.FormatDate
import com.intelin.core.library.repository.data.TypeFormatString
import com.intelin.core.library.repository.interfaces.ISessionManager
import com.intelin.core.library.repository.model.ThrowableModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun Parcelable.toJson(): String? = Gson().toJson(this)

inline fun <reified T>String.parseModel(): T = Gson().fromJson(this, T::class.java)

fun Throwable.clone() = ThrowableModel(this.message, this.cause)

fun  (() -> Unit?)?.isNull(): Boolean = this == null

fun  (() -> Unit?)?.isNotNull(): Boolean = !this.isNull()

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Unit.HiddenKeyboard() {
    LocalSoftwareKeyboardController.current?.hide()
}

fun Boolean?.switch(value: Boolean): Boolean = if (this.isNotNull()) this!! else value

fun Boolean?.compareTrue() = this == true

fun Boolean?.compareFalse() = this == false

fun ISessionManager.getBearerToken() = "Bearer ${this.getAccessToken()}"

fun Any?.isNull() = this == null
fun Any?.isNotNull() = !this.isNull()

fun String?.compareLengthThanOrEqual(length: Int): Boolean = (this?.length ?: 0) >= length

fun String?.switch(replaceWith: String): String {
    return if (this.isNotNull()) {
        if (this!!.isNotEmpty()) {
            this
        }else {
            replaceWith
        }
    }else {
        replaceWith
    }
}
fun String.hasVietnameseAccent(): Boolean {
    val vietnameseAccentPattern = "[àáảãạâầấẩẫậăắằặẵẳđèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵ]"
    return this.any { it.toString().matches(Regex(vietnameseAccentPattern)) }
}

fun String.getFormat(): TypeFormatString {
    if (this.toLongOrNull().isNotNull()) {
        return TypeFormatString.NUMBER
    }

    if (this.count { it == '@' } == 1 && this.lastIndexOf('.') > this.lastIndexOf("@")) {
        return TypeFormatString.EMAIL
    }

    return TypeFormatString.NORMAL
}
fun String.censoring(start: Int = 1, end: Int = 1, char: Char = '*'): String {
    if (this.length < 3) {
        return this
    }
    var startLocal = start
    var endLocal = end
    if (this.length < start + end + 1) {
        startLocal = 1
        endLocal = 1
    }
    val outputString = StringBuilder()
    for (i in this.indices) {
        if (i <= startLocal - 1 || i >= this.length - endLocal) {
            outputString.append(this[i])
        } else {
            outputString.append(char)
        }
    }
    return outputString.toString()
}

fun Long.toDate(mode: FormatDate = FormatDate.DATE, isInternational: Boolean = true): String {
    val format = if (isInternational) mode.internationalFormat else mode.vnFormat
    val instant = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Instant.ofEpochMilli(this * 1000)
    } else {
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        return formatter.format(Date(this * 1000))
    }
    val zoneId = ZoneId.systemDefault()
    val localDateTime = LocalDateTime.ofInstant(instant, zoneId)
    val formatter = DateTimeFormatter.ofPattern(format)
    return localDateTime.format(formatter)
}