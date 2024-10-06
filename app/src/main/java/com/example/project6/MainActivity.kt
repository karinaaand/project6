package com.example.project6

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project6.databinding.ActivityMainBinding
import com.example.project6.databinding.ActivityPage2Binding

class MainActivity : AppCompatActivity() {
    // Inisialisasi variabel binding untuk
    // mengakses elemen layout di activity_main.xml
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menggunakan binding untuk
        // meng-inflate layout activity_main.xml
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menetapkan aksi ketika tombol "Add Task" diklik
        binding.btnAddTask.setOnClickListener {
            // Membuat intent untuk berpindah
            // ke Page2 (Activity selanjutnya)
            val intent = Intent(this, Page2::class.java)
            // Memulai activity Page2
            startActivity(intent)
            // Menyelesaikan (mengakhiri)
            // activity saat ini setelah berpindah
            finish()
        }
    }
}
