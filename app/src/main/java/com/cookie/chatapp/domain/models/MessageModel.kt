package com.cookie.chatapp.domain.models

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

data class MessageModel (
    val content: String,
    val status: String,
    val isSystem: Boolean,
    val userId: String,
    val roomCode: String,
    val username: String,
    val createdAt: MessageTimestamp,
    val updatedAt: String
)

fun toDateFromTimestamp(timeStamp: String): Date {
    val simpleDateTimeFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    SimpleDateFormat.getDateTimeInstance()
    simpleDateTimeFormatter.timeZone = TimeZone.getTimeZone("UTC")
    return simpleDateTimeFormatter.parse(timeStamp)!!
}

data class MessageTimestamp(
    val month: Int,
    val year: Int,
    val date: Int,
    val hour: Int,
    val minute: Int
)

fun getOffsetFromNow(date: Date): String{
    var lastActivity = ""
    val currentActivity = Date()
    if (currentActivity.month > (date.month)){
        lastActivity = (currentActivity.month - date.month).toString() + "Months ago"
    }
    else if (currentActivity.date > (date.date)){
        val present = (currentActivity.date - date.date)
        if (present == 1){
            lastActivity = "$present Day ago"
        }else{
            lastActivity = "$present Days ago"
        }
    }
    else if (currentActivity.hours > (date.hours)){
        lastActivity = (currentActivity.hours - date.hours).toString() + "Hours ago"
    }
    else if (currentActivity.minutes > (date.minutes)){
        lastActivity = (currentActivity.minutes - date.minutes).toString() + "Minutes ago"
    }
    else{
        lastActivity = "Just Now"
    }
    return lastActivity
}

fun fromStringToMessageTimestamp(time: String): MessageTimestamp{
    val year = substring(time, 0, 3)
    val month = substring(time, 5, 6)
    val date= substring(time, 8, 9)
    val hour= substring(time, 11, 12)
    val minute = substring(time, 14, 15)
    return MessageTimestamp(
        month = month.toInt(),
        year = year.toInt(),
        date = date.toInt(),
        hour = hour.toInt(),
        minute = minute.toInt()
    )
}

fun substring(string: String, start: Int, end: Int): String{
    var sub = ""
    for (i in start..end){
        sub += string[i]
    }
    return sub
}