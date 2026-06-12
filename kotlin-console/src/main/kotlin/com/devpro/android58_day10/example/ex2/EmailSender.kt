package com.devpro.android58_day10.example.ex2

class EmailSender:NotificationSender {
    override fun sendNotification(user: User, message: String) {
        println("Email sent to ${user.name}: $message")
    }
}