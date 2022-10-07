package com.example.vision

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vision.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    //menggunakan objek pendamping untuk memfaktorkan ulang kode untuk tambahan "letter"
    companion object {
        const val LETTER = "letter"
        //objek pendamping untuk menambahkan konstanta baru, SEARCH_PREFIX
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //=========================================================================
        //Mengubah letter hard code val letterId = "A" menjadi val letterId = intent?.extras?.getString(LETTER).toString()
        //toString() dipanggil untuk memastikan hurufnya adalah String, bukan null.
        val letterId = intent?.extras?.getString(LETTER).toString()


        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(letterId, this)

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        title = getString(R.string.detail_prefix) + " " + letterId

    }
}