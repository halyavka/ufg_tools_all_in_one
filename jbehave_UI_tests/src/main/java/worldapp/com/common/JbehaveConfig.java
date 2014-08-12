package worldapp.com.common;

import org.apache.commons.lang.ArrayUtils;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.failures.PendingStepStrategy;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.web.selenium.*;
import worldapp.com.myConfig.DriverScreenshotOnFailure;
import worldapp.com.myConfig.MyPerStoryWebDriverSteps;
import worldapp.com.steps.*;
import java.util.List;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.*;
import static org.jbehave.web.selenium.WebDriverHtmlOutput.WEB_DRIVER_HTML;

/**
 * Created with IntelliJ IDEA.
 * User: ivan.halyavka
 * Date: 30.11.13
 * Time: 10:52
 * To change this template use File | Settings | File Templates.
 */
public class JbehaveConfig extends JUnitStories {

    private int threads = 1;

    private WebDriverProvider driverProvider = new SimpleProvider();

    PendingStepStrategy pendingStepStrategy = new FailingUponPendingStep();
    CrossReference crossReference = new CrossReference()
            .withJsonOnly()
            .withPendingStepStrategy(pendingStepStrategy)
            .withOutputAfterEachStory(true)
            .excludingStoriesWithNoExecutedScenarios(true);
    ContextView contextView = new LocalFrameContextView().sized(600, 50);
    SeleniumContext seleniumContext = new SeleniumContext();
    SeleniumStepMonitor stepMonitor = new SeleniumStepMonitor(contextView, seleniumContext, crossReference.getStepMonitor());
    Format[] formats = new Format[] { new SeleniumContextOutput(seleniumContext), CONSOLE, WEB_DRIVER_HTML };
    StoryReporterBuilder reporterBuilder = new StoryReporterBuilder()
            .withCodeLocation(codeLocationFromClass(JbehaveConfig.class))
            .withFailureTrace(true)
            .withFailureTraceCompression(true)
            .withDefaultFormats().withFormats(formats)
            .withCrossReference(crossReference);

    public JbehaveConfig() {
        configuredEmbedder().embedderControls()
                .doGenerateViewAfterStories(true)
                .doIgnoreFailureInStories(true)
                .doIgnoreFailureInView(true)
                .useThreads(threads)
                .useStoryTimeoutInSecs(15000);

       // configuredEmbedder().useMetaFilters(Arrays.asList("+skip", "id"));
       // configuration().storyControls().useStoryMetaPrefix("").useScenarioMetaPrefix("scenario_");
    }

    @Override
    public Configuration configuration() {
        return new SeleniumConfiguration()
                .useWebDriverProvider(driverProvider)
                .usePendingStepStrategy(pendingStepStrategy)
                .useStoryControls(new StoryControls().doResetStateBeforeScenario(true))
                .useStepMonitor(stepMonitor)
                .useStoryLoader(new LoadFromClasspath(JbehaveConfig.class))
                .useStoryReporterBuilder(reporterBuilder);
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        /*ApplicationContext context = new SpringApplicationContextFactory("worldapp/com/common/webAcceptanceTest.xml").createApplicationContext();
        return new SpringStepsFactory(configuration(), context);*/

        return new InstanceStepsFactory(configuration(), ArrayUtils.addAll(generalSteps(), storySteps()));
    }

    public Object[] generalSteps() {
        return new Object[]{
                new MyPerStoryWebDriverSteps((SimpleProvider) driverProvider),
                new BasicSteps((SimpleProvider) driverProvider),
                new LoginSteps((SimpleProvider) driverProvider),
                new DriverScreenshotOnFailure((SimpleProvider) driverProvider)
        };
    }

    public Object[] storySteps() {
        return new Object[]{
                /*new SurveySteps((SimpleProvider) driverProvider),
                new FormSteps((SimpleProvider) driverProvider),
                new LinearFormSteps((SimpleProvider) driverProvider),
                new BranchingLogicSteps((SimpleProvider) driverProvider),
                new SetValueSteps((SimpleProvider) driverProvider),
                new VotingSteps((SimpleProvider) driverProvider),
                new RBRSteps((SimpleProvider) driverProvider),
                new PortalSteps((SimpleProvider) driverProvider),
                new OfflineFormSteps((SimpleProvider) driverProvider),
                new OfflineLoginSteps((SimpleProvider) driverProvider),
                new PluginSteps((SimpleProvider) driverProvider)*/
        };
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/*ForReport.story", "**/excluded*.story");
    }

}