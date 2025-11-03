class ProfileActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        val onCreate = super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val user = auth.currentUser
        val userId = user?.uid

        // Lấy thông tin người dùng từ Firestore
        if (userId != null) {
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val name = document.getString("name")
                        val email = document.getString("email")
                        val photoUrl = document.getString("photoUrl")

                        // Hiển thị thông tin người dùng
                        findViewById<EditText>(R.id.editName).setText(name)
                        findViewById<EditText>(R.id.editEmail).setText(email)

                        // Tải ảnh đại diện từ URL
                        if (photoUrl != null) {
                            Glide.with(this)
                                .load(photoUrl)
                                .into(findViewById<ImageView>(R.id.imgProfile))
                        }
                    }
                }
                .addOnFailureListener { e ->
                    Log.w("Firestore", "Error getting user info", e)
                }
        }

        // Lưu thông tin chỉnh sửa vào Firestore
        findViewById<Button>(R.id.btnSaveProfile).setOnClickListener {
            val updatedName = findViewById<EditText>(R.id.editName).text.toString()
            val updatedEmail = findViewById<EditText>(R.id.editEmail).text.toString()

            if (userId != null) {
                val updatedUser = hashMapOf(
                    "name" to updatedName,
                    "email" to updatedEmail
                )

                db.collection("users").document(userId).update(updatedUser)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show()
                        Log.w("Firestore", "Error updating user info", e)
                    }
            }
        }

        // Đăng xuất
        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
