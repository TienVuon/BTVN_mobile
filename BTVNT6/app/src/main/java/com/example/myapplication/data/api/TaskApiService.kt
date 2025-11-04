package com.example.myapplication.data.api

import com.example.myapplication.data.model.Task
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface TaskApiService {
    // 1️⃣ Lấy toàn bộ task
    @GET("tasks")
    suspend fun getAllTasks(): Response<List<Task>>

    @GET("tasks")
    suspend fun getAllTasksRaw(): Response<ResponseBody>

    // 2️⃣ Lấy task theo ID
    @GET("task/{id}")
    suspend fun getTaskById(@Path("id") id: Int): Response<Task>

    @GET("task/{id}")
    suspend fun getTaskByIdRaw(@Path("id") id: Int): Response<ResponseBody>

    // 3️⃣ Xóa task
    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<Unit>

    // 🆕 4️⃣ Thêm task mới
    @POST("task")
    suspend fun createTask(@Body task: Task): Response<Task>

    // 🆕 5️⃣ Cập nhật thông tin task
    @PUT("task/{id}")
    suspend fun updateTask(@Path("id") id: Int, @Body task: Task): Response<Task>

    // 🆕 6️⃣ Đánh dấu hoàn thành
    @PATCH("task/{id}/complete")
    suspend fun markTaskCompleted(@Path("id") id: Int): Response<Task>

    // 🆕 7️⃣ Lọc task theo trạng thái
    @GET("tasks/status/{status}")
    suspend fun getTasksByStatus(@Path("status") status: String): Response<List<Task>>

    // 🆕 8️⃣ Tìm kiếm task theo từ khóa
    @GET("tasks/search")
    suspend fun searchTasks(@Query("q") keyword: String): Response<List<Task>>
}
