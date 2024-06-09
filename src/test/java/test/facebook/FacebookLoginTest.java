package test.facebook;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FacebookLoginTest extends FacebookBaseTest {


    @Test
    public void testFbLoginPage() {
        List<String> details = fbLogin.getPageDetails();
        System.out.println("Page url is : " + details.get(0));
        System.out.println("Page title is : " + details.get(1));

        Assert.assertTrue(fbLogin.isVisible(), "Icons are not Visible");
        Assert.assertTrue(fbLogin.isEnabled(), "Buttons are not Enabled");

        fbLogin.setId(properties.getProperty("fbUserName"));
        fbLogin.setPassword(properties.getProperty("fbPassword"));

    }
}
