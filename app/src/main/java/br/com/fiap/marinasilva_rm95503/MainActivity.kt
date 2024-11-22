package br.com.fiap.marinasilva_rm95503

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.marinasilva_rm95503.ui.theme.MarinaSilva_RM95503Theme
import br.com.fiap.marinasilva_rm95503.viewmodel.ItemsAdapter
import br.com.fiap.marinasilva_rm95503.viewmodel.ItemsViewModel
import br.com.fiap.marinasilva_rm95503.viewmodel.ItemsViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = ItemsViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Eco Dicas APP"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val itemsAdapter = ItemsAdapter { item ->
        }
        recyclerView.adapter = itemsAdapter

        val getText1 = findViewById<EditText>(R.id.texttip)
        val getText2 = findViewById<EditText>(R.id.textdescription)
        val btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener {
            if (getText1.text.isEmpty()) {
                getText1.error = "PREENCHA O TÍTULO DA DICA"
                return@setOnClickListener
            }

            if (getText2.text.isEmpty()) {
                getText2.error = "PREENCHA A DESCRIÇÃO"
                return@setOnClickListener
            }

            viewModel.addItem(getText1.text.toString(), getText2.text.toString())

            getText1.text.clear()
            getText2.text.clear()
        }

        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }

        val searchView = findViewById<androidx.appcompat.widget.SearchView>(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredItems = viewModel.itemsLiveData.value?.filter {
                    it.tip.contains(newText.orEmpty(), ignoreCase = true)
                } ?: listOf()

                itemsAdapter.updateItems(filteredItems)
                return true
            }
        })
    }
}
