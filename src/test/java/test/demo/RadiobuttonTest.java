package test.demo;

import browser.PlayWrightBrowser;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;
import uiUtil.RadiobuttonUtil;

import java.util.Properties;

import static org.testng.AssertJUnit.*;

public class RadiobuttonTest {

    @Test(retryAnalyzer = retry.RetryAnalyzer.class)
    private void radioButtonTest() {
        PlayWrightBrowser playWrightBrowser = new PlayWrightBrowser();
        Properties properties = playWrightBrowser.getProperties();
        Page page = playWrightBrowser.launchBrowser(properties);
        page.navigate("https://qa-automation-practice.netlify.app/radiobuttons.html");
        RadiobuttonUtil radiobuttonUtil = new RadiobuttonUtil(page);
        radiobuttonUtil.selectRadioButton("//input[@id='radio-button3']");
        assertTrue(radiobuttonUtil.isRadioButtonSelected("//input[@id='radio-button3']"));
        //To Test RetryAnalyzer
        /* String value = radiobuttonUtil.getSelectedRadioButtonValue("//input[@id='radio-button3']");
        assertEquals(value, "RadioButton3", "value fo radio-button is blank");*/
        assertFalse(radiobuttonUtil.isRadioButtonSelectable("//input[@id='radio-button4']"));
    }
}
