package com.example.schedule.models

import android.icu.text.SimpleDateFormat
import android.os.Parcel
import android.os.Parcelable
import com.example.schedule.enums.DayOfWeek


data class Day(val weekDay : DayOfWeek, val lessons : ArrayList<Lesson>) : Parcelable {

    fun getDay():String{
        return when(weekDay) {
            DayOfWeek.MONDAY -> "Понедельник"
            DayOfWeek.TUESDAY -> "Вторник"
            DayOfWeek.WEDNESDAY -> "Среда"
            DayOfWeek.THURSDAY -> "Четверг"
            DayOfWeek.FRIDAY -> "Пятница"
            DayOfWeek.SATURDAY -> "Суббота"
            DayOfWeek.SUNDAY -> "Воскресенье"
            else -> "Это когда?"
        }
    }

    fun getInfo(): String {
        var formatter: SimpleDateFormat = SimpleDateFormat("HH:mm")
        if (lessons.size < 1) return "В этот день пар нет)"
        return "${lessons.size} ${getDeclension(lessons.size)} c ${formatter.format(lessons.first().start)} " +
                "до ${formatter.format(lessons.last().end)}"
    }

    private fun getDeclension(num: Int) : String{
        return if (num == 1) "пара"
        else if (num < 5) "пары"
        else "пар"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(weekDay.ordinal)
        dest.writeTypedList(lessons)
    }

    companion object CREATOR : Parcelable.Creator<Day> {
        override fun createFromParcel(parcel: Parcel): Day {
            val weekDay = DayOfWeek.values()[parcel.readInt()]
            val lessons = ArrayList<Lesson>()
            parcel.readTypedList(lessons, Lesson.CREATOR)
            return Day(weekDay, lessons)
        }

        override fun newArray(size: Int): Array<Day?> {
            return arrayOfNulls(size)
        }
    }
}
