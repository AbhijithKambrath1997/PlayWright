package testUtil;

import browser.PlayWrightBrowser;
import com.microsoft.playwright.Page;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.Properties;

public class TestTemplate {

    @Getter(AccessLevel.PROTECTED)
    public PlayWrightBrowser playWrightBrowser = new PlayWrightBrowser();

    @Getter(AccessLevel.PROTECTED)
    Properties properties = playWrightBrowser.getProperties();

    @Getter(AccessLevel.PROTECTED)
    public TestLogger logger = new TestLogger();

    public void print(String value){
        logger.getLogger(value);
    }

    public Page lauchPage(){
        return playWrightBrowser.launchBrowser(properties);
    }

}
