package pageObjPattern.basePage;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjPattern.BasicTestsExecutor;
import pageObjPattern.pages.LeadsListPage;

/**
 * Created with IntelliJ IDEA.
 * User: vanle
 * Date: 07.08.14
 * Time: 01:26
 * To change this template use File | Settings | File Templates.
 */
public class HomePage extends MainPage{

    private WebElement element;
    protected static Logger LOG = Logger.getLogger(HomePage.class);

    public By getLeadsButton(){
        return By.xpath("//a[@class='leads']");
    }

    public void clickLeadsButtonOnNav(){
        webDriver.findElement(getLeadsButton()).click();
    }

    public void clickAddLeadsOnNav(){
        webDriver.findElement(getAddLeadsButton()).click();
    }

    public By getAddLeadsButton(){
        return By.xpath("//a[@class='add-lead']");
    }

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickQuestion(){
        webDriver.findElement(getQuestion()).click();
    }

    public By getQuestion(){
        return By.xpath("//svg[@id='Layer_1']");
    }

    public void typeFirstName(String name){
        webDriver.findElement(getFirstName()).clear();
        webDriver.findElement(getFirstName()).sendKeys(name);
    }

    public By getFirstName(){
        return By.name("first_name");
    }

    public void clickOnMyAccount(){
        webDriver.findElement(By.xpath("//*[@ng-show='isAuthenticated()']")).click();
    }

    public void clickLogoutButton(){
        webDriver.findElement(By.xpath("//*[@ng-click='logout()']")).click();
    }


    public void typeLastName(String name){
        webDriver.findElement(getLastName()).clear();
        webDriver.findElement(getLastName()).sendKeys(name);
    }

    public By getLastName(){
        return By.name("last_name");
    }

    public void typeEmail(String name){
        webDriver.findElement(getEmail()).clear();
        webDriver.findElement(getEmail()).sendKeys(name);
    }

    public By getEmail(){
        return By.name("email");
    }

    public void typePassword(String name){
        webDriver.findElement(getPassword()).clear();
        webDriver.findElement(getPassword()).sendKeys(name);
    }

    public By getPassword(){
        return By.name("password");
    }

    public void typePasswordRepeat(String name){
        webDriver.findElement(getPasswordRepeat()).clear();
        webDriver.findElement(getPasswordRepeat()).sendKeys(name);
    }

    public By getPasswordRepeat(){
        return By.name("password1");
    }

    public void typeCompanyName(String name){
        webDriver.findElement(getCompanyName()).clear();
        webDriver.findElement(getCompanyName()).sendKeys(name);
    }

    public By getCompanyName(){
        return By.name("company_name");
    }

    public void typeCompanyAddress(String name){
        webDriver.findElement(getCompanyAddress()).clear();
        webDriver.findElement(getCompanyAddress()).sendKeys(name);
    }

    public By getCompanyAddress(){
        return By.name("company_address");
    }

    public void typeCardNumber(String name){
        webDriver.findElement(getCardNumber()).clear();
        webDriver.findElement(getCardNumber()).sendKeys(name);
    }

    public By getCardNumber(){
        return By.name("number");
    }

    public void typeCVC(String name){
        webDriver.findElement(getCVC()).clear();
        webDriver.findElement(getCVC()).sendKeys(name);
    }

    public By getCVC(){
        return By.name("cvc");
    }

    public void typeExpiry(String name){
        webDriver.findElement(getExpiry()).clear();
        webDriver.findElement(getExpiry()).sendKeys(name);
    }

    public By getExpiry(){
        return By.name("expiry");
    }

    public void clickSignUpSubmit(){
        webDriver.findElement(getSignUpButton()).click();
    }

    public By getSignUpButton(){
        return By.xpath("//button[@type='submit']");
    }

    public By getLoginForm(){
        return By.xpath("//form[@name='Form']");
    }

    public void clickSignIn(){
        webDriver.findElement(getSignInButton()).click();
    }

    public By getSignInButton(){
        return By.xpath("//button[contains(text(),'Sign in')]");
    }

    public void clickCancelButton(){
        webDriver.findElement(getCancelButton()).click();
    }

    public By getCancelButton(){
        return By.xpath("//button[contains(text(),'Cancel')]");
    }

    public void typeForgotPass(String password){
        webDriver.findElement(getForgotPass()).clear();
        webDriver.findElement(getForgotPass()).sendKeys(password);
    }

    public By getForgotPass(){
        return By.linkText("Forgot your password?");
    }

    public String getPlaceholderByElement(WebElement el){
        return el.getAttribute("placeholder");
    }

    public void typeLoginEmail(String email){
        webDriver.findElement(getEmailField()).clear();
        webDriver.findElement(getEmailField()).sendKeys(email);
    }

    public By getEmailField(){
        return By.name("login");
    }

    public void typePass(String pass){
        webDriver.findElement(getPasswordField()).clear();
        webDriver.findElement(getPasswordField()).sendKeys(pass);
    }

    public By getPasswordField(){
        return By.name("pass");
    }

    public void clickLoginButton(){
        webDriver.findElement(getLogin()).click();
    }

    public By getLogin(){
        return By.linkText("Log in");
    }

    public void clickSignUp(){
        webDriver.findElement(getSignUp()).click();
    }

    public By getSignUp(){
        return By.linkText("Sign Up");
    }

}
