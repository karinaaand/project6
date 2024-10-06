package com.example.project6

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project6.databinding.ActivityPage3Binding

class Page3 : AppCompatActivity() {
    // Inisialisasi binding untuk menghubungkan layout activity_page3.xml
    private lateinit var binding: ActivityPage3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menghubungkan layout activity_page3.xml dengan Activity ini menggunakan binding
        binding = ActivityPage3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data yang dikirimkan dari Page2 menggunakan Intent
        val title = intent.getStringExtra("TITLE")
        // Menampilkan judul yang diambil ke TextView dengan ID txtInfo2
        binding.txtInfo2.text = title

        // Mengambil dan menampilkan tanggal yang dipilih
        val date = intent.getStringExtra("SELECTED_DATE")
        binding.date.text = date

        // Mengambil dan menampilkan waktu yang dipilih
        val time = intent.getStringExtra("SELECTED_TIME")
        binding.time.text = time

        // Mengambil dan menampilkan opsi pengulangan (repeat) yang dipilih
        val repeat = intent.getStringExtra("SELECTED_REPEAT")
        binding.repeat.text = repeat

        // Menangani klik pada tombol Add Task untuk kembali ke halaman Page2
        binding.btnAddTask.setOnClickListener {
            val intent = Intent(this, Page2::class.java)
            startActivity(intent)
            finish() // Menutup Page3 setelah berpindah ke Page2
        }
    }
}
