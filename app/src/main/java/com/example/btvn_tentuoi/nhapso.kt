package com.example.btvn_tentuoi

import android.graphics.Color
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class NhapSo : AppCompatActivity() {

    private lateinit var edtNumber: EditText
    private lateinit var btnCreate: Button
    private lateinit var tvError: TextView
    private lateinit var layoutList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nhapso)

        edtNumber = findViewById(R.id.edtNumber)
        btnCreate = findViewById(R.id.btnCreate)
        tvError = findViewById(R.id.tvError)
        layoutList = findViewById(R.id.layoutList)

        btnCreate.setOnClickListener {
            val input = edtNumber.text.toString().trim()
            layoutList.removeAllViews()
            tvError.visibility = TextView.GONE

            try {
                val count = input.toInt()
                if (count <= 0) {
                    showError("Số lượng phải > 0")
                    return@setOnClickListener
                }

                // Ẩn bàn phím sau khi bấm nút
                currentFocus?.let {
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(it.windowToken, 0)
                }

                // Tạo các ô đỏ
                for (i in 1..count) {
                    val tv = TextView(this)
                    tv.text = "Ô $i"
                    tv.setPadding(30, 30, 30, 30)
                    tv.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                    tv.setTextColor(Color.WHITE)
                    tv.setBackgroundColor(Color.parseColor("#D32F2F")) // đỏ đậm

                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 15, 0, 0)
                    tv.layoutParams = params

                    layoutList.addView(tv)
                }

            } catch (e: NumberFormatException) {
                showError("Dữ liệu không hợp lệ, vui lòng nhập số")
            }
        }
    }

    private fun showError(message: String) {
        tvError.text = message
        tvError.visibility = TextView.VISIBLE
        layoutList.removeAllViews()
    }
}
