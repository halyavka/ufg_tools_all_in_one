package pageObjPattern;

import com.google.common.base.Function;
import config.AppConfig;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import utils.AppUtil;
import utils.SysUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class BasicTestsExecutor {
    protected WebDriver webDriver;
    protected long userID;
    protected static final Logger LOG = Logger.getLogger(BasicTestsExecutor.class);
    protected boolean isTestFailed = false;
    private static String testName;
    protected long testStartTime;
    protected long testEndTime;

    public static String getTestName() {
        return testName;
    }

    public BasicTestsExecutor() {
    }

    public BasicTestsExecutor(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

   /* static {
        PropertyConfigurator.configure(AppUtil.loadPropertiesFromClassPath("/log4j.properties"));
    }*/


   /* @AfterSuite
    public void shutDownConnectionPool() {
        //Set<Map.Entry<String, SimpleConnectionPool>> entries = new HashSet<>(SimpleConnectionPool.getAllPools().entrySet());
        //Iterator<Map.Entry<String, SimpleConnectionPool>> iterator = entries.iterator();
        //if (entries.size() > 0) LOG.info("Shutting down the pools... ");
        for (int i = 0; i < entries.size(); i++) {
            Map.Entry<String, SimpleConnectionPool> entry = iterator.next();
            DataSource dataSource = entry.getValue().getDataSource();
            try {
                SimpleConnectionPool.shutdownDataSource(dataSource);
                LOG.info("Pool manager for DB " + entry.getKey().toString() + " has been shutdown.");
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.warn("Pool manager for DB " + entry.getKey().toString() + " has not been shutdown. " + e);
            }

        }
    }
*/
    @AfterClass
    public void closeBrowsers() {
        if (webDriver != null) webDriver.quit();
    }

    @BeforeMethod
    public void doBeforeMethod(Method method) throws Exception {
        testStartTime = System.currentTimeMillis() / 1000;
        LOG.info("//////////////////////////////////////////////////////////////");
        isTestFailed = false;
        LOG.info("TestCase " + this.getClass().getSimpleName() + " AND METHOD: " + method.getName() + " is starting");
        testName = this.getClass().getSimpleName() + "_" + method.getName();
    }

    @AfterMethod
    public void doAfterMethod(Method method, ITestResult result) throws Exception {
        testEndTime = System.currentTimeMillis() / 1000;
        isTestFailed = !result.isSuccess();
        if (!isTestFailed) {
            LOG.info("TestCase: " + this.getClass().getSimpleName() + " AND METHOD: " + method.getName() + " is passed. Test execution time " + (testEndTime - testStartTime) + " sec");
        } else {
            LOG.error("TestCase " + this.getClass().getSimpleName() + " AND METHOD: " + method.getName() + " is failed. Test execution time " + (testEndTime - testStartTime) + " sec");
        }
        getScreenShotIfNeed(result);
      //  logoutFromAccount();
        if (result.getMethod().getRetryAnalyzer() != null) {
            if (result.getMethod().getRetryAnalyzer() instanceof Analyzer && result.getMethod().getRetryAnalyzer().retry(result)) {
                webDriver.quit();
                webDriver = null;
            }
        }
    }

    public void getScreenShotIfNeed(ITestResult result) {
        String folder;
        if (result.isSuccess()) {
            folder = "success";
        } else {
            folder = "failed";
        }
        Reporter.setCurrentTestResult(result);
        File file = new File("");
        getScreenShot(file.getAbsolutePath() + "\\target\\surefire-reports\\html\\screenshots\\" + folder + "\\" + result.getName());
        try {
            FileUtils.copyDirectory(new File(file.getAbsolutePath() + "\\target\\surefire-reports\\html\\screenshots\\" + folder + "\\" + result.getName()), new File(file.getAbsolutePath() + "\\autotests\\resources\\screenshots\\" + folder + "\\" + result.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Reporter.setCurrentTestResult(result);
        Reporter.log(result.getName());
        Reporter.log(" screenshot saved at " + file.getAbsolutePath() + "\\target\\surefire-reports\\html\\screenshots\\" + folder + "\\" + result.getName());
        Reporter.log("<a href='screenshots/" + folder + "/" + result.getName() +
                "/screenshot.png'><img src='screenshots/" + folder + "/" +
                result.getName() + "/screenshot.png' hight='100' width='100'/></a>");
        Reporter.setCurrentTestResult(null);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public boolean isElementPresent(By element) {
        try {
            webDriver.findElement(element);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresent(By element, int timeoutToCheckInSeconds) {
        try {
            webDriver.manage().timeouts().implicitlyWait(timeoutToCheckInSeconds, TimeUnit.SECONDS);
            webDriver.findElement(element);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresent(WebElement element) {
        try {
            return element != null;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isFramePresent(String frameName) {
        try {
            return webDriver.switchTo().frame(frameName) != null;
        } catch (NoSuchFrameException e) {
            return false;
        }
    }

    public boolean isTextPresent(String text) {
        try {
            return webDriver.findElement(By.tagName("body")).getText().contains(text);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isTextPresentFromPageSource(String text) {
        try {
            return webDriver.getPageSource().contains(text);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isTextPresentByRegExp(String textSource, String textMatcher) {
        try {
            return textSource.matches(textMatcher);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void waitForTextPresentInElement(final By element, final String text) {
        final Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withMessage("Text '" + text + "' is not yet present in " + element)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(element, text));
    }

    public void waitForTextPresentInElementValue(final By element, final String text) {
        final Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withMessage("Text '" + text + "' is not yet present in " + element + " value")
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    public void waitForElementIsSelected(final By element) {
        final Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withMessage("Element '" + element + "' is not selected yet")
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public void waitForElementIsSelected(final WebElement element) {
        final Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withMessage("Element '" + element + "' is not selected yet")
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public void waitForTextPresent(String text) throws Exception {
        final int waitRetryDelayMs = 100; //шаг итерации (задержка)
        final int timeOut = 500;  //время тайм маута
        boolean first = true;
        try {
            for (int milliSecond = 0; ; milliSecond += waitRetryDelayMs) {
                if (milliSecond > timeOut * 100) {
                    LOG.info("Timeout: Text " + text + " is not found during " + milliSecond / 1000 + " sec.");
                    break; //если время ожидания закончилось (элемент за выделенное время не был найден)
                }
                if (webDriver.getPageSource().contains(text)) {
                    if (!first) LOG.info("Text is found: " + text + "");
                    break; //если элемент найден
                }
                if (first) LOG.info("Waiting for text is present: " + text + "");
                first = false;
                Thread.sleep(waitRetryDelayMs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForTextNotPresent(String text) throws Exception {
        final int waitRetryDelayMs = 100; //шаг итерации (задержка)
        final int timeOut = 500;  //время тайм маута
        boolean first = true;
        try {
            for (int milliSecond = 0; ; milliSecond += waitRetryDelayMs) {
                if (milliSecond > timeOut * 100) {
                    LOG.info("Timeout: Text '" + text + "' is still here after " + milliSecond / 1000 + " sec.");
                    break;
                }
                if (!webDriver.getPageSource().contains(text)) {
                    if (first) LOG.info("Text is gone: '" + text + "");
                    break; //если элемент найден
                }
                if (!first) LOG.info("Waiting for text not present: '" + text + "");
                first = false;
                Thread.sleep(waitRetryDelayMs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveToElement(By path) {
        LOG.info("Moving to element " + path);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(path)).build().perform();
    }

    public void moveToElement(WebElement element) {
        LOG.info("Moving to element " + element.toString());
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element).build().perform();
    }

    public void waitForElementDisplayed(final By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(locator).isDisplayed();
            }
        });
    }

    public void waitForElementDisplayed(final By locator, long time) {
        WebDriverWait wait = new WebDriverWait(webDriver, time);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(locator).isDisplayed();
            }
        });
    }

    public void waitForElementDisplayed(final WebElement element) {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return element.isDisplayed();
            }
        });
    }

    public void waitForElementDisplayed(final WebElement element, long time) {
        WebDriverWait wait = new WebDriverWait(webDriver, time);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return element.isDisplayed();
            }
        });
    }

    public WebElement fluentWait(final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement foo = wait.until(
                new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(locator);
                    }
                }
        );
        return foo;
    }

    public void waitForElementNotPresent(final By by) {
        final Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withMessage("Element " + by + " is still present")
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(by)));
    }

    public void waitForElementNotVisible(final WebElement element) {
        final Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withMessage("Element " + element.getTagName() + " is still visible")
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
    }

    public void waitForElementNotVisible(final By element) {
        final Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withMessage("Element " + element + " is still visible")
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public void waitForElementNotVisibleByTime(final By element, long seconds) {
        final Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withMessage("Element " + element.toString() + " is still visible")
                .withTimeout(seconds, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }


    public static Alert waitForAlert(WebDriver driver, int seconds) {
        Wait<WebDriver> wait = new WebDriverWait(driver, seconds);
        return wait.until(new ExpectedCondition<Alert>() {
            @Override
            public Alert apply(WebDriver driver) {
                Alert alert = driver.switchTo().alert();
                return alert;
            }
        });
    }

    public static void waitForAlertNotPresent(WebDriver driver, int seconds) {
        Wait<WebDriver> wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));

    }


    public void waitForIFrame(final String iFrameName, int timeOutInSeconds) {
        try {
            WebDriverWait wdw = new WebDriverWait(webDriver, timeOutInSeconds);
            webDriver.switchTo().defaultContent();
            wdw.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrameName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForIFrameIgnoringExceptions(final String iFrameName) {
        try {
            Wait wait = new FluentWait(webDriver).withTimeout(10, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS)
                    .ignoring(InvalidSelectorException.class)
                    .ignoring(ElementNotVisibleException.class)
                    .ignoring(NoSuchFrameException.class);
            webDriver.switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrameName));

            /*new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    return webDriver.switchTo().frame(iFrameName) != null;
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Alert getAlert() {
        return waitForAlert(webDriver, 10);
    }

    public void ifAlertPresentAcceptHim(WebDriver webDriver){
        if(isAlertPresent(webDriver)){
            Alert al = webDriver.switchTo().alert();
            al.accept();
        }
    }

    public Alert isAlertPresent() {
        try {
            return webDriver.switchTo().alert();
        } catch (NoAlertPresentException e) {
            return null;
        }
    }

    public WebElement isAlertDialogPresent() {
        try {
            return webDriver.switchTo().activeElement();
        } catch (NoAlertPresentException e) {
            return null;
        }
    }

    public void takeConfirm() {
        try {
            Alert alert = getAlert();
            if (alert != null) {
                LOG.info(webDriver.switchTo().alert().getText());
                webDriver.switchTo().alert().accept();
            }
        } catch (NoAlertPresentException nape) {
            LOG.error(nape.getMessage());
        }
    }

    public void dismissConfirm() {
        try {
            Alert alert = webDriver.switchTo().alert();
            if (alert != null) {
                alert.dismiss();

            }
        } catch (NoAlertPresentException e) {
            LOG.info("Alert is not present here");
        } finally {
            waitForAlertNotPresent(webDriver, 10);
        }


    }


    public void getScreenShot(String pathToFile) {
        if (webDriver == null) throw new NullPointerException("webDriver is undefined");
        try {
            File failedTestCaseFolder = new File(pathToFile);
            failedTestCaseFolder.mkdirs();
            File scrFile;
            if (webDriver.getClass().getSimpleName().equals("RemoteWebDriver")) {
                Augmenter augmenter = new Augmenter();
                TakesScreenshot ts = (TakesScreenshot) augmenter.augment(webDriver);
                scrFile = ts.getScreenshotAs(OutputType.FILE);
            } else {
                scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            }
            FileUtils.copyFile(scrFile, new File(failedTestCaseFolder, "screenshot.png"));
            FileOutputStream fos = new FileOutputStream(new File(failedTestCaseFolder, "page_source.html"));
            fos.write(webDriver.getPageSource().getBytes());
            fos.close();

        } catch (Exception e) {
            LOG.error("An error occurred during screen shot taking: " + e.getMessage());
        }
    }

    public void getPageSourceInfo(String pathToFile) {
        if (webDriver == null) throw new NullPointerException("webDriver is undefine");
        try {
            File failedTestCaseFolder = new File(pathToFile);
            failedTestCaseFolder.mkdirs();
            FileOutputStream fos = new FileOutputStream(new File(failedTestCaseFolder, "page_source.html"));
            fos.write(webDriver.getPageSource().getBytes());
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshPage() {
        LOG.info("Refreshing the page");
        webDriver.navigate().refresh();
    }

    public void refreshJavaScript() {
        LOG.info("Performing javascript refresh in browser...");
        new Actions(webDriver).keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
    }

    public void getConfirmation() {
        try {
            Alert alert = webDriver.switchTo().alert();
            if (alert != null) {
                alert.accept();
            }
        } catch (NoAlertPresentException e) {
            LOG.info("Alert is not present here.");
        }
    }

    public void waitingForElement(final By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, 15);
        WebElement element = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator);
            }
        });
    }


    public void isTitlePageContainsString(final String title) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                String currentTitle = webDriver.getTitle();
                return currentTitle != null && currentTitle.contains(title);
            }
        });
    }

    public boolean isElementVisible(final By locator) {
        try {
            return webDriver.findElement(locator).isDisplayed();//elementIsVisible(findElement(locator, webDriver));
        } catch (NoSuchElementException e) {
            LOG.error("Unable to locate element: " + locator.toString());
            return false;
        } catch (ElementNotVisibleException env) {
            LOG.error("Element is not visible: " + locator.toString());
            return false;
        }
    }

    public boolean isElementVisible(final By locator, String info) {
        try {
            return webDriver.findElement(locator).isDisplayed();//elementIsVisible(findElement(locator, webDriver));
        } catch (NoSuchElementException e) {
            LOG.error(e.getClass().getSimpleName() + " ." + info);
        } catch (ElementNotVisibleException env) {
            LOG.error(env.getClass().getSimpleName() + " ." + info);
        }
        return false;
    }

    public boolean isElementVisible(final WebElement element) {
        try {
            return element.isDisplayed();
        } catch (ElementNotVisibleException env) {
            LOG.error(env.getMessage());
            return false;
        }
    }

    public boolean isElementVisible(WebDriver webDriver, final WebElement element) {
        this.webDriver = webDriver;
        try {
            return element.isDisplayed();
        } catch (ElementNotVisibleException env) {
            LOG.error(env.getMessage());
            return false;
        }
    }

    protected WebElement findElement(By by) {
        try {
            return webDriver.findElement(by);
        } catch (NoSuchElementException e) {
            return null;
        } catch (WebDriverException e) {
            LOG.info(String.format("WebDriverException thrown by findElement(%s)", by), e);
            return null;
        }
    }

    public void executeJavaScript(String script, Object... objects) {
        if (objects == null) ((JavascriptExecutor) webDriver).executeScript(script);
        else ((JavascriptExecutor) webDriver).executeScript(script, objects);
    }

    public Object executeJavaScript(String script) {
        return ((JavascriptExecutor) webDriver).executeScript(script);
    }

    public void scrollWindow(String script) {
        executeJavaScript(script);
    }

    public void setWindowSize(WebDriver webDriver, Dimension size) {
        webDriver.manage().window().setSize(size);
    }

    protected void getMainWindow(String parentWindowHandle) {
        webDriver.switchTo().window(parentWindowHandle);
    }

    protected void switchToWindowByWindowTitle(String windowTitle) {
        for (String windowHandle : webDriver.getWindowHandles()) {
            WebDriver popup = webDriver.switchTo().window(windowHandle);
            if (popup.getTitle().contains(windowTitle)) {
                break;
            }
            SysUtils.sleep(1500);
        }
    }

    public void waitForPageLoaded(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };

        Wait<WebDriver> wait = new WebDriverWait(driver, Long.parseLong(AppConfig.getPageLoad()) / 1000);
        try {
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.", error);
        }
    }

    public void waitForAjax(int timeoutInSeconds) {
        try {
            if (webDriver instanceof JavascriptExecutor) {
                JavascriptExecutor jsDriver = (JavascriptExecutor) webDriver;
                Object jQuery = jsDriver.executeScript("return window.jQuery == undefined;");
                if ((boolean) jQuery) Thread.sleep(1000);
                for (int i = 0; i < timeoutInSeconds; i++) {
                    Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");
                    if (numberOfAjaxConnections instanceof Long) {
                        Long n = (Long) numberOfAjaxConnections;
                        if (0L == n.longValue())
                            break;
                        else LOG.info("Waiting for ajax complete..." + i);
                    }
                    Thread.sleep(1000);

                }
            } else {
                LOG.info("Web driver: cannot execute javascript");
                throw new TimeoutException();
            }
        } catch (InterruptedException e) {
            LOG.info(e);
        }
    }

    public void waitForAjax(int timeoutInSeconds, String ajaxInfo) {
        if (AppUtil.isStringEmpty(ajaxInfo)) waitForAjax(timeoutInSeconds);
        try {
            if (webDriver instanceof JavascriptExecutor) {
                JavascriptExecutor jsDriver = (JavascriptExecutor) webDriver;
                for (int i = 0; i < timeoutInSeconds; i++) {
                    Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");
                    if (numberOfAjaxConnections instanceof Long) {
                        Long n = (Long) numberOfAjaxConnections;
                        if (0L == n.longValue())
                            break;
                        else LOG.info(ajaxInfo + i);
                    }
                    Thread.sleep(1000);

                }
            } else {
                LOG.info("Web driver: cannot execute javascript");
                throw new TimeoutException();
            }
        } catch (InterruptedException e) {
            LOG.info(e);
        }
    }

    public void waitForCondition(final By by, final CONDITION_TO_CHECK condition_to_check, final int value) {
        WebDriverWait myWait = new WebDriverWait(webDriver, 45);
        ExpectedCondition<Boolean> conditionToCheck = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                switch (condition_to_check) {
                    case GREATER_THAN:
                        return input.findElements(by).size() > value;
                    case LOWER_THAN:
                        return (input.findElements(by).size() < value);
                    case EQUAL:
                        return (input.findElements(by).size() == value);
                    case NOT_EQUAL:
                        return (input.findElements(by).size() != value);
                    default:
                        return false;
                }
            }
        };
        myWait.until(conditionToCheck);
    }


    public void waitForSecondWindow(final Long TIMETOWAITINSECONDS) {
        FluentWait myWait = new WebDriverWait(webDriver, TIMETOWAITINSECONDS).pollingEvery(1, TimeUnit.SECONDS);
        ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.getWindowHandles().size() == 2;
            }
        };
        myWait.until(condition);
    }

    public void waitForNewWindowIsOpened(final int TIMETOWAITINSECONDS, final int previousNumOfWindows) {
        FluentWait myWait = new WebDriverWait(webDriver, TIMETOWAITINSECONDS).pollingEvery(1, TimeUnit.SECONDS);
        ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.getWindowHandles().size() == previousNumOfWindows + 1;
            }
        };
        myWait.until(condition);
    }

    public void unSuccessTest(String testName) {
        isTestFailed = true;
        getScreenShot(AppUtil.getRelativePathToImageWithClass(BasicTestsExecutor.class.getResource("/"), "resources/screenshots/" + testName + "/"));
    }

    public enum CONDITION_TO_CHECK {
        GREATER_THAN,
        LOWER_THAN,
        EQUAL,
        NOT_EQUAL
    }

    public static void getMemoryInfo() {
        Runtime rt = Runtime.getRuntime();
        long totalMem = rt.totalMemory();
        long maxMem = rt.maxMemory();
        long freeMem = rt.freeMemory();
        double megs = 1048576.0;
        LOG.info("Total Memory: " + totalMem + " (" + (totalMem / megs) + " MiB)");
        LOG.info("Max Memory:   " + maxMem + " (" + (maxMem / megs) + " MiB)");
        LOG.info("Free Memory:  " + freeMem + " (" + (freeMem / megs) + " MiB)");
    }

    public Boolean IsElementIconPresented(WebElement image) {
        Boolean imageLoaded = (Boolean) ((JavascriptExecutor) webDriver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", image);

        return imageLoaded;
    }

    public boolean isAlertPresent(WebDriver webDriver) {
        try {
            webDriver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public void executeScript(String file) throws Throwable {
        // create a script engine manager
        ScriptEngineManager factory = new ScriptEngineManager();
        // create a JavaScript engine
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        // evaluate JavaScript code from String
        engine.eval(new java.io.FileReader(file));
    }

}
