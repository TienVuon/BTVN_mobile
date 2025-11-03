@Composable
fun RegisterScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Welcome to Register Screen!")
        Spacer(modifier = Modifier.height(24.dp))

        // Các trường nhập liệu như tên người dùng và mật khẩu
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Username") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Password") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Nút đăng ký
        Button(onClick = {
            // Điều hướng đến trang đăng nhập
            navController.navigate("login")
        }) {
            Text("Go to Login")
        }
    }
}