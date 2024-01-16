package com.intelin.core.library.repository.data

sealed class FormatDate(val internationalFormat: String, val vnFormat: String) {
    object DATE: FormatDate("yyyy-MM-dd", "dd-MM-yyyy")
    object DATETIME: FormatDate("yyyy-MM-dd HH:mm:ss", "dd-MM-yyyy HH:mm:ss")
    object TIME: FormatDate("HH:mm:ss", "HH:mm:ss")
}
