package com.paquitosoft.demotvapplication.repository

import android.app.Application
import com.paquitosoft.demotvapplication.shared.datamodel.Video
import com.paquitosoft.demotvapplication.shared.datamodel.VideoType

interface VideoRepository {

    val application: Application

    fun getAllVideos(): List<Video>

    fun getVideoById(videoId: String): Video?

    fun getVideByVideoUri(videoUri: String): Video?

    fun getAllVideosFromSeries(seriesUri: String): List<Video>

    fun getNextEpisodeInSeries(episode: Video): Video? {
        if (episode.videoType != VideoType.EPISODE) {
            return null
        }

        val tvSeasonsMap = getAllVideosFromSeries(episode.seriesUri).groupBy { it.seasonNumber }
        var nextEpisode: Video? = null
        val nextEpisodeNumber = (episode.episodeNumber.toInt() + 1).toString()

        // Searching for next episode in the same season.
        tvSeasonsMap[episode.seasonNumber]?.let { currentSeason ->
            nextEpisode = currentSeason.firstOrNull { videoInCurrenSeason ->
                videoInCurrenSeason.episodeNumber == nextEpisodeNumber
            }
        }

        // If not found in previous step, checking if there's an episode available in next season.
        if (nextEpisode == null) {
            val nextSeasonNumber = (episode.seasonNumber.toInt() + 1).toString()
            tvSeasonsMap[nextSeasonNumber]?.let { nextSeason ->
                nextEpisode = if (nextSeason.sortedBy { it.episodeNumber }.isNotEmpty()) {
                    nextSeason[0]
                } else {
                    null
                }
            }
        }

        return nextEpisode
    }
}