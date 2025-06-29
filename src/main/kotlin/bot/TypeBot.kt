package id.neotica.bot

import java.awt.Robot
import java.awt.event.KeyEvent

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

fun typeBot2(robot: Robot, input: String) {
    var shiftHeld = false

    for (char in input) {
        val keyCode = KeyEvent.getExtendedKeyCodeForChar(char.code)
        if (keyCode.toChar() == KeyEvent.CHAR_UNDEFINED) continue

        val needsShift = Character.isUpperCase(char) || needShift(char)

        // Press SHIFT only if needed and not already held
        if (needsShift && !shiftHeld) {
            robot.keyPress(KeyEvent.VK_SHIFT)
            shiftHeld = true
        } else if (!needsShift && shiftHeld) {
            robot.keyRelease(KeyEvent.VK_SHIFT)
            shiftHeld = false
        }

        robot.keyPress(keyCode)
        robot.keyRelease(keyCode)
        // No delay — maximum speed
    }

    if (shiftHeld) robot.keyRelease(KeyEvent.VK_SHIFT)
}