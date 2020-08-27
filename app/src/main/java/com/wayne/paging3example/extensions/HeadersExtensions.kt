package com.wayne.paging3example.extensions

import android.net.Uri
import okhttp3.Headers

class HeadersExtensions {
    companion object {
        const val LINK = "link"
        const val COMMA = ","
        const val SEMICOLON = ";"
        const val NEXT = "next"
        const val SINCE = "since"
        const val ANGLE_BRACKETS_LEFT = "<"
        const val ANGLE_BRACKETS_RIGHT = ">"
    }
}

fun Headers.getUsersNextPageUrl(): String? {
    val headerLink =  this[HeadersExtensions.LINK]
    if (headerLink != null) {
        val links = headerLink.split(HeadersExtensions.COMMA)
        for (linkData in links) {
            val linkDetail = linkData.split(HeadersExtensions.SEMICOLON)
            if (linkDetail.size == 2 && linkDetail[1].contains(HeadersExtensions.NEXT)) {
                val url = linkDetail[0]
                    .replace(HeadersExtensions.ANGLE_BRACKETS_LEFT, "")
                    .replace(HeadersExtensions.ANGLE_BRACKETS_RIGHT, "")
                    .trim()
                return Uri.parse(url).getQueryParameter(HeadersExtensions.SINCE)
            }
        }
    }
    return null
}