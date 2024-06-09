package test.demo;

import browser.PlayWrightBrowser;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;
import uiUtil.CheckboxUtil;

import java.util.Properties;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CheckboxTest {

    @Test
    private void checkboxTest() {
        PlayWrightBrowser playWrightBrowser = new PlayWrightBrowser();
        Properties properties = playWrightBrowser.getProperties();
        Page page = playWrightBrowser.launchBrowser(properties);
        page.navigate("https://deformdemo.pylonsproject.org/checkboxchoice2/");
        CheckboxUtil checkBoxUtil = new CheckboxUtil(page);
        checkBoxUtil.selectCheckBox("//input[@type='checkbox'][@value='habanero']");
        assertTrue(checkBoxUtil.isCheckboxSelected("//input[@type='checkbox'][@value='habanero']"));
        assertFalse(checkBoxUtil.isCheckboxSelected("//input[@type='checkbox'][@value='jalapeno']"));
        checkBoxUtil.clearCheckboxSelection("//input[@type='checkbox'][@value='habanero']");
        assertFalse(checkBoxUtil.isCheckboxSelected("//input[@type='checkbox'][@value='habanero']"));
    }
}
