package test.gmail;


import browser.PlayWrightBrowser;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.gmailPages.CreateAccountPage;
import pages.gmailPages.LoginPage;

import java.util.Properties;

public class GmailBaseTest {

    protected Properties properties;
    protected Page page;

    protected LoginPage loginPage;
    protected CreateAccountPage createAccountPage;

    @BeforeClass
    public void setUp() {
        PlayWrightBrowser playWrightBrowser = new PlayWrightBrowser();
        properties = playWrightBrowser.getProperties();
        page = playWrightBrowser.launchBrowser(properties);
        page.navigate(properties.getProperty("gmailUrl"));
        loginPage = new LoginPage(page);
    }

    @AfterClass
    public void tearDown() {
        page.context().browser().close();
    }
}
