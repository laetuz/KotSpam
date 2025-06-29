package id.neotica

import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent
import java.util.Locale

fun main() {

    println("Enter 1 if you want to send default message.")
    println("Enter the message to be sent: ")
    val message = readlnOrNull() ?: ""

    val robot = Robot()

    when (message) {
        "" -> {
            println("Message is empty.")
            return
        }
        "1" -> {
            val message2 = defaultMessage
            val count = defaultCount

            sendMessageQuick(
                message = message2,
                count = count,
                robot = robot
            )
        }
        else -> {
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

fun typeBot(robot: Robot, input: String) {
    for (char in input) {
        val keyCode = KeyEvent.getExtendedKeyCodeForChar(char.code)
        if (KeyEvent.CHAR_UNDEFINED.code == keyCode) {
            throw IllegalArgumentException("Cant type this shit")
        }

        val isUpperCase = Character.isUpperCase(char)
        val requireShift = needShift(char)

        if (isUpperCase || requireShift) robot.keyPress(KeyEvent.VK_SHIFT)

        robot.keyPress(keyCode)
        robot.keyRelease(keyCode)

        if (isUpperCase || requireShift) robot.keyRelease(KeyEvent.VK_SHIFT)

        robot.delay(10)
    }
}

fun pasteBot(robot: Robot, input: String) {
    val stringSelection = StringSelection(input)
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard

    clipboard.setContents(stringSelection, stringSelection)

    val os = System.getProperty("os.name").lowercase(Locale.getDefault())
    val pasteKey = if (os.contains("mac")) {
        KeyEvent.VK_META
    } else {
        KeyEvent.VK_CONTROL
    }

    robot.apply {
        keyPress(pasteKey)
        keyPress(KeyEvent.VK_V)
        keyRelease(KeyEvent.VK_V)
        keyRelease(pasteKey)
    }
}

fun needShift(char: Char): Boolean = "!@#$%^&*()_+{}:\"<>?~".contains(char)