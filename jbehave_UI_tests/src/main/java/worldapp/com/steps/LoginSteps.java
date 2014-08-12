package worldapp.com.steps;

import org.jbehave.core.annotations.*;
import org.junit.Assert;
import worldapp.com.common.SimpleProvider;
import worldapp.com.pages.PageFactory;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey.melnychenko
 * Date: 2/24/14
 * Time: 8:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginSteps extends PageFactory {

    private PageFactory pageFactory;
    private TestVariables testVariables;
    private String userPassword = "world1";

    public LoginSteps(SimpleProvider webDriverProvider){
        super(webDriverProvider);
    }

    @BeforeScenario
    public void beforeScenario(){
       // webDriver = webDriverProvider.get();
        if(webDriverProvider instanceof SimpleProvider){
            pageFactory = new PageFactory(webDriverProvider);
        //    loginPage = pageFactory.newLoginPage();
            this.testVariables = ((SimpleProvider) webDriverProvider).getTestVariables();
        }
    }

    @Given("login as $userLogin")
    public void loginAs(@Named("userLogin") String userLogin){
      //  loginPage.goToLoginUrl();
        testVariables.setUserLogin(userLogin);
        testVariables.setUserPassword(userPassword);
     //   surveysPage = loginPage.loginWithCheck(userLogin, userPassword);
    }

    @Given("open login page")
    public void givenOpenLoginPage(){
      //  loginPage.goToLoginUrl();
      //  waitForPageLoaded(webDriver);
    }

    @When("login to account as $login")
    @Aliases(values = "login to account as <login>")
    public void typeLoginAndClickLoginButton(@Named("login") String login){
        testVariables.setUserLogin(login);
        testVariables.setUserPassword(userPassword);
      //  surveysPage = loginPage.loginWithoutCheck(login, userPassword);
    }

    @Then("user is on survey dashboard page")
    public void thenLoginCheck(){
    //    Assert.assertTrue("Could not login!!!", webDriver.findElement(loginPage.getLogoutLinkNameByName()).isDisplayed() &&
     //           webDriver.findElement(loginPage.getMyAccountLink()).isDisplayed());
     //   LOG.info("Login is successful");
    }
}
