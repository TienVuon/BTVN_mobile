package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.AuthViewModel
import com.example.myapplication.ui.AuthState
import com.example.myapplication.ui.screens.LoginScreen
import com.example.myapplication.ui.screens.ProfileScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class GoogleSignInActivity : ComponentActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private var authViewModel: AuthViewModel? = null

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                println("DEBUG: Got idToken: ${account.idToken}") // Debug log
                // Gọi authViewModel để xử lý đăng nhập
                account.idToken?.let { idToken ->
                    authViewModel?.signInWithGoogle(idToken)
                }
            } catch (e: ApiException) {
                println("DEBUG: Google Sign-In failed: ${e.message}") // Debug log
            }
        } else {
            println("DEBUG: Google Sign-In cancelled or failed") // Debug log
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Khởi tạo Google Sign-In Options
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("859522607163-h1fn88h4spjsc2kfu8r4gp39ghogv5iu.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Set nội dung của Activity với Jetpack Compose
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val authViewModel: AuthViewModel = viewModel()
                    this@GoogleSignInActivity.authViewModel = authViewModel
                    val authState = authViewModel.authState.collectAsState().value


                    // Kiểm tra tài khoản Google đã đăng nhập
                    LaunchedEffect(Unit) {
                        val account = GoogleSignIn.getLastSignedInAccount(this@GoogleSignInActivity)
                        account?.let {
                            println("DEBUG: Found existing Google account: ${it.email}")
                            authViewModel.signInWithGoogle(it.idToken!!)
                        }
                    }

                    // Debug log: Khi trạng thái AuthState thay đổi
                    LaunchedEffect(authState) {
                        println("DEBUG: AuthState changed: $authState")
                    }

                    // Hiển thị giao diện người dùng tùy thuộc vào trạng thái xác thực
                    when (authState) {
                        is AuthState.Success -> {
                            ProfileScreen(
                                authViewModel = authViewModel,
                                onNavigateBack = {
                                    authViewModel.signOut()
                                }
                            )
                        }
                        else -> {
                            LoginScreen(
                                authViewModel = authViewModel,
                                onNavigateToProfile = {
                                    // Khi người dùng nhấn nút Đăng nhập, bắt đầu quy trình Google Sign-In
                                    val signInIntent = googleSignInClient.signInIntent
                                    googleSignInLauncher.launch(signInIntent)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
