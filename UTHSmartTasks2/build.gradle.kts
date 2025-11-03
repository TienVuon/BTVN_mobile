// File build.gradle.kts cấp độ dự án
plugins {
    id("com.android.application") // Không cần thiết nếu bạn sử dụng alias trong file cấp độ app
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.gms.google-services") version "4.4.4" apply false
}

dependencies {
    // Plugin Firebase Google services (không phải firebase-bom)
    classpath("com.google.gms:google-services:4.4.4")
}
