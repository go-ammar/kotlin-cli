package utils

import com.google.gson.Gson
import model.ErrorResponse
import retrofit2.Response
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object Utils {

    fun parseError(response: Response<*>): ErrorResponse {
        val errorBody = response.errorBody()?.string()
        return if (errorBody != null) {
            try {
                Gson().fromJson(errorBody, ErrorResponse::class.java)
            } catch (e: Exception) {
                ErrorResponse("error", "unknown", "Unknown error occurred")
            }
        } else {
            ErrorResponse("error", "unknown", "Unknown error occurred")
        }
    }

    fun String.removeHtmlAttributes() =
        this.replace(Regex("<[^>]*>"), "")

    fun String.removeEndingCharsOfContent() =
        this.replace(Regex("\\[\\+\\d+ chars]"), "")


    fun convertDateTime(originalDateTime: String): String {
        return try {
            // Define the input and output formatters
            val inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
            val outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

            // Parse the original date-time string
            val parsedDate = OffsetDateTime.parse(originalDateTime, inputFormatter)

            // Format the parsed date to the desired format
            parsedDate.format(outputFormatter)
        } catch (e: DateTimeParseException) {
            // Handle parsing exception
            "Invalid date-time format: $originalDateTime"
        }
    }
}