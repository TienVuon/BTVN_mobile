package com.example.btvn_tentuoi

import android.graphics.Color
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class Gmail : AppCompatActivity() {

    private lateinit var tilEmail: TextInputLayout
    private lateinit var edtEmail: TextInputEditText
    private lateinit var btnCheck: MaterialButton
    private lateinit var tvMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gmail)

        tilEmail = findViewById(R.id.tilEmail)
        edtEmail = findViewById(R.id.edtEmail)
        btnCheck = findViewById(R.id.btnCheck)
        tvMessage = findViewById(R.id.tvMessage)

        btnCheck.setOnClickListener {
            validateEmail()
        }

        // Xóa thông báo khi người dùng bắt đầu nhập lại
        edtEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) tvMessage.text = ""
        }
    }

    private fun validateEmail() {
        val email = edtEmail.text?.toString()?.trim().orEmpty()

        when {
            email.isEmpty() ->
                showError("Vui lòng nhập email")
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                showError("Email không đúng định dạng")
            else ->
                showSuccess("Email hợp lệ!")
        }

        // Ẩn bàn phím
        currentFocus?.let {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun showError(msg: String) {
        tvMessage.text = msg
        tvMessage.setTextColor(Color.parseColor("#D32F2F"))
    }

    private fun showSuccess(msg: String) {
        tvMessage.text = msg
        tvMessage.setTextColor(Color.parseColor("#1E88E5"))
    }
}
