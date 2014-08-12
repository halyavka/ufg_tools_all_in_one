package worldapp.com.myConfig;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.web.selenium.WebDriverProvider;
import org.jbehave.web.selenium.WebDriverSteps;
import worldapp.com.common.SimpleProvider;
import worldapp.com.pages.PageFactory;

/**
 * Created with IntelliJ IDEA.
 * User: vanle
 * Date: 30.11.13
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
public class MyPerStoryWebDriverSteps extends PageFactory {

    public MyPerStoryWebDriverSteps(SimpleProvider driverProvider) {
        super(driverProvider);
    }

    @BeforeStory
    public void beforeStory() throws Exception {
        System.out.println("===== Before Story MyPerStoryWebDriverSteps =====");
        webDriverProvider.initialize();
    }

    @AfterStory
    public void afterStory() throws Exception {
        System.out.println("===== After Story MyPerStoryWebDriverSteps =====");
        webDriverProvider.end();
    }

}
