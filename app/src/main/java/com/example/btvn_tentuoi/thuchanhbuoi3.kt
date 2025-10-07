package com.example.btvn_tentuoi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ThucHanhBuoi3 : AppCompatActivity() {

    private lateinit var tvMessage: TextView
    private lateinit var btnSayHi: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.thuchanhbuoi3)

        tvMessage = findViewById(R.id.tvMessage)
        btnSayHi = findViewById(R.id.btnSayHi)

        btnSayHi.setOnClickListener {
            tvMessage.text = "I'm\nNguyen Tien Vuon"
        }
    }
}
