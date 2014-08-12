package pageObjPattern.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pageObjPattern.basePage.MainPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vanle
 * Date: 10.08.14
 * Time: 18:32
 * To change this template use File | Settings | File Templates.
 */
public class LeadsListPage extends MainPage{

    public LeadsListPage(WebDriver webDriver){
        super(webDriver);
    }

    public void clickMyCurrentLeads(){
        webDriver.findElement(getMyCurrentLeadsButton()).click();
    }

    public By getMyCurrentLeadsButton(){
        return By.xpath("//a[contains(text(),'My Current Leads')");
    }

    public void clickOpenLeads(){
        webDriver.findElement(getOpenLeadsButton()).click();
    }

    public By getOpenLeadsButton(){
        return By.xpath("//a[contains(text(),'Open Leads')");
    }

    public void clickAllLeads(){
        webDriver.findElement(getAllLeadsButton()).click();
    }

    public By getAllLeadsButton(){
        return By.xpath("//a[contains(text(),'All Leads')");
    }

    public void clickClosedLeads(){
        webDriver.findElement(getClosedLeadsButton()).click();
    }

    public By getClosedLeadsButton(){
        return By.xpath("//a[contains(text(),'Closed Leads')");
    }

    public List<WebElement> getListLeadsInAssignedLeads(){
        if(webDriver.findElement(By.xpath("//div[@class='ng-scope']/table/tbody/tr[@class='ng-scope']")) != null){
            return webDriver.findElements(By.xpath("//div[@class='ng-scope']/table/tbody/tr[@class='ng-scope']"));
        }
        else{
            webDriver.findElements(By.xpath("//div[@class='ng-scope']/table/tbody/tr[@class='']/td[contains(text(),'No leads')]"));
            return new ArrayList<>(0);
        }
    }

}

