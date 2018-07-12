package `fun`.vladov.actorapi.utils

import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter.ofPattern
import java.time.temporal.TemporalAdjusters.lastDayOfMonth
import java.util.*


/**
 * @author vladov
 * 06/07/2018
 */
class DateUtils {

    companion object {

        /**
         * get last day of month as localdate
         */
        @JvmStatic
        fun resolveLastDayOfMonth(month: Int):String {
            return formatDate(false, Month.of(month))
        }

        /**
         * get first day of month as localdate
         */
        @JvmStatic
        fun resolveFirstDayOfMonth(month: Int):String {
            return formatDate(true, Month.of(month))
        }

        /**
         * format date for template
         */
        @JvmStatic
        fun formatDate(date: LocalDate):String {
            val formatter = ofPattern("«dd» MMMM yyyy", Locale("ru"))
            return date.format(formatter)
        }

        @JvmStatic
        private fun formatDate(isFirstDay: Boolean, month: Month):String {
            val initial: LocalDate = if (isFirstDay) {
                LocalDate.of(2018, month, 1)
            } else {
                LocalDate.of(2018, month, 1).with(lastDayOfMonth())
            }
            val formatter = ofPattern("«dd» MMMM yyyy", Locale("ru"))
            return initial.format(formatter)
        }
    }

}