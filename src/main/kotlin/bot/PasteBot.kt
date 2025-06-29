package id.neotica.bot

import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent
import java.util.Locale

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
