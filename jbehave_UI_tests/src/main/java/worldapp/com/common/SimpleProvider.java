package worldapp.com.common;

import org.jbehave.web.selenium.WebDriverProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import worldapp.com.steps.TestVariables;
import java.io.File;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey.melnychenko
 * Date: 2/19/14
 * Time: 8:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleProvider implements WebDriverProvider {

    public SimpleProvider(){
    }

    private TestVariables testVariables;//

    private WebDriver webDriver;

    private static final ThreadLocal<WebDriver> driverProviderThreadLocal = new ThreadLocal<>();

    @Override
    public WebDriver get() {
        WebDriver webDriver = driverProviderThreadLocal.get();
        return webDriver;
    }

    @Override
    public void initialize() {
        webDriver = createFireFoxDriverWithProfiler();
        driverProviderThreadLocal.set(webDriver);
    }

    @Override
    public boolean saveScreenshotTo(String s) {
        return false;
    }

    @Override
    public void end() {
        if(webDriver != null) {
            webDriver.quit();
        }
        if(driverProviderThreadLocal != null) {
            driverProviderThreadLocal.remove();
        }
    }

    public TestVariables getTestVariables() {
        if(testVariables == null) {
            testVariables = new TestVariables();
        }
        return testVariables;
    }

    public void setTestVariables(TestVariables testVariables) {
        this.testVariables = testVariables;
    }

    public WebDriver createFireFoxDriverWithProfiler(){
        try {
            final String firebugPath;// = AppUtil.getRelativePathToImageWithClass(FileLoader.class.getResource("/"), "resources/ff-profile/firebug-1.11.2-fx.xpi");
            final String firepathPath;// = AppUtil.getRelativePathToImageWithClass(FileLoader.class.getResource("/"), "resources/ff-profile/firepath.xpi");
            String fileDownloadPath;// = AppUtil.getRelativePathToImageWithClass(FileLoader.class.getResource("/"), "resources/export/tmp/");
            FirefoxProfile profile = new FirefoxProfile();
            profile.setEnableNativeEvents(false);
           // profile.addExtension(new File(firebugPath));
            profile.setPreference("extensions.firebug.currentVersion", "9.9.9");
           // profile.addExtension(new File(firepathPath));
           // profile.deleteExtensionsCacheIfItExists(new File(firebugPath));
           // profile.deleteExtensionsCacheIfItExists(new File(firepathPath));
            profile.setPreference("browser.download.folderList", 2);
            //profile.setPreference(FirefoxProfile.PORT_PREFERENCE, "7088");
            profile.setPreference("browser.download.manager.showWhenStarting", false);
           // profile.setPreference("browser.download.dir", fileDownloadPath);
            profile.setPreference("browser.helperApps.alwaysAsk.force", false);
            profile.setPreference("browser.download.manager.showWhenStarting", false);
            profile.setPreference("pdfjs.disabled", true);
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream, text/txt, text/plain, text/comma-separated-values, text/csv, application/vnd.ms-excel (official), application/msexcel, application/x-msexcel, application/x-ms-excel, application/x-excel, application/x-dos_ms_excel, application/xls, application/x-xls, application/pdf, application/x-pdf");
            profile.setPreference("extensions.firebug.showFirstRunPage", false);
            //profile.setPreference("browser.download.useDownloadDir", false);
            /*if (InetAddress.getLocalHost().getHostAddress().equals(IRemoteResources.REMOTE_IP.split("http://")[1])) {
                DesiredCapabilities capability = DesiredCapabilities.firefox();
                capability.setCapability(FirefoxDriver.PROFILE, profile);
                webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
            } else {*/
                webDriver = new FirefoxDriver(profile);
           // }
          //  webDriver.manage().timeouts().implicitlyWait(Integer.parseInt(AppConfig.getPageLoad()), TimeUnit.MILLISECONDS);
            webDriver.manage().deleteAllCookies();
            Assert.assertTrue("Could not create webDriver !!!", webDriver != null);
            return webDriver;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Could not create webDriver !!!" + e);
            return null;
        }
    }
}
