package test.demo;

import browser.PlayWrightBrowser;
import com.microsoft.playwright.Page;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testUtil.TestTemplate;

import java.util.List;
import java.util.Properties;


public class BasicGmailTest {

    private Page page;
    TestTemplate template;

    @BeforeClass
    private void dataSetUp() {
        template = new TestTemplate();
        PlayWrightBrowser playWrightBrowser = new PlayWrightBrowser();
        Properties properties = playWrightBrowser.getProperties();
        page = playWrightBrowser.launchBrowser(properties);
    }

    @AfterClass
    private void cleanUp() {
        page.close();
    }

    @Test
    public void testGmailContents() {

        page.navigate("https://www.gmail.com/");
        page.locator("text=to continue to Gmail").first().isVisible();

        template.print("text with parent class name");
        page.locator("span:has-text('to continue to Gmail')").isVisible();

        template.print("text with parent and super parent class");
        page.locator("div.tosRNd span:has-text('to continue to Gmail')").isVisible();
        page.locator("div.PrDSKc a:has-text('Learn more')").isVisible();
        page.locator("button:has-text('Forgot email?')").isVisible();

        page.locator("text=Create account").click();
        page.locator("text=For my personal use").click();

        page.locator("div[class='Xb9hP'] input[name='firstName']")
                .fill(RandomStringUtils.randomAlphabetic(8).toUpperCase());
        page.locator("//div[@class='Xb9hP'] //input[@name='lastName']")
                .fill(RandomStringUtils.randomAlphabetic(8).toUpperCase());
    }

    @Test
    public void testAmazonVisible() {
        template.print("Login to Amazon");
        page.navigate("https://www.amazon.in/");

        List<String> texts = page.locator("a:visible").allInnerTexts();
        for (String text : texts) {
            template.print(text);
        }
        int count = page.locator("xpath=//img >> visible=true").count();
        template.print(String.valueOf(count));
    }
}
