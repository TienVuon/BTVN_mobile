package com.example.app_badminton.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject

val Context.dataStore by preferencesDataStore(name = "user_prefs")

data class User(
    val username: String,
    val password: String,
    val fullName: String = "",
    val gender: String = "",
    val phone: String = "",
    val address: String = "",
    val email: String = "",
    val avatarUri: String = "" // ✅ thêm ảnh đại diện thật
)

class UserPreferences(private val context: Context) {

    companion object {
        private val USERS_KEY = stringPreferencesKey("users")
        private val LOGGED_USER_KEY = stringPreferencesKey("logged_user")
    }

    // ✅ Lưu người dùng mới
    suspend fun saveUser(username: String, password: String) {
        context.dataStore.edit { prefs ->
            val existing = prefs[USERS_KEY]
            val jsonArray = if (existing != null) JSONArray(existing) else JSONArray()

            // tránh trùng tên
            for (i in 0 until jsonArray.length()) {
                if (jsonArray.getJSONObject(i).getString("username") == username) return@edit
            }

            val newUser = JSONObject().apply {
                put("username", username)
                put("password", password)
                put("fullName", "")
                put("gender", "")
                put("phone", "")
                put("address", "")
                put("email", "")
                put("avatarUri", "")
            }

            jsonArray.put(newUser)
            prefs[USERS_KEY] = jsonArray.toString()
            prefs[LOGGED_USER_KEY] = username
        }
    }

    // ✅ Cập nhật hồ sơ người dùng
    suspend fun updateUserProfile(
        username: String,
        fullName: String,
        gender: String,
        phone: String,
        address: String,
        email: String,
        avatarUri: String
    ) {
        context.dataStore.edit { prefs ->
            val existing = prefs[USERS_KEY] ?: "[]"
            val jsonArray = JSONArray(existing)

            for (i in 0 until jsonArray.length()) {
                val user = jsonArray.getJSONObject(i)
                if (user.getString("username") == username) {
                    user.put("fullName", fullName)
                    user.put("gender", gender)
                    user.put("phone", phone)
                    user.put("address", address)
                    user.put("email", email)
                    user.put("avatarUri", avatarUri)
                }
            }
            prefs[USERS_KEY] = jsonArray.toString()
        }
    }

    // ✅ Kiểm tra user đã tồn tại
    suspend fun isUserExists(username: String): Boolean {
        return getAllUsers().any { it.username == username }
    }

    // ✅ Lấy danh sách người dùng
    private suspend fun getAllUsers(): List<User> {
        val jsonText = context.dataStore.data.map { it[USERS_KEY] ?: "[]" }.first()
        val jsonArray = JSONArray(jsonText)
        val list = mutableListOf<User>()

        for (i in 0 until jsonArray.length()) {
            val u = jsonArray.getJSONObject(i)
            list.add(
                User(
                    username = u.getString("username"),
                    password = u.getString("password"),
                    fullName = u.optString("fullName", ""),
                    gender = u.optString("gender", ""),
                    phone = u.optString("phone", ""),
                    address = u.optString("address", ""),
                    email = u.optString("email", ""),
                    avatarUri = u.optString("avatarUri", "")
                )
            )
        }
        return list
    }

    // ✅ Kiểm tra đăng nhập
    suspend fun validateUser(username: String, password: String): Boolean {
        val users = getAllUsers()
        val match = users.any { it.username == username && it.password == password }
        if (match) setLoggedInUser(username)
        return match
    }

    // ✅ Lưu và lấy người đăng nhập
    suspend fun setLoggedInUser(username: String) {
        context.dataStore.edit { prefs -> prefs[LOGGED_USER_KEY] = username }
    }

    suspend fun getLoggedInUser(): User? {
        val loggedUser = context.dataStore.data.map { it[LOGGED_USER_KEY] }.first() ?: return null
        return getAllUsers().find { it.username == loggedUser }
    }

    // ✅ Đăng xuất
    suspend fun logout() {
        context.dataStore.edit { prefs -> prefs.remove(LOGGED_USER_KEY) }
    }
    // Trong UserPreferences.kt

    // Hàm tìm kiếm người dùng bằng Số điện thoại (SĐT)
    suspend fun findUserByPhone(phone: String): User? {
        // Logic: Lấy danh sách users, tìm user có phone trùng khớp
        // ...
        return TODO("Provide the return value")
    }

    // Hàm cập nhật mật khẩu
    suspend fun updatePassword(username: String, newPasswordHash: String) {
        // Logic: Tìm user bằng username và cập nhật trường password của họ
        // ...
    }
}
