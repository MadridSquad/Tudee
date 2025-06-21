package com.washingtondcsquad.tudee.data.localSource.utils

import java.security.MessageDigest

fun String.toMD5(): String {
    return MessageDigest.getInstance("MD5").digest(this.toByteArray())
        .joinToString("") { "%02x".format(it) }
}