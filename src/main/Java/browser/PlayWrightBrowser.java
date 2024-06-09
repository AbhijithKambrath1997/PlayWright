package browser;

import com.microsoft.playwright.*;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class PlayWrightBrowser {


    private static final String propertiesPath = "./src/test/resources/configs/config.properties";
    private Properties properties;

    private static final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> browserContextThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    public static Playwright getPlayWright() {
        return playwrightThreadLocal.get();
    }

    public static Browser getBrowser() {
        return browserThreadLocal.get();
    }

    public static BrowserContext getBrowserContext() {
        return browserContextThreadLocal.get();
    }

    public static Page getPage() {
        return pageThreadLocal.get();
    }

    public Page launchBrowser(Properties properties) {
        playwrightThreadLocal.set(Playwright.create());
        boolean headless = Boolean.parseBoolean(properties.getProperty("headless"));

        switch (properties.getProperty("browser")) {
            case "Chromium":
                browserThreadLocal.set(getPlayWright().chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless)));
                break;
            case "WebKit":
                browserThreadLocal.set(getPlayWright().webkit().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless)));
                break;
            case "FireFox":
                browserThreadLocal.set(getPlayWright().firefox().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless)));
                break;
            case "Chrome":
                browserThreadLocal.set(getPlayWright().chromium().launch(new
                        BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless)));
                break;
            case "FireFoxBrowser":
                browserThreadLocal.set(getPlayWright().firefox().launch(new
                        BrowserType.LaunchOptions().setChannel("firefox").setHeadless(headless)));
                break;
            default:
                Reporter.log("Please provide a valid browser from the list");
        }

        browserContextThreadLocal.set(getBrowser().newContext());
        pageThreadLocal.set(getBrowserContext().newPage());
        return getPage();
    }

    /**
     * Set properties from file
     */
    public Properties getProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream(propertiesPath);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.fillInStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    /**
     * take screenshot
     */

    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        //getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

        return Base64.getEncoder().encodeToString(buffer);
    }

}