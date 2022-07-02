package com.example.projeto_api.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.projeto_api.data.domain.Digimon

@Dao
interface DigimonDao {

    @Query("SELECT * FROM localdigimon order by name")
    fun getAllDigimons(): LiveData<List<LocalDigimon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDigimons(digimons: List<LocalDigimon>)

}