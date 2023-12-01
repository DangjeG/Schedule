package com.example.schedule.models

import android.os.Parcel
import android.os.Parcelable
import com.example.schedule.enums.WeekNumber

data class Week(val weekNumber: WeekNumber, val days: ArrayList<Day>) : Parcelable{

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(weekNumber.ordinal)
        dest.writeTypedList(days)
    }

    companion object CREATOR : Parcelable.Creator<Week> {
        override fun createFromParcel(parcel: Parcel): Week {
            val weekNumber = WeekNumber.values()[parcel.readInt()]
            val days = ArrayList<Day>()
            parcel.readTypedList(days, Day.CREATOR)
            return Week(weekNumber, days)
        }

        override fun newArray(size: Int): Array<Week?> {
            return arrayOfNulls(size)
        }
    }
}
