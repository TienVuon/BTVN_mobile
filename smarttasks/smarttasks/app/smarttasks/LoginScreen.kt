package com.example.smarttasks

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavHostController, onLoginSuccess: () -> Unit) {
    val context = LocalContext.current
    val auth = remember { FirebaseAuth.getInstance() }
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()
    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    // State for Snackbar to show login status
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Launcher to handle the result of Google Sign-In
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential).addOnCompleteListener { task2 ->
                if (task2.isSuccessful) {
                    // Show success message
                    scope.launch {
                        snackbarHostState.showSnackbar("Đăng nhập thành công!")
                    }
                    onLoginSuccess()
                    navController.navigate("profile") // Navigate to Profile screen on success
                } else {
                    // Show failure message
                    scope.launch {
                        snackbarHostState.showSnackbar("Đăng nhập thất bại!")
                    }
                }
            }
        } catch (e: Exception) {
            // Show error message if there was an exception
            scope.launch {
                snackbarHostState.showSnackbar("Đăng nhập lỗi: ${e.message}")
            }
        }
    }

    // Scaffold to wrap the UI and show Snackbar for feedback
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("UTH SmartTasks", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(20.dp))
            Button(onClick = {
                val signInIntent = googleSignInClient.signInIntent
                launcher.launch(signInIntent) // Launch Google Sign-In intent
            }) {
                Text("Sign in with Google")
            }
        }
    }
}
