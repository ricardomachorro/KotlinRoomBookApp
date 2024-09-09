package com.example.bookorganizationapp.util

import java.util.Calendar

class CalendarTools {


    fun CalendarToString(dateCalendar:Calendar):String{

        val day = (dateCalendar.get(Calendar.DAY_OF_MONTH)).toString()
        val month = (dateCalendar.get(Calendar.MONTH)+1).toString()
        val year = (dateCalendar.get(Calendar.YEAR)).toString()

        return "${day}/${month}/${year}"
    }

    fun StringToCalendar(dateString:String):Calendar{
        val listString:List<String> =dateString.split("/")
        val calendarReturn:Calendar= Calendar.getInstance()

        val day:Int= listString.get(0).toInt()
        val month:Int = listString.get(1).toInt()
        val year:Int = listString.get(2).toInt()
        calendarReturn.set(year,month,day)

        return calendarReturn
    }

    fun LongToStringDate(longEntrada:Long):String{

        val c = Calendar.getInstance();
        c.setTimeInMillis(longEntrada);

        val day = (c.get(Calendar.DAY_OF_MONTH)+1).toString();
        val month = (c.get(Calendar.MONTH) +1).toString();
        val year = c.get(Calendar.YEAR).toString();

        return day + "/"+month+"/"+year;




    }
}