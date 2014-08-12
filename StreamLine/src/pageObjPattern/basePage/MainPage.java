package pageObjPattern.basePage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import utils.AppUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: vanle
 * Date: 07.08.14
 * Time: 1:55
 * To change this template use File | Settings | File Templates.
 */
public class MainPage {

    private Long userID;
    protected WebDriver webDriver;
    public static String fileDownloadPath;
    protected static Logger LOG = Logger.getLogger(MainPage.class);

    public enum BROWSER {
        FIREFOX, CHROME, IE, SAFARI, OPERA, ANDROID, IPHONE
    }

    public WebDriver getWebDriver(){
        return webDriver;
    }

    public MainPage(String userLogin, BROWSER driver) {
        switch (driver) {
            case FIREFOX:
                createWebDriverFireFox();
                break;
            case CHROME:
               // createWebDriverChrome();
                break;
            case IE:
               // createWebDriverIE();
                break;
            case SAFARI:
               // createWebDriverSafari();
                break;
            case ANDROID:
               // createAndroidDriver();
                break;
            case IPHONE:
               // createIphoneDriver();
                break;
            default:
                createWebDriverFireFox();
        }
        webDriver.manage().deleteAllCookies();
        webDriver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
    }

    public MainPage() {
        webDriver = new FirefoxDriver();
    }

    public MainPage(Long userID) {
        this.userID = userID;
        createWebDriverFireFox();
    }


    public MainPage(WebDriver webDriver) {
        if (webDriver == null) {
            createWebDriverFireFox();
        } else this.webDriver = webDriver;
    }

    public synchronized void createWebDriverFireFox(boolean isDownloadProfileEnabled) {
        if (isDownloadProfileEnabled) {
            try {
                final String firebugPath = AppUtil.getRelativePathToImageWithClass(HomePage.class.getResource("/"), "resources/ff-profile/firebug-1.11.2-fx.xpi");
                final String firepathPath = AppUtil.getRelativePathToImageWithClass(HomePage.class.getResource("/"), "resources/ff-profile/firepath.xpi");
                // Add more if needed
                fileDownloadPath = AppUtil.getRelativePathToImageWithClass(MainPage.class.getResource("/"), "resources/export/tmp/");
                FirefoxProfile profile = new FirefoxProfile();
                profile.setEnableNativeEvents(false);
                profile.addExtension(new File(firebugPath));
                profile.setPreference("extensions.firebug.currentVersion", "9.9.9");
                profile.addExtension(new File(firepathPath));
                profile.deleteExtensionsCacheIfItExists(new File(firebugPath));
                profile.deleteExtensionsCacheIfItExists(new File(firepathPath));
                profile.setPreference("browser.download.folderList", 2);
                /*profile.setPreference(FirefoxProfile.PORT_PREFERENCE, "7088");*/
                profile.setPreference("browser.download.manager.showWhenStarting", false);
                profile.setPreference("browser.download.dir", fileDownloadPath);
                profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                profile.setPreference("browser.download.manager.showWhenStarting", false);
                profile.setPreference("pdfjs.disabled", true);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream, text/txt, text/plain, text/comma-separated-values, text/csv, application/vnd.ms-excel (official), application/msexcel, application/x-msexcel, application/x-ms-excel, application/x-excel, application/x-dos_ms_excel, application/xls, application/x-xls, application/pdf, application/x-pdf");
                profile.setPreference("extensions.firebug.showFirstRunPage", false);
                //profile.setPreference("browser.download.useDownloadDir", false);
              /*  if (InetAddress.getLocalHost().getHostAddress().equals(IRemoteResources.REMOTE_IP.split("http://")[1])) {
                    DesiredCapabilities capability = DesiredCapabilities.firefox();
                    capability.setCapability(FirefoxDriver.PROFILE, profile);
                    webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
                } else webDriver = new FirefoxDriver(profile);*/
                webDriver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
                webDriver.manage().deleteAllCookies();
                Assert.assertTrue(webDriver != null, "Could not create webDriver !!!");
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail("Could not create webDriver !!!" + e);
            }
        } else {
            createWebDriverFireFox();
        }
    }

    public synchronized void createWebDriverFireFox() {
        try {
            final String firebugPath = AppUtil.getRelativePathToImageWithClass(HomePage.class.getResource("/"), "resources/ff-profile/firebug-1.11.2-fx.xpi");
            // Add more if needed
            FirefoxProfile profile = new FirefoxProfile();
            profile.setEnableNativeEvents(false);
            profile.addExtension(new File(firebugPath));
            profile.deleteExtensionsCacheIfItExists(new File(firebugPath));
            profile.setPreference("browser.download.lastDir", "c:\\webdriverDownloads");
            profile.setPreference("extensions.firebug.showFirstRunPage", false);
            //webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
           /* if (InetAddress.getLocalHost().getHostAddress().equals(IRemoteResources.REMOTE_IP.split("http://")[1])) {
                DesiredCapabilities capability = DesiredCapabilities.firefox();
                *//*capability.setCapability("screenrecorder", true);*//*
                capability.setCapability(FirefoxDriver.PROFILE, profile);
                webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
            } else webDriver = new FirefoxDriver(profile);*/
            webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            webDriver.manage().deleteAllCookies();
            Assert.assertTrue(webDriver != null, "Could not create webDriver !!!");
            LOG.info("Firefox driver has been created");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Could not create webDriver !!!" + e);
        }
    }

/*    public HomePage switchToDefaultContent(HomePage mainPage) {
        Set<String> windowHandles = webDriver.getWindowHandles();
        webDriver.close();
        webDriver.switchTo().window(windowHandles.iterator().next());
        if (mainPage instanceof QuestionListPage) {
            return new QuestionListPage(webDriver);
        }
        if (mainPage instanceof SurveysPage) {
            return new SurveysPage(webDriver);
        }
        if (mainPage instanceof LaunchPage) {
            return new LaunchPage(webDriver);
        }
        return null;
    }*/

    public WebDriver timeoutsForWaitingPresentElement(int timeOutInMilliSec) {
        webDriver.manage().timeouts().implicitlyWait(timeOutInMilliSec, TimeUnit.MILLISECONDS);
        return webDriver;
    }

    private void loseFocus() throws Exception {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_ALT);
        Thread.sleep(200);
        // miNimize
        robot.keyPress(KeyEvent.VK_N);
        robot.keyRelease(KeyEvent.VK_N);
    }

}
