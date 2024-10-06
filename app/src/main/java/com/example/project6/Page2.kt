package com.example.project6

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.project6.databinding.ActivityPage2Binding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Page2 : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    // Inisialisasi binding untuk mengakses elemen layout activity_page2.xml
    private lateinit var binding: ActivityPage2Binding
    // Membuat kalender untuk mengatur tanggal dan waktu
    private val calendar = Calendar.getInstance()
    private var selectedDate: String = ""
    private var selectedTime: String = ""
    private var selectedHour: Int = 0
    private var selectedMinute: Int = 0
    private var selectedRepeat: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menghubungkan layout activity_page2.xml dengan Activity ini menggunakan binding
        binding = ActivityPage2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Pengaturan Spinner untuk memilih opsi pengulangan (Repeat)
        val stringRepeat = resources.getStringArray(R.array.repeat)
        val adapterSpinnerRepeat = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, stringRepeat)
        binding.spinnerRepeat.adapter = adapterSpinnerRepeat
        binding.spinnerRepeat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedRepeat = stringRepeat[position] // Mengambil nilai repeat yang dipilih
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Mengatur nilai default Spinner ke "Once"
        val selection = "Once"
        val spinnerPosition: Int = adapterSpinnerRepeat.getPosition(selection)
        binding.spinnerRepeat.setSelection(spinnerPosition)
    }

    // Menampilkan DatePicker untuk memilih tanggal
    fun showCalendar(view: View) {
        val datePickerDialog = DatePickerDialog(
            this, this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show() // Menampilkan dialog pemilihan tanggal
    }

    // Mengatur tanggal yang dipilih ke TextView
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        // Mengatur format tanggal yang dipilih
        val dateFormat = SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault())
        selectedDate = dateFormat.format(calendar.time) // Mengubah tanggal ke format yang ditentukan
        binding.btnDatePicker.text = selectedDate // Menampilkan tanggal yang dipilih pada tombol
    }

    // Menampilkan TimePicker untuk memilih waktu
    fun showTimepicker(view: View) {
        val timePickerDialog = TimePickerDialog(
            this, this,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show() // Menampilkan dialog pemilihan waktu
    }

    // Mengatur waktu yang dipilih ke TextView
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        // Mengatur jam dan menit yang dipilih
        selectedHour = hourOfDay
        selectedMinute = minute

        // Format waktu tanpa detik
        selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
        binding.fieldTimePicker.text = selectedTime // Menampilkan waktu yang dipilih
    }

    // Menampilkan dialog konfirmasi sebelum menambahkan task
    fun showAlert(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("SimpliRemind") // Judul dialog
        builder.setMessage("Do you want to add this as a new task?") // Pesan dialog
        builder.setPositiveButton("Yes") { dialog, _ ->
            // Jika pengguna memilih "Yes", pindah ke Page3
            val intent = Intent(this, Page3::class.java)
            val title = binding.fieldTitle.text.toString() // Mengambil judul dari inputan pengguna
            intent.putExtra("SELECTED_DATE", selectedDate) // Mengirim data tanggal ke Page3
            intent.putExtra("SELECTED_TIME", selectedTime) // Mengirim data waktu ke Page3
            intent.putExtra("SELECTED_REPEAT", selectedRepeat) // Mengirim data repeat ke Page3
            intent.putExtra("TITLE", title) // Mengirim judul ke Page3
            startActivity(intent) // Memulai aktivitas Page3
        }
        builder.setNeutralButton("No") { dialog, _ -> dialog.dismiss() } // Menutup dialog jika pengguna memilih "No"

        val dialog = builder.create()
        dialog.show() // Menampilkan dialog
    }
}
