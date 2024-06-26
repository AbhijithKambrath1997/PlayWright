package retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    int counter = 0;
    int limit = 2;

    public boolean retry(ITestResult iTestResult) {
        if (counter< limit){
            counter++;
            return true;
        }
        return false;
    }
}
