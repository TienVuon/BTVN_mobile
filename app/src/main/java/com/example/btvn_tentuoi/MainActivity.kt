package com.example.btvn_tentuoi

import android.graphics.Color
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtAge: EditText
    private lateinit var btnCheck: Button
    private lateinit var tvMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById(R.id.edtName)
        edtAge = findViewById(R.id.edtAge)
        btnCheck = findViewById(R.id.btnCheck)
        tvMessage = findViewById(R.id.tvMessage)

        btnCheck.setOnClickListener { validateAndShow() }
    }

    private fun validateAndShow() {
        val name = edtName.text?.toString()?.trim().orEmpty()
        val ageStr = edtAge.text?.toString()?.trim().orEmpty()

        when {
            name.isEmpty() -> showError("Vui lòng nhập tên")
            ageStr.isEmpty() -> showError("Vui lòng nhập tuổi")
            ageStr.toIntOrNull() == null -> showError("Tuổi phải là số")
            else -> {
                val age = ageStr.toInt()
                val category = when {
                    age > 65 -> "Người già"
                    age in 6..65 -> "Người lớn"
                    age in 2..5 -> "Trẻ em"
                    else -> "Em bé"
                }
                showSuccess("Họ tên: $name\nTuổi: $age\nPhân loại: $category")
            }
        }

        currentFocus?.let { v ->
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    private fun showError(msg: String) {
        tvMessage.text = msg
        tvMessage.setTextColor(Color.parseColor("#D32F2F"))
        tvMessage.visibility = TextView.VISIBLE
    }

    private fun showSuccess(msg: String) {
        tvMessage.text = msg
        tvMessage.setTextColor(Color.parseColor("#1E88E5"))
        tvMessage.visibility = TextView.VISIBLE
    }
}
