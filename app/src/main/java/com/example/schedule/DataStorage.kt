package com.example.schedule

import com.example.schedule.enums.DayOfWeek
import com.example.schedule.enums.WeekNumber
import com.example.schedule.models.Day
import com.example.schedule.models.Lesson
import com.example.schedule.models.Week
import java.sql.Time
import java.util.Calendar


class DataStorage {


    public fun getWeek(number: WeekNumber): Week {
        return when (number) {
            WeekNumber.First -> Week(WeekNumber.First, firstWeek)
            WeekNumber.Second -> Week(WeekNumber.Second, secondWeek)
        }
    }

    public fun getDay(number: WeekNumber): Day {

        var day: Day? = null
        day = when (number) {
            WeekNumber.First -> getCurrentDay(firstWeek)
            WeekNumber.Second -> getCurrentDay(secondWeek)
        }
        return day ?: Day(DayOfWeek.TUESDAY, arrayListOf())
    }

    fun getCurrentDay(days: List<Day>): Day? {
        val calendar = Calendar.getInstance()
        val currentDayOfWeek = when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> DayOfWeek.MONDAY
            Calendar.TUESDAY -> DayOfWeek.TUESDAY
            Calendar.WEDNESDAY -> DayOfWeek.WEDNESDAY
            Calendar.THURSDAY -> DayOfWeek.THURSDAY
            Calendar.FRIDAY -> DayOfWeek.FRIDAY
            Calendar.SATURDAY -> DayOfWeek.SATURDAY
            Calendar.SUNDAY -> DayOfWeek.SUNDAY
            else -> null
        }
        return days.find { it.weekDay == currentDayOfWeek }
    }


    private val firstWeek: ArrayList<Day> = arrayListOf(
        Day(DayOfWeek.MONDAY, arrayListOf()),
        Day(
            DayOfWeek.TUESDAY, arrayListOf(
                Lesson("Экономика", Time(15, 0, 0), Time(16, 30, 0), "Ткач Е.С.", "Ауд 221"),
                Lesson(
                    "Базы данных",
                    Time(18, 20, 0),
                    Time(19, 50, 0),
                    "Барабаншиков И.В.",
                    "Ауд 221"
                )
            )
        ),
        Day(
            DayOfWeek.WEDNESDAY, arrayListOf(
                Lesson("Экономика", Time(15, 0, 0), Time(16, 30, 0), "Ткач Е.С.", "Ауд 221"),
                Lesson(
                    "Базы данных",
                    Time(18, 20, 0),
                    Time(19, 50, 0),
                    "Барабаншиков И.В.",
                    "Ауд 221"
                )

            )
        ),
        Day(
            DayOfWeek.TUESDAY, arrayListOf(
                Lesson("Экономика", Time(15, 0, 0), Time(16, 30, 0), "Ткач Е.С.", "Ауд 221"),
                Lesson(
                    "Базы данных",
                    Time(18, 20, 0),
                    Time(19, 50, 0),
                    "Барабаншиков И.В.",
                    "Ауд 221"
                )
            )
        ),
        Day(
            DayOfWeek.FRIDAY, arrayListOf(
                Lesson("Экономика", Time(15, 0, 0), Time(16, 30, 0), "Ткач Е.С.", "Ауд 221"),
                Lesson(
                    "Базы данных",
                    Time(18, 20, 0),
                    Time(19, 50, 0),
                    "Барабаншиков И.В.",
                    "Ауд 221"
                )
            )
        ),
        Day(
            DayOfWeek.SATURDAY, arrayListOf(
                Lesson("Экономика", Time(15, 0, 0), Time(16, 30, 0), "Ткач Е.С.", "Ауд 221"),
            )
        ),
        Day(
            DayOfWeek.SUNDAY, arrayListOf(
                Lesson(
                    "Базы данных",
                    Time(18, 20, 0),
                    Time(19, 50, 0),
                    "Барабаншиков И.В.",
                    "Ауд 221"
                ),
                Lesson("Экономика", Time(15, 0, 0), Time(16, 30, 0), "Ткач Е.С.", "Ауд 221"),
                Lesson("Экономика", Time(15, 0, 0), Time(16, 30, 0), "Ткач Е.С.", "Ауд 221"),

                )
        ),
    )

    private val secondWeek: ArrayList<Day> = arrayListOf(
        Day(DayOfWeek.MONDAY, arrayListOf()),
        Day(
            DayOfWeek.TUESDAY, arrayListOf(
                Lesson(
                    "Базы данных",
                    Time(18, 20, 0),
                    Time(19, 50, 0),
                    "Барабаншиков И.В.",
                    "Ауд 221"
                ),
                Lesson("Экономика", Time(15, 0, 0), Time(16, 30, 0), "Ткач Е.С.", "Ауд 221")
            )
        ),
        Day(
            DayOfWeek.WEDNESDAY, arrayListOf(
                Lesson("Экономика", Time(15, 0, 0), Time(16, 30, 0), "Ткач Е.С.", "Ауд 221"),

                Lesson(
                    "Базы данных",
                    Time(18, 20, 0),
                    Time(19, 50, 0),
                    "Барабаншиков И.В.",
                    "Ауд 221"
                ),
                Lesson("Экономика", Time(15, 0, 0), Time(16, 30, 0), "Ткач Е.С.", "Ауд 221")

            )
        ),
        Day(
            DayOfWeek.TUESDAY, arrayListOf(
                Lesson(
                    "Базы данных",
                    Time(18, 20, 0),
                    Time(19, 50, 0),
                    "Барабаншиков И.В.",
                    "Ауд 221"
                ),
                Lesson("Экономика", Time(15, 0, 0), Time(16, 30, 0), "Ткач Е.С.", "Ауд 221")
            )
        ),
    )

}