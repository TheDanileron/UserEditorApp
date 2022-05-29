package com.project.usereditorapp.framework.util

import com.google.gson.JsonParser
import java.lang.StringBuilder

object JsonConverter {
    fun convertUpdateError(error: String): String {
        if(error.startsWith("[")) {
            val jsonErrorArray = JsonParser.parseString(error).asJsonArray
            val sb = StringBuilder("")
            for(i in 0 until jsonErrorArray.size()) {
                val message = jsonErrorArray.get(i).asJsonObject.get(Const.KEY_MESSAGE).asString
                val field = jsonErrorArray.get(i).asJsonObject.get(Const.KEY_FIELD).asString
                sb.append("$field $message. ")
            }
            return sb.toString()
        } else {
            val jsonError = JsonParser.parseString(error).asJsonObject
            return jsonError.get(Const.KEY_MESSAGE).asString
        }
    }
 }