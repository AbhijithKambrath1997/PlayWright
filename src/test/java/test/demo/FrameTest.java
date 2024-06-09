package test.demo;

import browser.PlayWrightBrowser;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;
import uiUtil.FrameUtil;
import java.util.Properties;

public class FrameTest {

    @Test
    private void frameTest(){
        PlayWrightBrowser playWrightBrowser = new PlayWrightBrowser();
        Properties properties = playWrightBrowser.getProperties();
        Page page = playWrightBrowser.launchBrowser(properties);
        FrameUtil frameUtil = new FrameUtil();
        page.navigate("https://ui.vision/demo/webtest/frames/");

        Frame frame1 = frameUtil.switchToFrameBySelector(page, "//frame[@src='frame_1.html']");
        frame1.fill("//input[@name='mytext1']", "Abhi");

        Frame frame2 = frameUtil.switchToFrameByIndex(page, 2);
        frame2.fill("//input[@name='mytext2']", "Abhi");

        Frame frame4 = frameUtil.switchToFrameByIndex(page, 4);
        frame4.fill("//input[@name='mytext4']", "Abhi");

        Frame frame5 = frameUtil.switchToFrameBySelector(page, "//frame[@src='frame_5.html']");
        frame5.fill("//input[@name='mytext5']", "Abhi");

        frameUtil.switchToParentFrame(page);
        System.out.println(page.title());
        page.context().browser().close();
    }
}
