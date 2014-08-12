package leads;

import config.AppConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjPattern.AccountBasicTestsExecutor;
import pageObjPattern.basePage.MainPage;
import pageObjPattern.pages.LeadPage;
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
        LeadPage leadPage = new LeadPage(webDriver);
        for(int i=1; i<=30; i++){
            leadPage.createNewLead("first_name_"+i, "last_name_"+i, "444222342"+i, "111876342"+i, "testLead"+i+"@test.com", String.valueOf(i), "2014-08-14", "Creating lead number "+i, true);
        }


        logoutFromAccount();
    }


}
