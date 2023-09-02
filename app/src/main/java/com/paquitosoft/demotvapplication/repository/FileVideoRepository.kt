package com.paquitosoft.demotvapplication.repository

import android.app.Application
import com.paquitosoft.demotvapplication.shared.datamodel.Video

class FileVideoRepository(override val application: Application): VideoRepository {
    private val _allVideos: List<Video> by lazy {
        val jsonString = readJsonFromFile()
        VideoParser.loadVideosFromJson(jsonString)
    }

    private fun readJsonFromFile(): String {
        val inputStream = application.resources.openRawResource(R.raw.api)
        return inputStream.bufferedReader().use {
            it.readText()
        }
    }

    override fun getAllVideos(): List<Video> {
        TODO("Not yet implemented")
    }

    override fun getVideoById(videoId: String): Video? {
        TODO("Not yet implemented")
    }

    override fun getVideByVideoUri(videoUri: String): Video? {
        TODO("Not yet implemented")
    }

    override fun getAllVideosFromSeries(seriesUri: String): List<Video> {
        TODO("Not yet implemented")
    }
}