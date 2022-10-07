package com.example.vision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vision.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    //membuat properti untuk melacak status tata letak tempat aplikasi berada, Tetapkan nilai default ke true karena pengelola tata letak linear akan digunakan secara default
    private var isLinearLayoutManager = true

    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            //GridLayoutManager yang memungkinkan beberapa item di satu baris.
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView.adapter = LetterAdapter()
    }

    //memperbarui ikon untuk mencerminkan fungsi barunya dengan beralih kembali ke tata letak daftar. setel ikon tata letak linear dan petak, dan berdasarkan tata letak, tombol akan beralih kembali saat mengetuknya di lain waktu.
    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
    }
    //Ikon ditetapkan dengan syarat berdasarkan properti isLinearLayoutManager.

    //onCreateOptionsMenu: tempat meng-inflate menu opsi dan melakukan penyiapan tambahan
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
        return true
    }

    //onOptionsItemSelected: tempat memanggil chooseLayout() saat tombol dipilih.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //mengganti kode di onCreate() untuk memanggil metode baru karena pengelola tata letak dan adaptor sekarang ditetapkan dalam chooseLayout()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        chooseLayout()
    }
}