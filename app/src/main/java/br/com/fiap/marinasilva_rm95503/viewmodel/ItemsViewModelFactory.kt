package br.com.fiap.marinasilva_rm95503.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ItemsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemsViewModel::class.java)) {
            @Suppress("NAO_VERIFICADO")
            return ItemsViewModel(application) as T
        }
        throw IllegalArgumentException("Classe ViewModel não conhecida")
    }
}
