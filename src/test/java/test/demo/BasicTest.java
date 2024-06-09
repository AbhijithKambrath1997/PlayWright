package test.demo;

import browser.PlayWrightBrowser;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;
import testUtil.TestTemplate;

import java.util.Properties;

public class BasicTest {

    @Test
    private void urlTest(){
        TestTemplate template = new TestTemplate();
        template.print("PlayWright Begin");
        PlayWrightBrowser playWrightBrowser = new PlayWrightBrowser();
        Properties properties = playWrightBrowser.getProperties();
        Page page = playWrightBrowser.launchBrowser(properties);
        page.navigate("https://www.gmail.com/");
        page.context().browser().close();

    }
}
