@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Đảm bảo sử dụng NavHost để điều hướng giữa các màn hình
            App_sancaulongTheme {
                val navController = rememberNavController() // Điều khiển điều hướng
                AppNavGraph(navController) // Graph điều hướng các màn hình
            }
        }
    }
}