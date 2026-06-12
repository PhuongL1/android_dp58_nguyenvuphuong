package com.devpro.android58_day10.example.ex2

class ConsoleSender : NotificationSender {
    override fun sendNotification(user: User, message: String) {
        println("Notification sent to ${user.name}: $message")
    }
}