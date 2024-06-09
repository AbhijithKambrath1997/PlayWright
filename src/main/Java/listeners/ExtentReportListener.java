package listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import static browser.PlayWrightBrowser.takeScreenshot;

public class ExtentReportListener implements ITestListener {

    private static final String OUTPUT_FOLDER = "./build/";
    private static final String FILE_NAME = "TestExecutionReport.html" + System.currentTimeMillis();
    private static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite started!");

        // Initialize extent only if it is not already initialized
        if (extent == null) {
            try {
                extent = init(context);
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static ExtentReports init(ITestContext context) throws UnknownHostException {
        Path path = Paths.get(OUTPUT_FOLDER);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.fillInStackTrace();
            }
        }

        String reportName = context.getCurrentXmlTest().getParameter("reportName");

        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        reporter.config().setReportName(reportName);
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("System", System.getProperty("os.name"));
        extentReports.setSystemInfo("Author", InetAddress.getLocalHost().getHostName());

        return extentReports;
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Test Suite is ending!"));
        extent.flush();
        test.remove();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        System.out.println(methodName + " started!");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());

        extentTest.assignCategory(result.getTestContext().getSuite().getName());

        methodName = StringUtils.capitalize(StringUtils.join(StringUtils.
                splitByCharacterTypeCamelCase(methodName), StringUtils.SPACE));

        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        test.get().pass("Test passed");
        test.get().pass(result.getThrowable(), MediaEntityBuilder
                .createScreenCaptureFromBase64String(takeScreenshot(), result.getMethod().getMethodName()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        test.get().fail(result.getThrowable(), MediaEntityBuilder
                .createScreenCaptureFromBase64String(takeScreenshot(), result.getMethod().getMethodName()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        test.get().skip(result.getThrowable(), MediaEntityBuilder
                .createScreenCaptureFromBase64String(takeScreenshot(), result.getMethod().getMethodName()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
