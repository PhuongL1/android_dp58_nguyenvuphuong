plugins {
    kotlin("jvm")
    application
}

application {
    mainClass.set("com.devpro.android58_day10.MainKt")
    mainClass.set("com.devpro.android58_day10.example.ex2.MainEx2Kt")
}

// Cho phép đọc input từ bàn phím khi chạy bằng Gradle
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
tasks.withType<JavaExec>().configureEach {
    jvmArgs("-Dfile.encoding=UTF-8")
    systemProperty("file.encoding", "UTF-8")
}

dependencies {
    implementation(kotlin("stdlib"))
}
