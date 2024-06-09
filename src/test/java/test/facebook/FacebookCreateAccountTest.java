package test.facebook;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FacebookCreateAccountTest extends FacebookBaseTest {

    @BeforeClass
    private void init(){
        fbCreateAccount = fbLogin.navigateToFbCreateAccount();
    }

    @Test
    public void testCreateNewFbAccount(){
        fbCreateAccount.setNameFields(properties.getProperty("fbNewName"), properties.getProperty("fbSurName"));
        fbCreateAccount.setIdAndPassWord(properties.getProperty("fbNewEmailId"),
                properties.getProperty("fbNewPassword"));
    }
}
