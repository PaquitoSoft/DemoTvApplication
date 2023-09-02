package com.paquitosoft.demotvapplication.repository

import android.app.Application

class VideoRepositoryFactory private constructor() {

    companion object {
        fun getVideoRepository(application: Application): VideoRepository {
            return FileVideoRepository(application)
        }
    }
}