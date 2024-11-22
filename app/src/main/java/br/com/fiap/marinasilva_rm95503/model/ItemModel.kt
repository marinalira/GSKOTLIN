package br.com.fiap.marinasilva_rm95503.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ItemModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tip: String,
    val description: String
)