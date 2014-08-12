package worldapp.com.myConfig;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.ScenarioType;
import org.jbehave.core.failures.PendingStepFound;
import org.jbehave.core.failures.UUIDExceptionWrapper;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.web.selenium.WebDriverSteps;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import worldapp.com.common.SimpleProvider;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: ivan.halyavka
 * Date: 30.11.13
 * Time: 10:57
 * To change this template use File | Settings | File Templates.
 */
public class DriverScreenshotOnFailure extends WebDriverSteps {

    protected static Logger LOG = Logger.getLogger(DriverScreenshotOnFailure.class);
    public static final String DEFAULT_SCREENSHOT_PATH_PATTERN = "{0}/screenshots/failed-scenario-{1}.png";
	protected final StoryReporterBuilder reporterBuilder;
	protected final String screenshotPathPattern;

    private WebDriver webDriver;
    private UUID uuid = UUID.randomUUID();

	public DriverScreenshotOnFailure(SimpleProvider driverProvider) {
		this(driverProvider, new StoryReporterBuilder());
	}

	public DriverScreenshotOnFailure(SimpleProvider driverProvider, StoryReporterBuilder reporterBuilder) {
		this(driverProvider, reporterBuilder, DEFAULT_SCREENSHOT_PATH_PATTERN);
	}

	public DriverScreenshotOnFailure(SimpleProvider driverProvider, StoryReporterBuilder reporterBuilder, String str) {
		super(driverProvider);
		this.reporterBuilder = reporterBuilder;
		this.screenshotPathPattern = str;
	}

    @BeforeScenario
    public void before(){
        this.webDriver = driverProvider.get();
    }

    @AfterScenario(uponType = ScenarioType.EXAMPLE, uponOutcome = AfterScenario.Outcome.FAILURE)
    public void takeScreenShotExample(UUIDExceptionWrapper uuidWrappedFailure) {
        if (uuidWrappedFailure instanceof PendingStepFound) return;
        if (uuidWrappedFailure instanceof NotPerformedStepFound) return;
        String screenshotPath = MessageFormat.format(screenshotPathPattern, reporterBuilder.outputDirectory(), uuidWrappedFailure.getUUID());
        saveScreenshotTo(screenshotPath);
        logoutFromAccountWebDriver(webDriver);
    }

    @AfterScenario(uponType = ScenarioType.NORMAL, uponOutcome = AfterScenario.Outcome.FAILURE)
    public void takeScreenShotNormal(UUIDExceptionWrapper uuidWrappedFailure) {
        if (uuidWrappedFailure instanceof PendingStepFound) return;
        if (uuidWrappedFailure instanceof NotPerformedStepFound) return;
        String screenshotPath = MessageFormat.format(screenshotPathPattern, reporterBuilder.outputDirectory(), uuidWrappedFailure.getUUID());
        saveScreenshotTo(screenshotPath);
        logoutFromAccountWebDriver(webDriver);
    }

    @AfterScenario(uponType = ScenarioType.NORMAL, uponOutcome = AfterScenario.Outcome.SUCCESS)
    public void logoutAfterScenarioNormal(){
        String screenshotPath = MessageFormat.format(screenshotPathPattern, reporterBuilder.outputDirectory(), uuid);
        saveScreenshotTo(screenshotPath);
        logoutFromAccountWebDriver(webDriver);
    }

    @AfterScenario(uponType = ScenarioType.EXAMPLE, uponOutcome = AfterScenario.Outcome.SUCCESS)
    public void logoutAfterScenarioExample(){
        String screenshotPath = MessageFormat.format(screenshotPathPattern, reporterBuilder.outputDirectory(), uuid);
        saveScreenshotTo(screenshotPath);
        logoutFromAccountWebDriver(webDriver);
    }

    public boolean saveScreenshotTo(String screenshotPath) {
        File screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screen, new File(screenshotPath));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void logoutFromAccountWebDriver(WebDriver webDriver) {
        if(((FirefoxDriver) webDriver).getSessionId() != null) {
     //       webDriver.get(AppConfig.getStartUrl() + "/servlet/survey.SurveyLogout");
        }
        else {
            LOG.info("WEBDRIVER IS OF "+webDriver.getClass().getSimpleName()+" INSTANCE");
        }
    }

}
