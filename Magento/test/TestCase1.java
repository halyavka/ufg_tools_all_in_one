import config.AppConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjPattern.AccountBasicTestsExecutor;
import pageObjPattern.basePage.MainPage;
import pageObjPattern.pages.LeadsListPage;

/**
 * Created with IntelliJ IDEA.
 * User: vanle
 * Date: 12.08.14
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */
public class TestCase1 extends AccountBasicTestsExecutor {

    @BeforeClass
    public void before_test(){
        MainPage mainPage = new MainPage();
        webDriver = mainPage.getWebDriver();
    }

    @Test
    public void test_Login_and_create_Leads(){
        webDriver.get(AppConfig.getStartUrl());
        waitForPageLoaded(webDriver);
        LeadsListPage leadsListPage = loginIntoAccount("vanle@ukr.net", "090289");



        logoutFromAccount();
    }


}
