package com.paquitosoft.demotvapplication.deeplink

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DeepLinkViewModel(application: Application, deepLinkUri: String) : ViewModel() {

    private val videoRepository: VideoRepositoryFactory.getVideoRepository(application)
    val deepLinkResult: MutableLiveData<SingleUseEvent<Result<Video>>>()

}