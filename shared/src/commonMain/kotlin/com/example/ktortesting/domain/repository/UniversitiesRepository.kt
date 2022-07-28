package com.example.ktortesting.domain.repository

import com.example.ktortesting.domain.models.UniversityItemDomain

interface UniversitiesRepository {
    suspend fun getUniversities(): List<UniversityItemDomain>
}