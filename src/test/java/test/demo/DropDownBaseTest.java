package test.demo;

import browser.PlayWrightBrowser;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;
import uiUtil.DropDownUtil;

import java.util.Properties;

public class DropDownBaseTest {

    @Test
    public void dropDownTest(){
        PlayWrightBrowser playWrightBrowser = new PlayWrightBrowser();
        Properties properties = playWrightBrowser.getProperties();
        Page page = playWrightBrowser.launchBrowser(properties);
        page.navigate("https://www.bbc.com/weather");
        DropDownUtil dropDownUtil = new DropDownUtil(page);
        dropDownUtil.clickDropDownByIndex("//select[@id='language_cookie-aria-id']", 2);
        dropDownUtil.clickDropDownByText("//select[@id='language_cookie-aria-id']", "Cymraeg");
        dropDownUtil.clickDropDownByValue("//select[@id='language_cookie-aria-id']", "gd");

        System.out.println(dropDownUtil.getOptionCounts("//select[@id='language_cookie-aria-id']"));
        System.out.println(dropDownUtil.getOptionText("//select[@id='language_cookie-aria-id']"));
    }
}
