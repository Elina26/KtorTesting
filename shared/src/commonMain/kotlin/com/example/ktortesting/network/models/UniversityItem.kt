package com.example.ktortesting.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniversityItem(
    @SerialName("name")
    val name: String,
    @SerialName("country")
    val country: String
)