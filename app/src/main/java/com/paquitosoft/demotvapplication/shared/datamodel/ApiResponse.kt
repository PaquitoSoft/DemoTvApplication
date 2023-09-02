package com.paquitosoft.demotvapplication.shared.datamodel

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ApiResponse<T>(val content: List<T>, val metadata: Map<String, String>?)
