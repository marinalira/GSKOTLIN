package br.com.fiap.marinasilva_rm95503.data

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.fiap.marinasilva_rm95503.model.ItemModel

@Database(entities = [ItemModel::class], version = 1)

abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

}
