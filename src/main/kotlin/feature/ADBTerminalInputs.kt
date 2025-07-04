package id.neotica.feature

import id.neotica.adbCommands
import id.neotica.feature.android.AdbInput

fun adbTerminalInputs() {
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
                print("Write message: ")
                val writeInput = readlnOrNull() ?: break

                AdbInput.sendText(writeInput)
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