package com.example.myapplication.data.model

import android.util.Log
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson

data class TasksResponse(
    @SerializedName("data")
    val data: List<Task>? = null,

    @SerializedName("tasks")
    val tasks: List<Task>? = null,

    @SerializedName("results")
    val results: List<Task>? = null
) {
    fun extractTasks(): List<Task> {
        return data ?: tasks ?: results ?: emptyList()
    }

    companion object {
        private const val TAG = "TasksResponse"

        /**
         * Parse an unknown JSON string safely.
         * Works even if API returns:
         *  - { "data": [...] }
         *  - { "tasks": [...] }
         *  - { "results": [...] }
         *  - [{...}, {...}]
         */
        fun fromJsonSafely(json: String): List<Task> {
            return try {
                val gson = Gson()
                val element: JsonElement = JsonParser.parseString(json)

                // Case 1: response là mảng trực tiếp
                if (element.isJsonArray) {
                    val type = object : TypeToken<List<Task>>() {}.type
                    gson.fromJson(json, type)
                }
                // Case 2: response là object wrapper
                else if (element.isJsonObject) {
                    val obj = gson.fromJson(json, TasksResponse::class.java)
                    obj.extractTasks()
                } else {
                    Log.e(TAG, "Unexpected JSON format: $json")
                    emptyList()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error parsing tasks JSON: ${e.message}", e)
                emptyList()
            }
        }
    }
}
