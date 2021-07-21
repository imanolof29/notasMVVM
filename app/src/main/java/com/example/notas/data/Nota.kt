package com.example.notas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nota")
data class Nota(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    val titulo:String,
    val descripcion:String
)