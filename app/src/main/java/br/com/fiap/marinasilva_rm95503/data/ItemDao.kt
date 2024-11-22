package br.com.fiap.marinasilva_rm95503.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.marinasilva_rm95503.model.ItemModel

@Dao
interface ItemDao {

    @Query("SELECT * FROM ItemModel")
    abstract fun getAll(): LiveData<List<ItemModel>>

    @Insert
    abstract fun insert(item: ItemModel)


    @Delete
    abstract fun delete(item: ItemModel)

}