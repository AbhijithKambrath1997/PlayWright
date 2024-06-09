package test.gmail;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class GmailCreateAccountTest extends GmailBaseTest{

    @BeforeClass
    private void init(){
        createAccountPage = loginPage.navigateToCreateAccount();
    }

    @Test
    public void testCreateAccountPage(){
        createAccountPage.fillName("Abhi", "Jith");
        List<String> details = createAccountPage.nameDetails();
        System.out.println("First name given is " + details.get(0));
        System.out.println("last name given is " + details.get(1));

        System.out.println(createAccountPage.verifyTextField());
        System.out.println(createAccountPage.verifyDescriptionField());
        createAccountPage.navigateToDetailsPage();
    }
}
