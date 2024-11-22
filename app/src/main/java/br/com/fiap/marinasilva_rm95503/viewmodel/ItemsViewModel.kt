package br.com.fiap.marinasilva_rm95503.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import br.com.fiap.marinasilva_rm95503.data.ItemDao
import br.com.fiap.marinasilva_rm95503.data.ItemDatabase
import br.com.fiap.marinasilva_rm95503.model.ItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(application: Application) : AndroidViewModel(application) {
    private val itemDao: ItemDao
    val itemsLiveData: LiveData<List<ItemModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            ItemDatabase::class.java,
            "items_database"
        ).build()
        itemDao = database.itemDao()
        itemsLiveData = itemDao.getAll()
    }

    fun addItem(item: String, item2: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newItem = ItemModel(tip = item, description = item2)
            itemDao.insert(newItem)
            Log.d("Room", "Item inserido: Tip: ${newItem.tip}, Description: ${newItem.description}")

        }
    }
}