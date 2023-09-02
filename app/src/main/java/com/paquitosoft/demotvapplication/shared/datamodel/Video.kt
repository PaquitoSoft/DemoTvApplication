package com.paquitosoft.demotvapplication.shared.datamodel

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import timber.log.Timber
import java.time.Duration

enum class VideoType {
    @Json(name = "clip")
    CLIP,

    @Json(name = "episode")
    EPISODE,

    @Json(name = "moviw")
    MOVIE
}

@Parcelize
@JsonClass(generateAdapter = true)
class Video(
    val id: String,
    val name: String,
    val description: String,
    val uri: String,
    val videoUri: String,
    val thumbnailUri: String,
    @Json(name = "backgroundUri") val backgroundImageUri: String,
    val category: String,
    val videoType: VideoType,
    // The duration is specified in the ISO 8601 format as 'PT00H00M'. For more information on the
    // format, refer - https://en.wikipedia.org/wiki/ISO_8601.
    val duration: String = "PT00H00M",
    // The series, season and episode information is picked from the JSON feed that stores the
    // catalog. For consistency and proper formatting of the JSON, the fields for series, season and
    // episode data have been defined as empty strings for content types that are not TV Episodes.
    val seriesUri: String = "",
    val seasonUri: String = "",
    val episodeNumber: String = "",
    val seasonNumber: String = ""
) : Parcelable {

    companion object {
        /**
         * Threshold constant used to calculate if a Video's credits have started. Using 95% to
         * simulate the start position for when credits would appear in a video.
         */
        private const val VIDEO_COMPLETED_DURATION_MAX_PERCENTAGE = 0.95
    }

    override fun toString(): String {
        return "Video(name=$name)"
    }

    fun duration(): Duration {
        return Duration.parse(duration)
    }

    /**
     * The user has "finished" a video if the end credits start OR an approximation based on the
     * content length. We do not have metadata that contains the timestamp for when credits appear
     * in a video so we are using an approximation.
     */
    fun isAfterEndCreditsPosition(positionMillis: Long): Boolean {
        if (positionMillis <= 0) {
           return false
        }

        val durationMillis = duration().toMillis() * VIDEO_COMPLETED_DURATION_MAX_PERCENTAGE
        val result = positionMillis >= durationMillis

        Timber.v(
            "Has video Ended? %s, duration: %s, durationMillis: %s, positionMillis: %s",
            result,
            duration,
            durationMillis,
            positionMillis
        )

        return result
    }
}