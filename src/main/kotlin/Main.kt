package id.neotica

import id.neotica.android.AdbInput
import id.neotica.bot.pasteBot
import id.neotica.bot.typeBot
import id.neotica.bot.typeBot2
import java.awt.Robot
import java.awt.event.KeyEvent

fun main() {

    println("Enter 1 if you want to send default message.")
    println("Enter 3 for adbInput")
    println("Enter 3a for default adbInput")
    println("Enter 3b for android Adb control")
    println("Enter 4 for inputting using text and count")
    println("Enter the message to be sent: ")
    val message = readlnOrNull() ?: ""

    val robot = Robot()

    when (message) {
        "" -> {
            println("Message is empty.")
            return
        }
        "1" -> {
            val message2 = messageList.random()
            val count = defaultCount

            sendMessageQuick(
                message = message2,
                count = count,
                robot = robot
            )
        }
        "2" -> {
            println("Preparing to send $defaultCount random messages from the list.")
            println("Starting in 5 seconds, switch to your target window now.")
            for (i in 5 downTo 1) {
                println("$i...")
                Thread.sleep(1000)
            }
            println("Starting spam!")

            for (i in 1..defaultCount) {
                // 1. A NEW RANDOM MESSAGE IS PICKED ON EVERY ITERATION
                val randomMessage = messageList.random()
                print("Sending ($i/$defaultCount): \"$randomMessage\"") // Print which message is being sent

                // 2. The message is sent using the fast 'paste' method
                typeBot2(robot, randomMessage)
                robot.keyPress(KeyEvent.VK_ENTER)
                robot.keyRelease(KeyEvent.VK_ENTER)

                // Add a dot for progress and wait
                println(" ...Sent.")
                Thread.sleep(100) // Small delay between messages
            }

            println("Finished spamming!")
        }
        "3" -> {
            while (true) {
                print("Type message to send (or 'exit'): ")
                val input = readlnOrNull() ?: break
                if (input.lowercase() == "exit") break
                AdbInput.sendText(input)
                Thread.sleep(200)
                AdbInput.sendEnter()
                Thread.sleep(300)
            }
        }
        "3a" -> {
            for (i in 1..20) {
                AdbInput.sendText(messageList.random())
                Thread.sleep(200)
                AdbInput.sendEnter()
                Thread.sleep(300)
            }
            println("Finished spamming!")
        }
        "3b" -> {
            while (true) {
                println("Type --list for command list")
                Thread.sleep(500)
                print("Type message to send (or 'exit'): ")
                val input = readlnOrNull() ?: break
                when(input) {
                    "--list" -> println(adbCommands)
                    "home" -> AdbInput.homeButton()
                    "recent" -> AdbInput.recentButton()
                    "back" -> AdbInput.backButton()
                    "switch" -> AdbInput.switchApp()
                    "next", ">" -> AdbInput.nextButton()
                    "prev", "<" -> AdbInput.prevButton()
                    "up" -> AdbInput.upButton()
                    "down" -> AdbInput.downButton()
                    "enter" -> AdbInput.sendEnter()
                    "write" -> {
                        if (input.lowercase() == "exit") break
                        AdbInput.sendText(input)
                        Thread.sleep(200)
                        AdbInput.sendEnter()
                        Thread.sleep(300)
                    }
                    "aMan" -> AdbInput.activityManager()
                    "exit" -> break
                    else -> {
                        println("nah")
                    }
                }
            }
        }
        "4" -> {
            println("Enter the number of times to send: ")
            val count = readlnOrNull()?.toIntOrNull() ?: 0

            println("Use typing simulation? (y/n): ")
            val typing = readlnOrNull()?: ""

            when (typing) {
                "y" -> {
                    sendMessageWithTyping(
                        message = message,
                        count = count,
                        robot = robot
                    )
                }
                "n" -> {
                    sendMessageQuick(
                        message = message,
                        count = count,
                        robot = robot
                    )
                }
                "" -> {
                    println("Wrong input.")
                    return
                }
            }
        }
        else -> {
            println("Yeah input something else.")
        }
    }

}

fun sendMessageWithTyping(message: String, count: Int, robot: Robot) {
    println("Here is your message: $message $count")
    if (count <= 0) {
        println("Invalid count.")
        return
    }

    println("Starting in 5 seconds, switch to your target window now.")
    for (i in 5 downTo 1) {
        println("$i...")
        Thread.sleep(1000)
    }
    println("Starting spam!")

    for (i in 1..count) {
        typeBot(robot, message)
        robot.keyPress(KeyEvent.VK_ENTER)
        robot.keyRelease(KeyEvent.VK_ENTER)
        Thread.sleep(100)
    }

    println("Finished spamming!")
}

fun sendMessageQuick(message: String, count: Int, robot: Robot) {
    println("Here is your message: $message $count")
    if (count <= 0) {
        println("Invalid count.")
        return
    }

    println("Starting in 5 seconds, switch to your target window now.")
    for (i in 5 downTo 1) {
        println("$i...")
        Thread.sleep(1000)
    }
    println("Starting spam!")

    for (i in 1..count) {
        pasteBot(robot, message)
        robot.keyPress(KeyEvent.VK_ENTER)
        robot.keyRelease(KeyEvent.VK_ENTER)
        Thread.sleep(100)
    }

    println("Finished spamming!")
}