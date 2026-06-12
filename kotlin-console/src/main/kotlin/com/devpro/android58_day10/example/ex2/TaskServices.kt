package com.devpro.android58_day10.example.ex2

class TaskServices(private val notificationSender: NotificationSender) {
    private val listTask = mutableListOf<Task>()

    fun createTask(user: User, id: String, title: String):Boolean {
        if (listTask.any { it.id == id }) {
            return false
        }
        if (title.isEmpty()) {
            return false
        }
        val task = Task(id, title)
        listTask.add(task)
        notificationSender.sendNotification(user, "New task created: ${task.title}")
        return true
    }

    fun markDone(id:String): Boolean{
        val task = listTask.find { it.id == id }
        if (task == null) {
            return false
        }
        task.markDone()
        return true
    }

    fun listOpenTask(): List<Task>{
        return listTask.filter { !it.isComplete() }
    }
}