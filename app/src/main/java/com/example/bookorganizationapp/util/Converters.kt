package com.example.bookorganizationapp.util

import androidx.room.TypeConverter
import java.util.Calendar

class Converters {

    @TypeConverter
    fun roomDBfromDateToString(calendarDate: Calendar):String?{

        val tool = CalendarTools()

        return tool.CalendarToString(dateCalendar = calendarDate)
    }

    @TypeConverter
    fun roomDBfromStringToDate(date:String):Calendar?{
        val tool = CalendarTools()
        return  tool.StringToCalendar(date)
    }

}