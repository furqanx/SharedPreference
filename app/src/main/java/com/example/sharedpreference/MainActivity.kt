package com.example.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

/**
 * Activity utama dari aplikasi SharedPreference.
 *
 * Activity ini memungkinkan pengguna untuk memasukkan data teks, menyimpannya menggunakan SharedPreferences,
 * dan menampilkan data yang disimpan.
 *
 * @property sharedPreferences Instance dari SharedPreferences yang digunakan untuk penyimpanan data.
 * @property editText View EditText untuk memasukkan data.
 * @property buttonSave View Button untuk menyimpan data.
 * @property textView TextView untuk menampilkan data yang disimpan.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editText: EditText
    private lateinit var buttonSave: Button
    private lateinit var textView: TextView

    /**
     * Dipanggil ketika activity sedang dibuat. Inilah tempat sebagian besar inisialisasi harus dilakukan.
     *
     * @param savedInstanceState Jika activity sedang diinisialisasi kembali setelah sebelumnya
     * ditutup, maka Bundle ini berisi data yang terakhir kali diberikan dalam [onSaveInstanceState].
     * Catatan: Jika tidak, nilainya null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        buttonSave = findViewById(R.id.buttonSave)
        textView = findViewById(R.id.textView)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        if (sharedPreferences.contains("myData")) {
            val savedData = sharedPreferences.getString("myData", "")
            textView.text = savedData
        }

        buttonSave.setOnClickListener {
            val data = editText.text.toString()

            val editor = sharedPreferences.edit()

            editor.putString("myData", data)
            editor.apply()

            textView.text = data
            editText.text.clear()
        }
    }
}