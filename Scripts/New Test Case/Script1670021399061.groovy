import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.awt.Robot
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent
import java.nio.charset.Charset
import java.text.Format
import java.text.SimpleDateFormat

import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.time.TimeCategory

def setHardText(String text) {
	Robot robot = new Robot();
	StringSelection selection = new StringSelection(text);
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	clipboard.setContents(selection, selection);
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
}
Format formatter = new SimpleDateFormat('MMMM d yyyy EEEE')

String s = formatter.format(new Date().plus(3))

String FromDate = String.format('//Pane[1]/Button[@Name=\'%s\']', 
    s)

String ToDate = String.format('//Pane[1]/Button[@Name=\'%s\']', 
    s)
Date date = new Date()
String timePart = date.format("HH:mm a")
use( TimeCategory ) {
	fromTime = (new Date() + 90.minutes)
	toTime = fromTime+ 30.minutes
	fromTime =fromTime.format( 'HH:mm a' )
	toTime = toTime.format( 'HH:mm a' )
}
byte[] array = new byte[30]; 
new Random().nextBytes(array);
String generatedString = new String(array, Charset.forName("UTF-8"));


Windows.startApplicationWithTitle('C:\\Users\\Diaa\\AppData\\Roaming\\Zoom\\bin\\Zoom.exe', '')

Windows.click(findWindowsObject('Object Repository/Button(2)'))

Windows.switchToWindowTitle('Schedule meeting')

Windows.clearText(findWindowsObject('Object Repository/Edit'))

Windows.click(findWindowsObject('Edit'))

Windows.sendKeys(findWindowsObject('Edit'), 'This is an meeting created by automation script')

Windows.click(findWindowsObject('Object Repository/StartDate'))

Windows.click(findWindowsObject('Object Repository/DateButton', [('xpath') : FromDate]))
Windows.click(findWindowsObject('Object Repository/StartTime'))
Thread.sleep(2000)
setHardText(fromTime)
Thread.sleep(2000)
Windows.click(findWindowsObject('Object Repository/EndTime'))
setHardText(toTime)
Thread.sleep(2000)
Windows.click(findWindowsObject('Object Repository/endDate'))
Windows.click(findWindowsObject('Object Repository/DateButton', [('xpath') : FromDate]))
Windows.clearText(findWindowsObject('Object Repository/editPassCode'))
Windows.click(findWindowsObject('editPassCode'))
Windows.sendKeys(findWindowsObject('editPassCode'), generatedString)
Windows.click(findWindowsObject('save'))

Windows.closeApplication()
Windows.switchToWindowTitle('Zoom')
Windows.closeApplication()

