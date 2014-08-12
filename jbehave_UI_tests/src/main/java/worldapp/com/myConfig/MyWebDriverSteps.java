package worldapp.com.myConfig;

import org.jbehave.web.selenium.WebDriverProvider;

/**
 * Created with IntelliJ IDEA.
 * User: vanle
 * Date: 21.01.14
 * Time: 11:48
 * To change this template use File | Settings | File Templates.
 */
public class MyWebDriverSteps {//extends AccountBasicTestsExecutor{

    protected WebDriverProvider driverProvider;

    public MyWebDriverSteps(WebDriverProvider driverProvider) {
        this.driverProvider = driverProvider;
    }

    public WebDriverProvider getDriverProvider() {
        return driverProvider;
    }


}
