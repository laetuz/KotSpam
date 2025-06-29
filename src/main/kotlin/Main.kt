package id.neotica

import java.awt.Robot
import java.awt.event.KeyEvent

fun main() {
    val defaultMessage = ""
    val defaultCount = 0

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
            println("one")
            return
        }
        else -> {
            println("Enter the number of times to send: ")
            val count = readlnOrNull()?.toIntOrNull() ?: 0
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
    }
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

fun needShift(char: Char): Boolean = "!@#$%^&*()_+{}:\"<>?~".contains(char)