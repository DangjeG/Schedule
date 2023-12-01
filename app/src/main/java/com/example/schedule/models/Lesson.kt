
package com.example.schedule.models

import android.os.Parcel
import android.os.Parcelable
import java.sql.Time

data class Lesson(val title: String, val start: Time, val end: Time, val lectureHall: String?, val teacher: String):
    Parcelable {


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeSerializable(start)
        parcel.writeSerializable(end)
        parcel.writeString(lectureHall)
        parcel.writeString(teacher)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Lesson> {
        override fun createFromParcel(parcel: Parcel): Lesson {
            return Lesson(
                parcel.readString() ?: "",
                parcel.readSerializable() as Time,
                parcel.readSerializable() as Time,
                parcel.readString() ?: "",
                parcel.readString() ?: ""
            )
        }

        override fun newArray(size: Int): Array<Lesson?> {
            return arrayOfNulls(size)
        }
    }
}

