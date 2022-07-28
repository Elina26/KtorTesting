package com.example.ktortesting.domain.repository

import com.example.ktortesting.domain.models.UniversityItemDomain
import com.example.ktortesting.network.ktorClient
import com.example.ktortesting.network.models.UniversityItem
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class UniversitiesRepositoryImpl : UniversitiesRepository {
    override suspend fun getUniversities(): List<UniversityItemDomain> {
        val response: List<UniversityItem> =
            ktorClient.get("http://universities.hipolabs.com/search?name=middle") {
                contentType(ContentType.Application.Json)
            }.body()

        return response.map {
            UniversityItemDomain(
                it.name,
                it.country
            )
        }
    }
}