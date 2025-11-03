@Composable
fun LoginScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Welcome to Login Screen!")
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

        // Nút đăng nhập
        Button(onClick = {
            // Điều hướng đến trang đăng ký
            navController.navigate("register")
        }) {
            Text("Go to Register")
        }
    }
}