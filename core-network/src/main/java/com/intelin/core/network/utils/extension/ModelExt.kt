package com.intelin.core.network.utils.extension

fun <T : Any> T.hasField(fieldName: String): Boolean {
    val properties = this.javaClass.declaredFields
    return properties.any { it.name == fieldName }
}