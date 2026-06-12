package com.devpro.android58_day10.example.ex2

fun main() {
    val user = User("u1", "Nguyen Vu Phuong")
    val taskServices = TaskServices(ConsoleSender())

    println("\n create task")
    println("Create task 1: ${taskServices.createTask(user, "1", "Learn Kotlin OOP")}")
    println("Create task 2: ${taskServices.createTask(user, "2", "Do homework")}")
    println("Create duplicate task id 1: ${taskServices.createTask(user, "1", "Duplicate task")}")
    println("Create task 3 with empty title: ${taskServices.createTask(user, "3", "")}")

    println("\n task before mark done:")
    printOpenTasks(taskServices)

    println("\n mark done:")
    println("Mark task 2 done: ${taskServices.markDone("2")}")
    println("Mark task 99 done: ${taskServices.markDone("99")}")

    println("\n task after mark done:")
    printOpenTasks(taskServices)

    println("\n test email")
    val emailTaskServices = TaskServices(EmailSender())
    println("Create email task: ${emailTaskServices.createTask(user, "1", "Learn Kotlin OOP")}")
}

fun printOpenTasks(taskServices: TaskServices) {
    val openTasks = taskServices.listOpenTask()

    if (openTasks.isEmpty()) {
        println("No open tasks")
        return
    }

    openTasks.forEach {
        println("Task id: ${it.id}, title: ${it.title}, completed: ${it.isComplete()}")
    }
}