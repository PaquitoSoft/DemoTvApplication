package com.paquitosoft.demotvapplication.repository

import com.paquitosoft.demotvapplication.shared.datamodel.ApiResponse
import com.paquitosoft.demotvapplication.shared.datamodel.Video
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object VideoParser {

    fun loadVideosFromJson(jsonString: String): List<Video> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(ApiResponse::class.java, Video::class.java)
        val adapter = moshi.adapter<ApiResponse<Video>>(type)
        return adapter.fromJson(jsonString)!!.content
    }

    fun findVideoFromJson(jsonString: String, videoId: String): Video? {
        val videosList = loadVideosFromJson(jsonString)
        for (video in videosList) {
            if (video.id == videoId) {
                return video
            }
        }
        return null
        // TODO: Can it be done like this?
//        return videosList.firstOrNull { video ->
//            video.id == videoId
//        }
    }

}