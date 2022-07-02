package com.example.projeto_api.data.source

import com.example.projeto_api.data.domain.Digimon
import com.example.projeto_api.data.local.LocalDigimon


data class SourceDigimonContainer(
    val sourceDigimons: List<SourceDigimon>
)

data class SourceDigimon (
    val img: String,
    val level: String,
    val name: String
)

fun SourceDigimonContainer.asDomainModel(): List<Digimon>{
    return sourceDigimons.map {
        Digimon(
            img = it.img,
            level = it.level,
            name = it.name
        )
    }
}

fun SourceDigimonContainer.asLocalModel(): List<LocalDigimon>{
    return sourceDigimons.map {
        LocalDigimon(
            img = it.img,
            level = it.level,
            name = it.name
        )
    }
}