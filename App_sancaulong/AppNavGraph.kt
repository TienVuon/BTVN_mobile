@Composable
fun AppNavGraph(navController: NavController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController)  // Màn hình đăng nhập
        }
        composable("register") {
            RegisterScreen(navController)  // Màn hình đăng ký
        }
    }
}