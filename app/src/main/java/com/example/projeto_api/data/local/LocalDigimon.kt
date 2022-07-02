package com.example.projeto_api.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projeto_api.data.domain.Digimon

@Entity
data class LocalDigimon(
    @PrimaryKey
    val img: String,
    val level: String,
    val name: String
)

fun List<LocalDigimon>.asDomainModel(): List<Digimon>{
    return map {
        Digimon(
            img = it.img,
            level = it.level,
            name = it.name
        )
    }
}