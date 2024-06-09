package test.facebook;

import browser.PlayWrightBrowser;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.facebookPages.FbCreateAccount;
import pages.facebookPages.FbLogin;

import java.util.Properties;

public class FacebookBaseTest {

    protected Properties properties;
    protected Page page;
    protected FbLogin fbLogin;
    protected FbCreateAccount fbCreateAccount;

    @BeforeClass
    public void setUp() {
        PlayWrightBrowser playWrightBrowser = new PlayWrightBrowser();
        properties = playWrightBrowser.getProperties();
        page = playWrightBrowser.launchBrowser(properties);
        page.navigate(properties.getProperty("fbUrl"));
        fbLogin = new FbLogin(page);
    }

    @AfterClass
    public void tearDown() {
        page.context().browser().close();
    }
}
