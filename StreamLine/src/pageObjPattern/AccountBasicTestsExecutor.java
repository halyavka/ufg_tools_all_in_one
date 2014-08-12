package pageObjPattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import pageObjPattern.basePage.HomePage;
import pageObjPattern.pages.LeadsListPage;
import utils.SysUtils;

public class AccountBasicTestsExecutor extends BasicTestsExecutor {
    protected String userLogin;
    protected String userPassword;
    protected static final Logger LOG = Logger.getLogger(AccountBasicTestsExecutor.class);
    protected HomePage homePage;

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    protected LeadsListPage loginIntoAccount(String login, String password) {
        homePage = new HomePage(webDriver);
        homePage.clickLoginButton();
        homePage.typeLoginEmail(login);
        homePage.typePass(password);
        homePage.clickSignIn();
        waitForElementDisplayed(By.xpath("//*[contains(text(),'+ Add lead')]"), 20);
        return new LeadsListPage(webDriver);
    }

    protected void logoutFromAccount() {
        homePage = new HomePage(webDriver);
        homePage.clickOnMyAccount();
        ((JavascriptExecutor) webDriver).executeScript("document.getElementsByClassName('dropdown-menu')[1].style.display = '';");
        SysUtils.sleep(1000);
        homePage.clickLogoutButton();
        waitForPageLoaded(webDriver);
        waitForElementDisplayed(homePage.getLogin(), 20);
    }

}
