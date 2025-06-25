package com.washingtondcsquad.tudee.presentation.features.tasks_screen.utils

import com.washingtondcsquad.tudee.presentation.features.tasks_screen.DayUiModel
import kotlinx.datetime.toKotlinLocalDate
import java.time.Instant
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

fun buildMonthDaysList(currentMonth: YearMonth, selectedDateMillis: Long): List<DayUiModel> {
    val selectedDay = Instant.ofEpochMilli(selectedDateMillis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate().dayOfMonth

    return (1..currentMonth.lengthOfMonth()).map { day ->
        val date = currentMonth.atDay(day)
        val dayName = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
        DayUiModel(
            dayNumber = day,
            dayName = dayName,
            isSelected = day == selectedDay
        )
    }
}

fun todayInMillis(): Long {
    val todayLocalDate = LocalDate.now(ZoneId.of("UTC"))
    return todayLocalDate
        .atStartOfDay(ZoneId.of("UTC"))
        .toInstant()
        .toEpochMilli()
}

fun getYearAndMonthTitleFromYearMonth(yearMonth: YearMonth): String {
    val monthName = yearMonth.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    return "$monthName, ${yearMonth.year}"
}
fun LocalDateParser(date: String): kotlinx.datetime.LocalDate {
    val formatter = DateTimeFormatter.ofPattern("d-M-yyyy")
    val javaLocalDate = LocalDate.parse(date, formatter)
    return javaLocalDate.toKotlinLocalDate()
}

