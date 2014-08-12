package pageObjPattern.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pageObjPattern.basePage.MainPage;

/**
 * Created with IntelliJ IDEA.
 * User: vanle
 * Date: 10.08.14
 * Time: 17:54
 * To change this template use File | Settings | File Templates.
 */
public class LeadPage extends MainPage{

    public LeadPage(WebDriver webDriver){
        super(webDriver);
    }

    public void createNewLead(String fName, String lName, String phone, String altPhone, String email, String priority, String followup, String comment, boolean withAddNew){
        typeFirstName(fName);
        typeLastName(lName);
        typePhone(phone);
        typeAltPhone(altPhone);
        typeEmail(email);
        typePriority(priority);
        typeDatePicher(followup);
        typeComment(comment);
        if(withAddNew){
            clickSaveAndAddAnotherButton();
        }
        else{
            clickCreateLeadButton();
        }
    }

    public void typeFirstName(String value){
        webDriver.findElement(getFirstName()).clear();
        webDriver.findElement(getFirstName()).sendKeys(value);
    }

    public By getFirstName(){
        return By.xpath("//input[@ng-model='lead.first_name']");
    }

    public void typeLastName(String value){
        webDriver.findElement(getLastName()).clear();
        webDriver.findElement(getLastName()).sendKeys(value);
    }

    public By getLastName(){
        return By.xpath("//input[@ng-model='lead.last_name']");
    }

    public void typePhone(String value){
        webDriver.findElement(getPhone()).clear();
        webDriver.findElement(getPhone()).sendKeys(value);
    }

    public By getPhone(){
        return By.name("phone");
    }

    public void typeAltPhone(String value){
        webDriver.findElement(getAltPhone()).clear();
        webDriver.findElement(getAltPhone()).sendKeys(value);
    }

    public By getAltPhone(){
        return By.name("alt_phone");
    }

    public void typeEmail(String value){
        webDriver.findElement(getEmail()).clear();
        webDriver.findElement(getEmail()).sendKeys(value);
    }

    public By getEmail(){
        return By.name("email");
    }

    public void typePriority(String value){
        webDriver.findElement(getPriority()).clear();
        webDriver.findElement(getPriority()).sendKeys(value);
    }

    public By getPriority(){
        return By.name("priority");
    }

    public void typeDatePicher(String value){
        webDriver.findElement(getDatePickerPopup()).clear();
        webDriver.findElement(getDatePickerPopup()).sendKeys(value);
    }

    public By getDatePickerPopup(){
        return By.xpath("//input[@datepicker-popup='yyyy-MM-dd']");
    }

    public void typeComment(String value){
        webDriver.findElement(getAddCommentName()).clear();
        webDriver.findElement(getAddCommentName()).sendKeys(value);
    }

    public void typeAddRecord(String value){
        webDriver.findElement(getAddRecord()).clear();
        webDriver.findElement(getAddRecord()).sendKeys(value);
    }

    public By getAddRecord(){
        return By.xpath("//textarea[@ng-model='record.body']");
    }

    public By getAddCommentName(){
        return By.xpath("//textarea[@ng-model='record.body']");
    }

    public void clickSaveLeadButton(){
        webDriver.findElement(getSaveLeadButton()).click();
    }

    public By getSaveLeadButton(){
        return By.xpath("//button[contains(text(),'Save')");
    }

    public void clickCreateLeadButton(){
        webDriver.findElement(getCreateLeadButton()).click();
    }

    public By getCreateLeadButton(){
        return By.xpath("//button[contains(text(),'Create')");
    }

    public void clickSaveAndAddAnotherButton(){
        webDriver.findElement(getSaveAndAddAnotherButton()).click();
    }

    public By getSaveAndAddAnotherButton(){
        return By.xpath("//button[contains(text(),'Save and Add Another')");
    }

    public void clickSaveAndGoToListButton(){
        webDriver.findElement(getSaveAndAddAnotherButton()).click();
    }

    public By getSaveAndGoToListButton(){
        return By.xpath("//button[contains(text(),'Save and Go to List')");
    }

    public void selectAssigneeTo(String value){
        webDriver.findElement(By.xpath("//select[@ng-model='lead.assigned_to']")).click();
        new Select(webDriver.findElement(By.xpath("//select[@ng-model='lead.assigned_to']"))).selectByVisibleText(value);
    }

    public void selectStatus(String value){
        webDriver.findElement(By.xpath("//select[@ng-model='lead.status']")).click();
        new Select(webDriver.findElement(By.xpath("//select[@ng-model='lead.status']"))).selectByVisibleText(value);
    }

}

