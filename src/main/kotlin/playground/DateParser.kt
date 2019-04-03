@file:Suppress("unused")

package playground

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class DateParser {
    private fun parseDatetime(input: String): String {
        // 2011-12-03T10:15:30Z
        try {
            return LocalDate.parse(input, DateTimeFormatter.ISO_INSTANT).toString()
        } catch (ex: Exception) {
            // Swallow
        }

        // 2011-12-03T10:15:30
        try {
            return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString()
        } catch (ex: Exception) {
            // Swallow
        }

        // 1553537337000
        try {
            return Instant.ofEpochMilli(input.toLong()).atZone(ZoneId.systemDefault()).toLocalDate().toString()
        } catch (ex: Exception) {
            // Swallow
        }

        return ""
    }
}
